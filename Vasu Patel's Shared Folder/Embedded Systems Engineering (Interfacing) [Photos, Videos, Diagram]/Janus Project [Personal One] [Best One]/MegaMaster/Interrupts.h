// File purpose: Interrupt handling for MEGA-1 – PB1 external interrupt and a 1 ms Timer1
// interrupt that refreshes the 7-segment display and debounces the reset button.

#pragma once
#include "Configuration.h"

volatile bool pb1Flag = false;
volatile bool resetButtonFlag = false;
volatile bool displayActive = false;

// ISR for PB1: sets pb1Flag when pressed (250 ms debounce)
void onPb1Interrupt() {

  static unsigned long lastPressMs = 0;
  unsigned long now = millis();

  if (now - lastPressMs > PB1_DEBOUNCE_MS) {
    pb1Flag = true;
    lastPressMs = now;
  }

}

// Timer1 ISR every 1 ms: refreshes the 7-segment display and checks the reset button
ISR(TIMER1_COMPA_vect) {

  if (displayActive) passDisplay.refreshDisplay();

  static uint8_t lowSamples = 0;
  static bool latched = false;

  if (digitalRead(Pins::RESET_BUTTON) == LOW) {

    if (lowSamples < RESET_DEBOUNCE_MS) lowSamples++;

    if (lowSamples >= RESET_DEBOUNCE_MS && !latched) {
      resetButtonFlag = true;
      latched = true;
    }

  } else {
    lowSamples = 0;
    latched = false;
  }

}

// Stops/starts the Timer1 interrupt (used while changing what the 7-segment shows)
void pauseDisplayTimer()  { TIMSK1 &= ~(1 << OCIE1A); }
void resumeDisplayTimer() { TIMSK1 |=  (1 << OCIE1A); }

// Sets up the PB1 and reset button pins, attaches the PB1 interrupt, and configures Timer1
void setupInterrupts() {
 
  pinMode(Pins::PB1_PIN, INPUT_PULLUP);
  pinMode(Pins::RESET_BUTTON, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(Pins::PB1_PIN), onPb1Interrupt, FALLING);

  noInterrupts();
  TCCR1A = 0;
  TCCR1B = 0;
  TCNT1  = 0;
  OCR1A  = 249;
  TCCR1B = (1 << WGM12) | (1 << CS11) | (1 << CS10);
  TIMSK1 |= (1 << OCIE1A);
  interrupts();

}
