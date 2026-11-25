// File purpose: I2C communication for MEGA-2 – receives commands from MEGA-1, sends back
// the 9-value telemetry string, and provides a Serial Monitor test mode.

#pragma once
#include "Configuration.h"

static volatile char i2cMessage[33];
static volatile bool i2cMessageReady = false;
static volatile bool i2cResetRequested = false;
static char telemetry[32] = "255,255,0,0,0,0,0,0,0";

// I2C receive ISR: copies the master's message into a buffer (handled later in loop)
void onI2CReceive(int count) {
  (void)count;
  char buf[33];
  uint8_t n = 0;
  while (Wire.available()) {
    char c = (char)Wire.read();
    if (n < 32) buf[n++] = c;
  }
  buf[n] = '\0';
  if (strcmp(buf, "RESET") == 0) {
    i2cResetRequested = true;
    return;
  }
  for (uint8_t i = 0; i <= n; i++) i2cMessage[i] = buf[i];
  i2cMessageReady = true;
}

// I2C request ISR: sends the telemetry string to MEGA-1
void onI2CRequest() {
  Wire.write((const uint8_t*)telemetry, strlen(telemetry) + 1);
}

// Starts I2C as a slave at address 8 and registers the receive/request handlers
void setupI2C() {
  Wire.begin(I2C_ADDRESS);
  Wire.onReceive(onI2CReceive);
  Wire.onRequest(onI2CRequest);
}

// Handles a master message: RESET, or "S:<on>,<temp>" to set ON/OFF and the target temperature
void handleMasterMessage(const char* msg) {
  lastMasterMessageMs = millis();
  if (strcmp(msg, "RESET") == 0) { resetSlaveState(); return; }
  if (msg[0] == 'S' && msg[1] == ':') {
    bool on = (msg[2] == '1');
    const char* comma = strchr(msg, ',');
    if (comma != NULL) {
      int tenths = atoi(comma + 1);
      if (tenths >= 100 && tenths <= 400) preferredTempTenths = tenths;
    }
    if (on && !systemEnabled) enableSystem();
    else if (!on && systemEnabled) disableSystem();
  }
}

// Safely copies the received I2C message out of the ISR buffer and handles it
void processI2CMessages() {
  if (i2cResetRequested) {
    i2cResetRequested = false;
    handleMasterMessage("RESET");
  }
  if (!i2cMessageReady) return;

  char msg[33];
  noInterrupts();
  for (uint8_t i = 0; i < sizeof(msg); i++) {
    msg[i] = i2cMessage[i];
    if (msg[i] == '\0') break;
  }
  msg[32] = '\0';
  i2cMessageReady = false;
  interrupts();
  handleMasterMessage(msg);
}

// Lets you test MEGA-2 from the Serial Monitor (STATUS or S:1,235)
void pollSerialTest() {
  static char line[33];
  static uint8_t len = 0;
  while (Serial.available()) {
    char c = (char)Serial.read();
    if (c == '\n' || c == '\r') {
      if (len == 0) continue;
      line[len] = '\0';
      len = 0;
      if (strcmp(line, "STATUS") == 0) {
        Serial.print(F("temp,hum,motor,night,motion,alert,target,seconds,light = "));
        Serial.println(telemetry);
      } else {
        handleMasterMessage(line);
        Serial.print(F("OK ")); Serial.println(line);
      }
    } else if (len < sizeof(line) - 1) {
      line[len++] = toupper(c);
    }
  }
}

// Builds the CSV telemetry string that MEGA-1 reads over I2C
void refreshTelemetry() {
  char buf[32];
  snprintf(buf, sizeof(buf), "%d,%d,%d,%d,%d,%d,%d,%u,%d",
           temperatureC, humidity, motorRunning ? 1 : 0, nightMode ? 1 : 0,
           motionActive ? 1 : 0, safetyAlert ? 1 : 0, activeTargetTenths(),
           (unsigned int)((motorOnMs / 1000UL) % 10000UL), lightLevel);
  noInterrupts();
  strcpy(telemetry, buf);
  interrupts();
}
