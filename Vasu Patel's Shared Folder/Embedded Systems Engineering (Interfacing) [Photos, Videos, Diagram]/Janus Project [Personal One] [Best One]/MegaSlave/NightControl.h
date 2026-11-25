// File purpose: Night mode for MEGA-2 – reads the photocell, switches day/night mode,
// and controls the red LEDs.

#pragma once
#include "Configuration.h"

// Turns the red LEDs on/off (handles active-low wiring)
void setRedLeds(bool on) {
  bool level = RED_LEDS_ACTIVE_LOW ? !on : on;
  digitalWrite(Pins::RED_LEDS, level ? HIGH : LOW);
}


void updateNightMode() {
  static unsigned long lastReadMs = 0;
  if (millis() - lastReadMs < LIGHT_PERIOD_MS) return;
  lastReadMs = millis();

  long sum = 0;
  for (uint8_t i = 0; i < 4; i++) sum += analogRead(Pins::PHOTOCELL);
  lightLevel = (int)(sum / 4);

  if (!nightMode && lightLevel < LIGHT_NIGHT_BELOW) nightMode = true;
  else if (nightMode && lightLevel > LIGHT_DAY_ABOVE) nightMode = false;

  setRedLeds(systemEnabled && nightMode);
}
