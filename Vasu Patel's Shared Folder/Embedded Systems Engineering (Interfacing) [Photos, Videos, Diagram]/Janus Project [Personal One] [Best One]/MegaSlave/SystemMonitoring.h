// File purpose: System monitoring for MEGA-2 – PIR motion + yellow LEDs, DHT11 temperature/
// humidity, LCD2 text, and the fan-runtime 7-segment display.

#pragma once
#include "Configuration.h"

static unsigned long lastMotionMs = 0;
static uint8_t dhtFailures = 0;

// Reads the PIR, keeps motion active for 3 s after the last trigger, and lights the yellow LEDs
void updateMotion() {
  bool pirHigh = (digitalRead(Pins::PIR) == HIGH);
  if (pirHigh) lastMotionMs = millis();
  motionActive = systemEnabled &&
                 (pirHigh || (lastMotionMs != 0 && millis() - lastMotionMs < MOTION_HOLD_MS));
  for (uint8_t i = 0; i < 4; i++) {
    digitalWrite(Pins::YELLOW_LEDS[i], motionActive ? HIGH : LOW);
  }
}

// Reads the DHT11 every 2 s while motion is detected; marks no reading after 3 failures
void readClimateSensor() {
  static unsigned long lastReadMs = 0;
  if (!motionActive) return;
  if (lastReadMs != 0 && millis() - lastReadMs < DHT_PERIOD_MS) return;
  lastReadMs = millis();

  int t = 0, h = 0;
  pauseDisplayTimer();
  int result = dht11.readTemperatureHumidity(t, h);
  resumeDisplayTimer();

  if (result == 0) {
    temperatureC = t;
    humidity = h;
    dhtFailures = 0;
  } else if (++dhtFailures >= 3) {
    temperatureC = NO_READING;
    humidity = NO_READING;
  }
}

// Writes a line to LCD2 only if the text changed (prevents flicker)
void writeLcdLine(uint8_t row, const char* text, char* lastText) {
  if (strcmp(text, lastText) == 0) return;
  strcpy(lastText, text);
  lcd2.setCursor(0, row);
  lcd2.print(text);
  for (uint8_t i = strlen(text); i < 16; i++) lcd2.print(' ');
}

static char lcdLast0[17] = "";
static char lcdLast1[17] = "";

// Clears the saved LCD text so both lines are redrawn next time
void clearLcdCache() { lcdLast0[0] = '\0'; lcdLast1[0] = '\0'; }

// Updates LCD2: target temperature (or Night Mode) on line 1, room temperature on line 2
void updateLcd2() {
  static unsigned long lastDrawMs = 0;
  if (!systemEnabled) return;
  if (millis() - lastDrawMs < LCD_PERIOD_MS) return;
  lastDrawMs = millis();

  char line0[17], line1[17];
  const char deg = (char)223;
  int16_t target = activeTargetTenths();

  if (!nightMode) {
    snprintf(line0, sizeof(line0), "Set %d.%d%cC", target / 10, target % 10, deg);
  } else if (target % 10 == 0) {
    snprintf(line0, sizeof(line0), "Night Mode %d%cC", target / 10, deg);
  } else {
    snprintf(line0, sizeof(line0), "Night Mode %d.%dC", target / 10, target % 10);
  }

  if (temperatureC == NO_READING) {
    snprintf(line1, sizeof(line1), "Room Temp --%cC", deg);
  } else {
    snprintf(line1, sizeof(line1), "Room Temp %d%cC", temperatureC, deg);
  }
  writeLcdLine(0, line0, lcdLast0);
  writeLcdLine(1, line1, lcdLast1);
}

// Shows the fan runtime in seconds on the 7-segment (blank when system is OFF)
void updateRuntimeDisplay() {
  static long shown = -1;
  long seconds = systemEnabled ? (long)((motorOnMs / 1000UL) % 10000UL) : -1;
  if (seconds == shown) return;
  shown = seconds;
  pauseDisplayTimer();
  if (seconds < 0) runtimeDisplay.blank();
  else runtimeDisplay.setNumber(seconds);
  resumeDisplayTimer();
}
