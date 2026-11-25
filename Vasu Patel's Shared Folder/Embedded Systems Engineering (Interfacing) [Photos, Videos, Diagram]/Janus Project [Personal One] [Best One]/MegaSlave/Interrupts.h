// File purpose: Timer1 interrupt for MEGA-2 – refreshes the fan-runtime 7-segment display every 1 ms.

#pragma once
#include "Configuration.h"

// Timer1 ISR every 1 ms: refreshes the multiplexed 7-segment display
ISR(TIMER1_COMPA_vect) {
  runtimeDisplay.refreshDisplay();
}

// Stops/starts the display interrupt (used during DHT11 reads and display updates)
void pauseDisplayTimer()  { TIMSK1 &= ~(1 << OCIE1A); }
void resumeDisplayTimer() { TIMSK1 |=  (1 << OCIE1A); }

// Configures Timer1 in CTC mode at 1 kHz
void setupDisplayTimer() {

  noInterrupts();
  TCCR1A = 0;
  TCCR1B = 0;
  TCNT1  = 0;
  OCR1A  = 249;
  TCCR1B = (1 << WGM12) | (1 << CS11) | (1 << CS10);
  TIMSK1 |= (1 << OCIE1A);
  interrupts();

}
