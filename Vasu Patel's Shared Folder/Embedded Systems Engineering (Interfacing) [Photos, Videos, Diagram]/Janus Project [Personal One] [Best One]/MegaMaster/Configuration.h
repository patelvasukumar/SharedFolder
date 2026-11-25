// File purpose: Central configuration for MEGA-1 – pin map, constants, system states,
// the SlaveStatus data structure, and shared declarations used by all other files.

#pragma once
#include <Arduino.h>
#include <Wire.h>
#include <EEPROM.h>
#include <LiquidCrystal.h>
#include <Keypad.h>
#include <SevSeg.h>
#include <SoftwareSerial.h>

// Pin assignments for every component connected to MEGA-1

namespace Pins {
 
  const uint8_t LCD_RS = 22;
  const uint8_t LCD_EN = 23;
  const uint8_t LCD_D4 = 24;
  const uint8_t LCD_D5 = 25;
  const uint8_t LCD_D6 = 26;
  const uint8_t LCD_D7 = 27;

  const uint8_t ACTIVE_BUZZER = 13;

  const uint8_t PB1_PIN = 18;

  const uint8_t RESET_BUTTON = 14;

  const uint8_t BT_RX = A14;
  const uint8_t BT_TX = A15;

  const uint8_t SEG_A = 12, SEG_B = 11, SEG_C = 10, SEG_D = 9;
  const uint8_t SEG_E = 8,  SEG_F = 7,  SEG_G = 6;
  const uint8_t DIG_1 = 5,  DIG_2 = 4,  DIG_3 = 3,  DIG_4 = 2;

}

#define BLUETOOTH_ON_SERIAL0 0

const uint8_t  SLAVE_I2C_ADDRESS   = 8;
const unsigned long USB_BAUD       = 115200;
const unsigned long BT_BAUD        = 9600;

const bool    DISPLAY_IS_COMMON_CATHODE = true;
const bool    RESISTORS_ON_SEGMENTS     = false;
const int16_t DISPLAY_BRIGHTNESS        = 40;

const int     PASSCODE_EEPROM_ADDR = 0;
const uint8_t PASSCODE_MARKER      = 0xA5;
const uint8_t PASSCODE_LENGTH      = 4;

const int16_t MIN_TEMP_TENTHS = 100;
const int16_t MAX_TEMP_TENTHS = 400;

const unsigned long FLASH_TOTAL_MS     = 5000;
const unsigned long FLASH_INTERVAL_MS  = 1000;
const unsigned long BUZZER_ON_MS       = 1000;
const unsigned long MESSAGE_MS         = 1600;
const unsigned long TITLE_MS           = 15000;
const unsigned long TELEMETRY_MS       = 1000;
const unsigned long RESET_DEBOUNCE_MS  = 30;
const unsigned long PB1_DEBOUNCE_MS    = 250;

enum MasterState : uint8_t {
  STATE_WAIT_PASSCODE_SETUP,
  STATE_ENTER_PASS_FIRST,
  STATE_ENTER_PASS_SECOND,
  STATE_FLASH_PASSCODE,
  STATE_ENTER_TEMPERATURE,
  STATE_WAIT_FOR_PB1,
  STATE_SYSTEM_ON,
  STATE_MESSAGE
};

enum CommandSource : uint8_t { FROM_USB, FROM_BLUETOOTH };

struct SlaveStatus {
  bool     valid;
  int      temperatureC;
  int      humidity;
  bool     motorOn;
  bool     nightMode;
  bool     motion;
  bool     alert;
  int      targetTenths;
  unsigned int motorSeconds;
  int      light;
};

extern LiquidCrystal lcd1;
extern Keypad keypad;
extern SevSeg passDisplay;

#if BLUETOOTH_ON_SERIAL0
  #define bluetooth Serial
#else
  extern SoftwareSerial bluetooth;
#endif

extern MasterState state;
extern MasterState nextState;
extern unsigned long stateStartMs;
extern unsigned long messageDurationMs;
extern bool systemOn;
extern bool returnToSystemOn;
extern int16_t preferredTempTenths;
extern char storedPasscode[PASSCODE_LENGTH + 1];
extern bool passcodeConfigured;
extern SlaveStatus slave;

extern volatile bool pb1Flag;
extern volatile bool resetButtonFlag;
extern volatile bool displayActive;
extern bool resetRequested;

void enterState(MasterState s);
void showMessage(const __FlashStringHelper* line1, const __FlashStringHelper* line2,
                 unsigned long ms, MasterState after);
void turnSystemOn();
void turnSystemOff();
void applyPreferredTemperature(int16_t tenths);
void setupInterrupts();
void pauseDisplayTimer();
void resumeDisplayTimer();
int16_t celsiusTenthsToFahrenheitTenths(int16_t c);
void printTenths(Print& out, int16_t tenths);
void loadPasscode();
bool savePasscode(const char* code);
void sendSyncToSlave();
void sendResetToSlave();
void pollSlave();
void pollUsbSerial();
void executeCommand(char* cmd, CommandSource src);
void sendEvent(CommandSource src, const __FlashStringHelper* event, const char* detail);
void setupBluetooth();
void pollBluetooth();
void resetAll();
