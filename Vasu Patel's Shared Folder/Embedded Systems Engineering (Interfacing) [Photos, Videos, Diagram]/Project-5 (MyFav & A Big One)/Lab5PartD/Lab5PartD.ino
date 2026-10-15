// Student Name: Vasukumar Patel
// Student Number: 041153940

#include "SevSeg.h"

SevSeg sevseg;

const byte evenSwitch = 2;
const byte oddSwitch = 3;

volatile bool evenPressed = false;
volatile bool oddPressed = false;

int countValue = 0;
int mode = 0;

unsigned long lastCountTime = 0;
unsigned long lastButtonTime = 0;

const unsigned long countInterval = 500;
const unsigned long debounceInterval = 50;

bool firstPress = true;

void evenISR() { 
  evenPressed = true;
}

void oddISR() {
  oddPressed = true;
}

void setup() {
  
  pinMode(evenSwitch, INPUT_PULLUP);
  pinMode(oddSwitch, INPUT_PULLUP);

  attachInterrupt(digitalPinToInterrupt(evenSwitch), evenISR, FALLING);
  attachInterrupt(digitalPinToInterrupt(oddSwitch), oddISR, FALLING);

  byte numDigits = 1;
  byte digitPins[] = {};
  byte segmentPins[] = {22,23,24,25,26,27,28};
  bool resistorsOnSegments = true;
  byte hardwareConfig = COMMON_CATHODE;

  sevseg.begin(hardwareConfig,numDigits,digitPins,segmentPins,resistorsOnSegments);

  sevseg.setBrightness(90);
  sevseg.setNumber(countValue);

  lastCountTime = millis();

}

void loop() {
  
  bool selectEven;
  bool selectOdd;

  noInterrupts();
  selectEven = evenPressed;
  selectOdd = oddPressed;

  evenPressed = false;
  oddPressed = false;
  interrupts();

  unsigned long currentTime = millis();

  if (selectEven && (firstPress || currentTime - lastButtonTime >= debounceInterval)) {

    mode = 0;
    countValue = 0;

    sevseg.setNumber(countValue);

    lastButtonTime = currentTime;
    lastCountTime = currentTime;
    firstPress = false;
  
  } else if (selectOdd && (firstPress || currentTime - lastButtonTime >= debounceInterval)) {

    mode = 1;
    countValue = 1;

    sevseg.setNumber(countValue);

    lastButtonTime = currentTime;
    lastCountTime = currentTime;
    firstPress = false;
  }

  currentTime = millis();

  if (currentTime - lastCountTime >= countInterval) {
    
    if (mode == 0) {
      
      countValue += 2;

      if (countValue > 8) {
        countValue = 0;
      }
    
    } else {
     
      countValue += 2;

      if (countValue > 9) {
        countValue = 1;
      }
    
    }

    sevseg.setNumber(countValue);
    lastCountTime = currentTime;
  
  }

  sevseg.refreshDisplay();

}