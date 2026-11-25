// File purpose: Central configuration for MEGA-2 – pin map, thresholds, timings,
// and shared variable/function declarations used by all other slave files.

#pragma once
#include <Arduino.h>
#include <Wire.h>
#include <LiquidCrystal.h>
#include <SevSeg.h>
#include <DHT11.h>

namespace Pins {
  const uint8_t LCD_RS = 22, LCD_EN = 23;
  const uint8_t LCD_D4 = 24, LCD_D5 = 25, LCD_D6 = 26, LCD_D7 = 27;

  const uint8_t SEG_A = 12, SEG_B = 11, SEG_C = 10, SEG_D = 9;
  const uint8_t SEG_E = 8,  SEG_F = 7,  SEG_G = 6;
  const uint8_t DIG_1 = 5,  DIG_2 = 4,  DIG_3 = 3,  DIG_4 = 2;

  const uint8_t PIR = A0;
  const uint8_t DHT_DATA = 13;

  const uint8_t YELLOW_LEDS[4] = {53, 52, 51, 50};

  const uint8_t US_TRIG = A13;
  const uint8_t US_ECHO = A14;
  const uint8_t PASSIVE_BUZZER = A15;
  const uint8_t PHOTOCELL = A8;

  const uint8_t RED_LEDS = 35;

  const uint8_t MOTOR_EN  = A7;
  const uint8_t MOTOR_IN1 = A6;
  const uint8_t MOTOR_IN2 = A5;
}

const uint8_t I2C_ADDRESS = 8;

const bool    DISPLAY_IS_COMMON_CATHODE = true;
const bool    RESISTORS_ON_SEGMENTS     = false;
const int16_t DISPLAY_BRIGHTNESS        = 40;
const bool    RED_LEDS_ACTIVE_LOW       = true;

const int16_t NIGHT_TARGET_TENTHS = 220;

const int LIGHT_NIGHT_BELOW = 15;
const int LIGHT_DAY_ABOVE   = 30;

const int SAFE_DISTANCE_CM   = 20;
const int RESUME_DISTANCE_CM = 22;
const unsigned long US_PERIOD_MS  = 60;
const unsigned long US_TIMEOUT_US = 15000;

const unsigned long DHT_PERIOD_MS    = 2000;
const unsigned long MOTION_HOLD_MS   = 3000;
const unsigned long LIGHT_PERIOD_MS  = 250;
const unsigned long LCD_PERIOD_MS    = 400;
const unsigned long MASTER_TIMEOUT_MS = 5000;
const uint8_t MOTOR_SPEED = 255;

const int NO_READING = 255;

extern LiquidCrystal lcd2;
extern SevSeg runtimeDisplay;
extern DHT11 dht11;

extern bool systemEnabled;
extern int16_t preferredTempTenths;
extern bool nightMode;
extern int lightLevel;
extern bool motionActive;
extern int temperatureC;
extern int humidity;
extern bool motorRunning;
extern bool safetyAlert;
extern unsigned long motorOnMs;
extern unsigned long lastMasterMessageMs;
extern unsigned long systemEnabledAtMs;

void setupDisplayTimer();
void pauseDisplayTimer();
void resumeDisplayTimer();
void setupI2C();
void processI2CMessages();
void handleMasterMessage(const char* msg);
void pollSerialTest();
void refreshTelemetry();
void setMotor(bool on);
void updateMotorRuntime();
void updateFanControl();
void stopAlarm();
void updateNightMode();
void setRedLeds(bool on);
void updateMotion();
void readClimateSensor();
void updateLcd2();
void updateRuntimeDisplay();
int16_t activeTargetTenths();
void enableSystem();
void disableSystem();
void resetSlaveState();
