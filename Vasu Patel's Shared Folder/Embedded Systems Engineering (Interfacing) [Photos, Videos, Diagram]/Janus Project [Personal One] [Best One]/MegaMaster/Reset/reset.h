// File purpose: Software reset for MEGA-1 – returns both boards to the startup state
// (passcode in EEPROM is kept).

#pragma once
#include "../Configuration.h"

bool resetRequested = false;

// Resets MEGA-1 and MEGA-2 to the start: system OFF, temperature cleared, title screen shown
void resetAll() {
 
  resetRequested = false;
  resetButtonFlag = false;
  pb1Flag = false;

  sendResetToSlave();

  systemOn = false;
  returnToSystemOn = false;
  showOffScreen = false;
  preferredTempTenths = 0;
  slave.valid = false;
  slave.temperatureC = 255;
  slave.humidity = 255;
  slave.motorSeconds = 0;

  digitalWrite(Pins::ACTIVE_BUZZER, LOW);
  setFlashContent(false);
  displayOffAtMs = millis() + 20;

  sendEvent(FROM_USB, F("RESET_DONE"), "");
  bluetooth.print(F("System reset\r\n"));
  showMessage(F("Project - Janus"), F("By Vasu and Lam"), TITLE_MS,
              passcodeConfigured ? STATE_ENTER_PASS_FIRST : STATE_WAIT_PASSCODE_SETUP);

}
