// File purpose: All communication for MEGA-1 – I2C with MEGA-2, JSON telemetry/events to
// Node-RED over USB, status to the phone over Bluetooth, and the command parser.

#pragma once
#include "Configuration.h"
#include "TemperatureConversion.h"

SlaveStatus slave = { false, 255, 255, false, false, false, false, 0, 0, 0 };
static unsigned long lastTelemetryMs = 0;

// Sends a text message to MEGA-2 over I2C
void sendI2CText(const char* text) {

  Wire.beginTransmission(SLAVE_I2C_ADDRESS);
  Wire.write((const uint8_t*)text, strlen(text));
  Wire.endTransmission();

}

// Sends "S:<on>,<tempTenths>" to MEGA-2 so it knows the system state and target temperature
void sendSyncToSlave() {
 
  char msg[16];
  snprintf(msg, sizeof(msg), "S:%d,%d", systemOn ? 1 : 0, preferredTempTenths);
  sendI2CText(msg);

}

// Tells MEGA-2 to reset itself
void sendResetToSlave() { sendI2CText("RESET"); }

// Requests the 9-value CSV status from MEGA-2 and stores it in 'slave'; false if invalid
bool readSlaveStatus() {
  
  char buf[33];
  uint8_t n = 0;
  uint8_t received = Wire.requestFrom((uint8_t)SLAVE_I2C_ADDRESS, (uint8_t)32);
  
  while (Wire.available()) {
    char c = (char)Wire.read();
    if (c == '\0' || (uint8_t)c == 0xFF) break;
    if (n < 32) buf[n++] = c;
  }
  
  while (Wire.available()) Wire.read();
  buf[n] = '\0';
  if (received == 0 || n == 0) return false;

  int v[9];
  uint8_t count = 0;
  char* token = strtok(buf, ",");
  
  while (token != NULL && count < 9) {
    v[count++] = atoi(token);
    token = strtok(NULL, ",");
  }
  
  if (count != 9) return false;

  slave.temperatureC = v[0];
  slave.humidity     = v[1];
  slave.motorOn      = v[2] != 0;
  slave.nightMode    = v[3] != 0;
  slave.motion       = v[4] != 0;
  slave.alert        = v[5] != 0;
  slave.targetTenths = v[6];
  slave.motorSeconds = (unsigned int)v[7];
  slave.light        = v[8];
  slave.valid        = true;
  return true;

}

// Returns the current state as text for Node-RED / the phone (LOCKED, READY, ON...)
const __FlashStringHelper* stateName() {
  
  switch (state) {
  
    case STATE_WAIT_PASSCODE_SETUP: return F("NO_PASSCODE");
    case STATE_ENTER_PASS_FIRST:
    case STATE_ENTER_PASS_SECOND:   return F("LOCKED");
    case STATE_FLASH_PASSCODE:      return F("ACCESS_GRANTED");
    case STATE_ENTER_TEMPERATURE:   return F("SET_TEMPERATURE");
    case STATE_WAIT_FOR_PB1:        return F("READY");
    case STATE_SYSTEM_ON:           return F("ON");
    default:                        return systemOn ? F("ON") : F("BUSY");
  
  }

}

// Prints a sensor value in JSON, or null if there is no reading (255)
void printJsonReading(int value) {
 
  if (value == 255) Serial.print(F("null"));
  else Serial.print(value);

}

// Prints one telemetry JSON line over USB for the Node-RED dashboard
void printTelemetryJson() {

  Serial.print(F("{\"type\":\"telemetry\",\"state\":\""));
  Serial.print(stateName());
  Serial.print(F("\",\"system\":"));       Serial.print(systemOn ? 1 : 0);
  Serial.print(F(",\"link\":"));           Serial.print(slave.valid ? 1 : 0);
  Serial.print(F(",\"temperature\":"));    printJsonReading(slave.temperatureC);
  Serial.print(F(",\"humidity\":"));       printJsonReading(slave.humidity);
  Serial.print(F(",\"motor\":"));          Serial.print(slave.motorOn ? 1 : 0);
  Serial.print(F(",\"night\":"));          Serial.print(slave.nightMode ? 1 : 0);
  Serial.print(F(",\"motion\":"));         Serial.print(slave.motion ? 1 : 0);
  Serial.print(F(",\"alert\":"));          Serial.print(slave.alert ? 1 : 0);
  Serial.print(F(",\"preferred\":"));      printTenths(Serial, preferredTempTenths);
  Serial.print(F(",\"target\":"));         printTenths(Serial, slave.targetTenths);
  Serial.print(F(",\"motorSeconds\":"));   Serial.print(slave.motorSeconds);
  Serial.print(F(",\"light\":"));          Serial.print(slave.light);
  Serial.print(F(",\"passcodeSet\":"));    Serial.print(passcodeConfigured ? 1 : 0);
  Serial.println('}');

}

// Every 1 s: sync with MEGA-2, read its status, and send telemetry to Node-RED
void pollSlave() {
 
  if (millis() - lastTelemetryMs < TELEMETRY_MS) return;
  lastTelemetryMs = millis();
  sendSyncToSlave();
  if (!readSlaveStatus()) slave.valid = false;
  printTelemetryJson();

}

// Sends an event as JSON (USB/Node-RED) or plain text (Bluetooth/phone)
void sendEvent(CommandSource src, const __FlashStringHelper* event, const char* detail) {
  
  if (src == FROM_USB) {
    Serial.print(F("{\"type\":\"event\",\"event\":\""));
    Serial.print(event);
    Serial.print(F("\",\"detail\":\""));
    Serial.print(detail);
    Serial.println(F("\"}"));
  } else {
    bluetooth.print(event);
    if (detail[0] != '\0') { bluetooth.print(F(": ")); bluetooth.print(detail); }
    bluetooth.print(F("\r\n"));
  }

}

// Sends a readable status report to the phone over Bluetooth
void sendBluetoothStatus() {
  
  bluetooth.print(F("State: ")); bluetooth.print(stateName());
  if (!slave.valid) { bluetooth.print(F("\r\nMEGA-2: no link\r\n")); return; }
  bluetooth.print(F("\r\nTemp: "));
  if (slave.temperatureC == 255) bluetooth.print(F("--")); else bluetooth.print(slave.temperatureC);
  bluetooth.print(F(" C  Hum: "));
  if (slave.humidity == 255) bluetooth.print(F("--")); else bluetooth.print(slave.humidity);
  bluetooth.print(F(" %\r\nTarget: ")); printTenths(bluetooth, slave.targetTenths);
  bluetooth.print(F(" C\r\nFan: "));    bluetooth.print(slave.motorOn ? F("ON") : F("OFF"));
  bluetooth.print(F("  Night: "));      bluetooth.print(slave.nightMode ? F("YES") : F("NO"));
  bluetooth.print(F("\r\nMotion: "));   bluetooth.print(slave.motion ? F("YES") : F("NO"));
  bluetooth.print(F("  Alert: "));      bluetooth.print(slave.alert ? F("YES") : F("NO"));
  bluetooth.print(F("\r\nFan time: ")); bluetooth.print(slave.motorSeconds);
  bluetooth.print(F(" s\r\n"));

}

// User is logged in once a preferred temperature has been set
bool isAuthenticated() { return preferredTempTenths > 0; }

// Removes spaces at the start/end and converts the command to uppercase
void normaliseCommand(char* cmd) {
 
  char* start = cmd;
  while (*start == ' ' || *start == '\t') start++;
  if (start != cmd) memmove(cmd, start, strlen(start) + 1);
  int len = strlen(cmd);
  while (len > 0 && (cmd[len - 1] == ' ' || cmd[len - 1] == '\t')) cmd[--len] = '\0';
  for (int i = 0; i < len; i++) cmd[i] = toupper(cmd[i]);

}

// Finds a known command anywhere in the line (ignores junk characters before it)
char* findKnownCommand(char* line) {
 
  static const char* const EXACT[] = {"RESTART", "RESET", "START", "STOP", "STATUS", "HELP", "ON", "OFF"};
  static const char* const PREFIX[] = {"SET_PASS:", "TEMP:"};
  
  for (char* p = line; *p; p++) {
    
    for (uint8_t i = 0; i < sizeof(PREFIX) / sizeof(PREFIX[0]); i++) {
      if (strncmp(p, PREFIX[i], strlen(PREFIX[i])) == 0) return p;
    }
  
    for (uint8_t i = 0; i < sizeof(EXACT) / sizeof(EXACT[0]); i++) {
      if (strcmp(p, EXACT[i]) == 0) return p;
    }
  
  }
 
  return line;

}

// Runs a command received from USB or Bluetooth (SET_PASS, START, STOP, TEMP, STATUS)
void executeCommand(char* rawCmd, CommandSource src) {
 
  normaliseCommand(rawCmd);
  char* cmd = findKnownCommand(rawCmd);
 
  if (cmd[0] == '\0') return;

  if (strncmp(cmd, "SET_PASS:", 9) == 0) {
  
    if (savePasscode(cmd + 9)) {
    
      sendEvent(src, F("PASSCODE_SAVED"), "");
    
      if (src == FROM_BLUETOOTH) sendEvent(FROM_USB, F("PASSCODE_SAVED"), "from phone");
    
      if (state == STATE_WAIT_PASSCODE_SETUP) enterState(STATE_ENTER_PASS_FIRST);
      else if (state == STATE_MESSAGE && nextState == STATE_WAIT_PASSCODE_SETUP)
        nextState = STATE_ENTER_PASS_FIRST;
    
    } else {
      sendEvent(src, F("ERROR"), "Passcode must be 4 digits");
    }
  
  } else if (strcmp(cmd, "RESET") == 0 || strcmp(cmd, "RESTART") == 0) {
  
    sendEvent(src, F("OK"), "RESET");
    resetRequested = true;
  
  } else if (strcmp(cmd, "START") == 0 || strcmp(cmd, "ON") == 0) {
  
    if (state == STATE_WAIT_FOR_PB1 || state == STATE_SYSTEM_ON) {
      turnSystemOn();
      sendEvent(src, F("OK"), "System is ON");
    } else {
      sendEvent(src, F("ERROR"), "Log in on the keypad first");
    }
  
  } else if (strcmp(cmd, "STOP") == 0 || strcmp(cmd, "OFF") == 0) {
  
    if (systemOn) turnSystemOff();
    sendEvent(src, F("OK"), "System is OFF");
  
  } else if (strcmp(cmd, "STATUS") == 0) {

#if BLUETOOTH_ON_SERIAL0
    sendBluetoothStatus();
    printTelemetryJson();

#else

    if (src == FROM_USB) printTelemetryJson(); else sendBluetoothStatus();

#endif
  
  } else if (strncmp(cmd, "TEMP:", 5) == 0) {
   
    int16_t tenths = digitsToTenths(cmd + 5);
   
    if (!isAuthenticated()) {
   
      sendEvent(src, F("ERROR"), "Log in on the keypad first");
   
    } else if (tenths < MIN_TEMP_TENTHS || tenths > MAX_TEMP_TENTHS) {
   
      sendEvent(src, F("ERROR"), "Use 3 digits 100-400, e.g. TEMP:235");
   
    } else {
   
      applyPreferredTemperature(tenths);
      if (state == STATE_WAIT_FOR_PB1) enterState(STATE_WAIT_FOR_PB1);
      sendEvent(src, F("OK"), "Preferred temperature updated");
   
    }
  
  } else if (strcmp(cmd, "HELP") == 0) {
  
    sendEvent(src, F("COMMANDS"), "START STOP RESTART STATUS TEMP:235 SET_PASS:1234");
  
  } else if (src == FROM_BLUETOOTH &&
  
             (strncmp(cmd, "OK", 2) == 0 || strncmp(cmd, "ERR", 3) == 0)) {
  
  } else {
  
    sendEvent(src, F("ERROR"), "Unknown command (send HELP)");
  
  }

}

// Reads USB Serial one character at a time and runs the command when Enter is received
void pollUsbSerial() {
 
  static char line[40];
  static uint8_t len = 0;
 
  while (Serial.available()) {
 
    char c = (char)Serial.read();
 
    if (c == '\n' || c == '\r') {
 
      if (len > 0) { line[len] = '\0'; executeCommand(line, FROM_USB); len = 0; }
 
    } else if (len < sizeof(line) - 1) {
 
      line[len++] = c;
 
    }
 
  }

}
