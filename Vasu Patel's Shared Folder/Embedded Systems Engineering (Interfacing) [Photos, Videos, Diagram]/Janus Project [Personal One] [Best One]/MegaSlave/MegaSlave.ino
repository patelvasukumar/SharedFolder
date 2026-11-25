/*
Project - Janus by Vasu and Lam
*/

// File purpose: Main sketch for MEGA-2 (Slave). Creates hardware objects and global
// variables, turns the system on/off, and runs all sensor/actuator updates in the loop.

#include "Configuration.h"

LiquidCrystal lcd2(Pins::LCD_RS, Pins::LCD_EN, Pins::LCD_D4, Pins::LCD_D5, Pins::LCD_D6, Pins::LCD_D7);
SevSeg runtimeDisplay;
DHT11 dht11(Pins::DHT_DATA);

bool systemEnabled = false;
int16_t preferredTempTenths = 235;
bool nightMode = false;
int lightLevel = 0;
bool motionActive = false;
int temperatureC = NO_READING;
int humidity = NO_READING;
bool motorRunning = false;
bool safetyAlert = false;
unsigned long motorOnMs = 0;
unsigned long lastMasterMessageMs = 0;
unsigned long systemEnabledAtMs = 0;

#include "Interrupts.h"
#include "ClimateControl.h"
#include "FanSafetyManagement.h"
#include "NightControl.h"
#include "SystemMonitoring.h"
#include "SystemCommunication.h"

// Turns the system ON: records the start time and turns on LCD2
void enableSystem() {
 
  systemEnabled = true;
  systemEnabledAtMs = millis();
  lcd2.display();
  lcd2.clear();
  clearLcdCache();

}

// Turns the system OFF: stops motor, alarm and LEDs, and blanks LCD2
void disableSystem() {
 
  systemEnabled = false;
  setMotor(false);
  stopAlarm();
  safetyAlert = false;
  motionActive = false;
  for (uint8_t i = 0; i < 4; i++) digitalWrite(Pins::YELLOW_LEDS[i], LOW);
  setRedLeds(false);
  lcd2.clear();
  lcd2.noDisplay();
  clearLcdCache();

}

// Resets MEGA-2 to defaults (OFF, 23.5 °C target, no readings, runtime 0)
void resetSlaveState() {
 
  disableSystem();
  preferredTempTenths = 235;
  temperatureC = NO_READING;
  humidity = NO_READING;
  nightMode = false;
  motorOnMs = 0;

}

// Runs once: sets up pins, Serial, DHT11, LCD2, 7-segment, display timer and I2C
void setup() {
  
  pinMode(Pins::PIR, INPUT);
  pinMode(Pins::US_TRIG, OUTPUT);
  pinMode(Pins::US_ECHO, INPUT);
  pinMode(Pins::PASSIVE_BUZZER, OUTPUT);
  pinMode(Pins::MOTOR_EN, OUTPUT);
  pinMode(Pins::MOTOR_IN1, OUTPUT);
  pinMode(Pins::MOTOR_IN2, OUTPUT);
  pinMode(Pins::RED_LEDS, OUTPUT);
  for (uint8_t i = 0; i < 4; i++) pinMode(Pins::YELLOW_LEDS[i], OUTPUT);
  digitalWrite(Pins::MOTOR_EN, LOW);
  setRedLeds(false);

  Serial.begin(115200);
  dht11.setDelay(0);
  lcd2.begin(16, 2);

  const uint8_t digitPins[4]   = {Pins::DIG_1, Pins::DIG_2, Pins::DIG_3, Pins::DIG_4};
  const uint8_t segmentPins[7] = {Pins::SEG_A, Pins::SEG_B, Pins::SEG_C, Pins::SEG_D,Pins::SEG_E, Pins::SEG_F, Pins::SEG_G};
  runtimeDisplay.begin(DISPLAY_IS_COMMON_CATHODE ? COMMON_CATHODE : COMMON_ANODE,
                       4, digitPins, segmentPins, RESISTORS_ON_SEGMENTS,
                       false, false, true);
  runtimeDisplay.setBrightness(DISPLAY_BRIGHTNESS);
  runtimeDisplay.blank();
  setupDisplayTimer();

  resetSlaveState();
  setupI2C();
  Serial.println(F("MEGA-2 ready (I2C address 8)"));

}

// Main loop: handles master messages, safety timeout, then updates every sensor and output
void loop() {
 
  processI2CMessages();
  pollSerialTest();

  if (systemEnabled && millis() - lastMasterMessageMs > MASTER_TIMEOUT_MS) {
    disableSystem();
  }

  updateNightMode();
  updateMotion();
  readClimateSensor();
  updateFanControl();
  updateMotorRuntime();
  updateRuntimeDisplay();
  updateLcd2();
  refreshTelemetry();

}
