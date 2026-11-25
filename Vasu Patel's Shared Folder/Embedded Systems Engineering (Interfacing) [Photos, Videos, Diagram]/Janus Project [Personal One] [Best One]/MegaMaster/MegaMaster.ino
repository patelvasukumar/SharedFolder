/*
Project - Janus by Vasu and Lam
*/

#include "Configuration.h"

LiquidCrystal lcd1(Pins::LCD_RS, Pins::LCD_EN, Pins::LCD_D4, Pins::LCD_D5, Pins::LCD_D6, Pins::LCD_D7);

char keyMap[4][4] = {
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};
byte keypadRowPins[4] = {46, 47, 48, 49};
byte keypadColPins[4] = {50, 51, 52, 53};
Keypad keypad = Keypad(makeKeymap(keyMap), keypadRowPins, keypadColPins, 4, 4);

SevSeg passDisplay;
#if !BLUETOOTH_ON_SERIAL0
SoftwareSerial bluetooth(Pins::BT_RX, Pins::BT_TX);
#endif

MasterState state = STATE_WAIT_PASSCODE_SETUP;
MasterState nextState = STATE_ENTER_PASS_FIRST;
unsigned long stateStartMs = 0;
unsigned long messageDurationMs = 0;
bool systemOn = false;
bool returnToSystemOn = false;
bool showOffScreen = false;
int16_t preferredTempTenths = 0;

#include "Interrupts.h"
#include "TemperatureConversion.h"
#include "AccessControl.h"
#include "SystemCommunication.h"
#include "BluetoothCommunication.h"
#include "Reset/reset.h"

// Shows a 2-line message on LCD1 for a set time, then moves to the 'after' state
void showMessage(const __FlashStringHelper* line1, const __FlashStringHelper* line2,
                 unsigned long ms, MasterState after) {

  lcd1.clear();
  lcd1.print(line1);
  lcd1.setCursor(0, 1);
  lcd1.print(line2);
  nextState = after;
  messageDurationMs = ms;
  state = STATE_MESSAGE;
  stateStartMs = millis();

}

// Draws the waiting screen: preferred temperature (or "System is OFF") + "Press PB1 to ON"
void drawReadyScreen() {
 
  lcd1.clear();
  if (showOffScreen) lcd1.print(F("System is OFF"));
  else printCelsiusAndFahrenheit(lcd1, preferredTempTenths);
  lcd1.setCursor(0, 1);
  lcd1.print(F("Press PB1 to ON"));

}

// Switches to a new state and draws that state's screen on LCD1
void enterState(MasterState s) {
  
  state = s;
  stateStartMs = millis();
  
  switch (s) {
    
    case STATE_WAIT_PASSCODE_SETUP:
      lcd1.clear();
      lcd1.print(F("No passcode yet"));
      lcd1.setCursor(0, 1);
      lcd1.print(F("Set in Node-RED"));
      break;
    
    case STATE_ENTER_PASS_FIRST:
      beginEntry(F("Enter passcode:"), PASSCODE_LENGTH, true);
      break;
    
    case STATE_ENTER_PASS_SECOND:
      beginEntry(F("Confirm passcode"), PASSCODE_LENGTH, true);
      break;
    
    case STATE_FLASH_PASSCODE:
      startPasscodeFlash();
      break;
    
    case STATE_ENTER_TEMPERATURE:
      showOffScreen = false;
      beginEntry(F("Room temp 3dig #"), 3, false);
      break;
    
    case STATE_WAIT_FOR_PB1:
      drawReadyScreen();
      break;
    
    case STATE_SYSTEM_ON:
      lcd1.clear();
      lcd1.print(F("System is ON"));
      lcd1.setCursor(0, 1);
      lcd1.print(F("Press PB1 to OFF"));
      break;
    
    default:
      break;
  
  }
  
  pb1Flag = false;

}

// Saves the new preferred temperature and sends it to MEGA-2
void applyPreferredTemperature(int16_t tenths) {
  
  preferredTempTenths = tenths;
  sendSyncToSlave();

}

// Turns the system ON, tells MEGA-2, and shows the ON screen
void turnSystemOn() {
  
  systemOn = true;
  sendSyncToSlave();
  enterState(STATE_SYSTEM_ON);

}

// Turns the system OFF, tells MEGA-2, and goes back to the waiting screen
void turnSystemOff() {

  systemOn = false;
  sendSyncToSlave();
  showOffScreen = true;
  enterState(STATE_WAIT_FOR_PB1);

}

// Runs once at power-up: sets up pins, Serial, I2C, LCD, 7-segment, interrupts, Bluetooth, passcode
void setup() {
  
  pinMode(Pins::ACTIVE_BUZZER, OUTPUT);
  digitalWrite(Pins::ACTIVE_BUZZER, LOW);

  Serial.begin(USB_BAUD);
  Wire.begin();
  Wire.setWireTimeout(25000, true);
  lcd1.begin(16, 2);

  const uint8_t digitPins[4]   = {Pins::DIG_1, Pins::DIG_2, Pins::DIG_3, Pins::DIG_4};
  const uint8_t segmentPins[7] = {Pins::SEG_A, Pins::SEG_B, Pins::SEG_C, Pins::SEG_D,
                                  Pins::SEG_E, Pins::SEG_F, Pins::SEG_G};
  passDisplay.begin(DISPLAY_IS_COMMON_CATHODE ? COMMON_CATHODE : COMMON_ANODE,
                    4, digitPins, segmentPins, RESISTORS_ON_SEGMENTS,
                    false, true, true);
  passDisplay.setBrightness(DISPLAY_BRIGHTNESS);
  passDisplay.blank();

  setupInterrupts();
  setupBluetooth();
  loadPasscode();

  sendResetToSlave();
  sendEvent(FROM_USB, F("BOOT"), passcodeConfigured ? "passcode stored" : "no passcode");

  showMessage(F("Project - Janus"), F("By Vasu and Lam"), TITLE_MS,
              passcodeConfigured ? STATE_ENTER_PASS_FIRST : STATE_WAIT_PASSCODE_SETUP);

}

// Main loop: reads commands, keypad and PB1, runs the current state, and polls MEGA-2
void loop() {
  
  pollUsbSerial();
  pollBluetooth();

  if (resetButtonFlag) { resetButtonFlag = false; resetRequested = true; }
  if (resetRequested) resetAll();

  serviceDisplayShutdown();

  char key = keypad.getKey();
  bool pb1Pressed = pb1Flag;
  pb1Flag = false;

  switch (state) {
    
    case STATE_ENTER_PASS_FIRST:
    case STATE_ENTER_PASS_SECOND:
      if (key) handlePasscodeKey(key);
      break;

    case STATE_FLASH_PASSCODE:
      updatePasscodeFlash();
      break;

    case STATE_ENTER_TEMPERATURE:
      if (key) handleTemperatureKey(key);
      break;

    case STATE_WAIT_FOR_PB1:
      if (pb1Pressed) {
        turnSystemOn();
        sendEvent(FROM_USB, F("OK"), "PB1 pressed - System is ON");
      }
      break;

    case STATE_SYSTEM_ON:
      if (pb1Pressed) {
        turnSystemOff();
        sendEvent(FROM_USB, F("OK"), "PB1 pressed - System is OFF");
      } else if (key == 'A') {
        returnToSystemOn = true;
        enterState(STATE_ENTER_TEMPERATURE);
      }
      break;

    case STATE_MESSAGE:
      if (millis() - stateStartMs >= messageDurationMs) enterState(nextState);
      break;

    default:
      break;
  
  }

  pollSlave();

}
  