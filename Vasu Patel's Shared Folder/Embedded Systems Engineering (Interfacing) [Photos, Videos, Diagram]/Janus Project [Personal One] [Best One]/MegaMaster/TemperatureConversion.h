// File purpose: Temperature helper functions – Celsius to Fahrenheit, printing tenths
// values (23.5), and converting typed digits into a temperature.

#pragma once
#include "Configuration.h"

// Converts Celsius tenths to Fahrenheit tenths using integer math (with rounding)
int16_t celsiusTenthsToFahrenheitTenths(int16_t c) {
 
  int32_t scaled = (int32_t)c * 9;
  scaled += (scaled >= 0) ? 2 : -2;
  return (int16_t)(scaled / 5 + 320);

}

// Prints a tenths value as a decimal number, e.g. 235 -> "23.5"
void printTenths(Print& out, int16_t tenths) {
 
  if (tenths < 0) { out.print('-'); tenths = -tenths; }
  out.print(tenths / 10);
  out.print('.');
  out.print(tenths % 10);

}

// Converts exactly 3 digit characters into a number (e.g. "235" -> 235), -1 if invalid
int16_t digitsToTenths(const char* digits) {
 
  if (strlen(digits) != 3) return -1;
  for (uint8_t i = 0; i < 3; i++) if (!isDigit(digits[i])) return -1;
  return (int16_t)atoi(digits);

}

// Prints the temperature in both °C and °F on the LCD
void printCelsiusAndFahrenheit(LiquidCrystal& lcd, int16_t tenths) {

  printTenths(lcd, tenths);
  lcd.print((char)223); lcd.print('C');
  lcd.print(' ');
  printTenths(lcd, celsiusTenthsToFahrenheitTenths(tenths));
  lcd.print((char)223); lcd.print('F');

}
