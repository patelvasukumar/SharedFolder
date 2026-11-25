// File purpose: Climate control for MEGA-2 – picks the target temperature, controls the
// fan motor through the motor driver, and counts fan runtime.

#pragma once
#include "Configuration.h"

static unsigned long motorLastTickMs = 0;

// Returns the target temperature: 22.0 °C in night mode, otherwise the user's preferred temp
int16_t activeTargetTenths() {
  return nightMode ? NIGHT_TARGET_TENTHS : preferredTempTenths;
}

// Turns the fan motor on/off through the motor driver (IN1/IN2 = direction, EN = enable)
void setMotor(bool on) {
  if (on == motorRunning) return;
  if (on) {
    digitalWrite(Pins::MOTOR_IN1, HIGH);
    digitalWrite(Pins::MOTOR_IN2, LOW);
    digitalWrite(Pins::MOTOR_EN, MOTOR_SPEED > 127 ? HIGH : LOW);
    motorLastTickMs = millis();
  } else {
    digitalWrite(Pins::MOTOR_EN, LOW);
    digitalWrite(Pins::MOTOR_IN1, LOW);
    digitalWrite(Pins::MOTOR_IN2, LOW);
  }
  motorRunning = on;
}

// Adds the time the motor has been running to motorOnMs
void updateMotorRuntime() {
  unsigned long now = millis();
  if (motorRunning) motorOnMs += now - motorLastTickMs;
  motorLastTickMs = now;
}

// Fan is needed when system is ON, motion is detected, and room temp is above the target
bool fanIsNeeded() {
  if (!systemEnabled || !motionActive || temperatureC == NO_READING) return false;
  return (int16_t)(temperatureC * 10) > activeTargetTenths();
}
