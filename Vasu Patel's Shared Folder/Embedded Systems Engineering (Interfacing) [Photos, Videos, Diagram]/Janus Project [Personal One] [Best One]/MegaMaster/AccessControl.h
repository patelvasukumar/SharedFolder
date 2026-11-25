// File purpose: Access control for MEGA-1 – saves/loads the passcode in EEPROM, handles
// keypad entry, checks the passcode, flashes it on the 7-segment, and reads the temperature.

#pragma once
#include "Configuration.h"
#include "TemperatureConversion.h"

char storedPasscode[PASSCODE_LENGTH + 1] = "";
bool passcodeConfigured = false;

static char entryText[6] = "";
static uint8_t entryLength = 0;
static uint8_t entryMaxLength = 4;
static bool entryMasked = true;
static char firstPasscode[PASSCODE_LENGTH + 1] = "";

void setFlashContent(bool visible);
extern unsigned long displayOffAtMs;

// Loads the saved passcode from EEPROM (only if the marker byte says one was saved)
void loadPasscode() {

  passcodeConfigured = false;
  storedPasscode[0] = '\0';
  if (EEPROM.read(PASSCODE_EEPROM_ADDR) != PASSCODE_MARKER) return;

  for (uint8_t i = 0; i < PASSCODE_LENGTH; i++) {
    char c = (char)EEPROM.read(PASSCODE_EEPROM_ADDR + 1 + i);
    if (!isDigit(c)) return;
    storedPasscode[i] = c;
  }
  storedPasscode[PASSCODE_LENGTH] = '\0';
  passcodeConfigured = true;

}

// Checks the new passcode is 4 digits and saves it to EEPROM
bool savePasscode(const char* code) {
  
  if (strlen(code) != PASSCODE_LENGTH) return false;
  for (uint8_t i = 0; i < PASSCODE_LENGTH; i++) if (!isDigit(code[i])) return false;
  for (uint8_t i = 0; i < PASSCODE_LENGTH; i++) {
    EEPROM.update(PASSCODE_EEPROM_ADDR + 1 + i, code[i]);
    storedPasscode[i] = code[i];
  }
  EEPROM.update(PASSCODE_EEPROM_ADDR, PASSCODE_MARKER);
  storedPasscode[PASSCODE_LENGTH] = '\0';
  passcodeConfigured = true;
  return true;

}

// Redraws the typed input on LCD line 2 (as * for passcode, digits for temperature)
void redrawEntryLine() {
 
  lcd1.setCursor(0, 1);
  for (uint8_t i = 0; i < 16; i++) {
    if (i < entryLength) lcd1.print(entryMasked ? '*' : entryText[i]);
    else lcd1.print(' ');
  }
  lcd1.setCursor(entryLength, 1);

}

// Starts a new keypad entry: shows the prompt and clears the input
void beginEntry(const __FlashStringHelper* prompt, uint8_t maxLength, bool masked) {

  entryLength = 0;
  entryText[0] = '\0';
  entryMaxLength = maxLength;
  entryMasked = masked;
  lcd1.clear();
  lcd1.print(prompt);
  redrawEntryLine();

}

// Handles one key: digit = add, * = clear, # = submit; returns true when input is complete
bool handleEntryKey(char key) {
 
  if (key >= '0' && key <= '9') {
 
    if (entryLength < entryMaxLength) {
      entryText[entryLength++] = key;
      entryText[entryLength] = '\0';
      redrawEntryLine();
    }
 
  } else if (key == '*') {
  
    entryLength = 0;
    entryText[0] = '\0';
    redrawEntryLine();
  
  } else if (key == '#') {
  
    if (entryLength == entryMaxLength) return true;
  
  }

  return false;

}

// Handles passcode entry: first entry, confirm entry, then compares with the saved passcode
void handlePasscodeKey(char key) {
  
  if (!handleEntryKey(key)) return;

  if (state == STATE_ENTER_PASS_FIRST) {
    strcpy(firstPasscode, entryText);
    enterState(STATE_ENTER_PASS_SECOND);
    return;
  }

  if (strcmp(firstPasscode, entryText) != 0) {

    sendEvent(FROM_USB, F("PASSCODE_MISMATCH"), "");
    showMessage(F("Codes mismatch!"), F("Try again"), MESSAGE_MS, STATE_ENTER_PASS_FIRST);

  } else if (strcmp(entryText, storedPasscode) != 0) {

    sendEvent(FROM_USB, F("PASSCODE_WRONG"), "");
    showMessage(F("Wrong passcode"), F("Try again"), MESSAGE_MS, STATE_ENTER_PASS_FIRST);

  } else {

    sendEvent(FROM_USB, F("ACCESS_GRANTED"), "");
    enterState(STATE_FLASH_PASSCODE);

  }

  firstPasscode[0] = '\0';

}

// Shows "Access granted", starts flashing the passcode and turns the buzzer on
void startPasscodeFlash() {
 
  lcd1.clear();
  lcd1.print(F("Access granted"));
  lcd1.setCursor(0, 1);
  lcd1.print(F("Welcome!"));
  displayOffAtMs = 0;
  setFlashContent(true);
  displayActive = true;
  digitalWrite(Pins::ACTIVE_BUZZER, HIGH);

}

static bool flashVisible = true;
unsigned long displayOffAtMs = 0;

// Shows or blanks the passcode on the 7-segment display
void setFlashContent(bool visible) {

  pauseDisplayTimer();
  if (visible) passDisplay.setChars(storedPasscode);
  else passDisplay.blank();
  resumeDisplayTimer();
  flashVisible = visible;

}

// Flashes the passcode for 5 s (1 s on/off), stops buzzer after 1 s, then asks for temperature
void updatePasscodeFlash() {

  unsigned long elapsed = millis() - stateStartMs;
  if (elapsed >= BUZZER_ON_MS) digitalWrite(Pins::ACTIVE_BUZZER, LOW);

  if (elapsed >= FLASH_TOTAL_MS) {
    digitalWrite(Pins::ACTIVE_BUZZER, LOW);
    setFlashContent(false);
    displayOffAtMs = millis() + 20;
    enterState(STATE_ENTER_TEMPERATURE);
    return;
  }

  bool visible = ((elapsed / FLASH_INTERVAL_MS) % 2) == 0;
  if (visible != flashVisible) setFlashContent(visible);

}

// Turns off 7-segment refreshing once the scheduled shutdown time has passed
void serviceDisplayShutdown() {

  if (displayOffAtMs != 0 && (long)(millis() - displayOffAtMs) >= 0) {
    displayActive = false;
    displayOffAtMs = 0;
  }

}

// Handles temperature entry: checks range 100-400, saves it, then goes to the next screen
void handleTemperatureKey(char key) {

  if (!handleEntryKey(key)) return;
  int16_t tenths = digitsToTenths(entryText);

  if (tenths < MIN_TEMP_TENTHS || tenths > MAX_TEMP_TENTHS) {
    showMessage(F("Use 100 to 400"), F("(10.0 - 40.0 C)"), MESSAGE_MS, STATE_ENTER_TEMPERATURE);
    return;
  }

  applyPreferredTemperature(tenths);

  if (!returnToSystemOn) {
    enterState(STATE_WAIT_FOR_PB1);
    return;
  }

  returnToSystemOn = false;
  lcd1.clear();
  lcd1.print(F("Preferred temp:"));
  lcd1.setCursor(0, 1);
  printCelsiusAndFahrenheit(lcd1, tenths);
  nextState = STATE_SYSTEM_ON;
  messageDurationMs = 2500;
  state = STATE_MESSAGE;
  stateStartMs = millis();

}
