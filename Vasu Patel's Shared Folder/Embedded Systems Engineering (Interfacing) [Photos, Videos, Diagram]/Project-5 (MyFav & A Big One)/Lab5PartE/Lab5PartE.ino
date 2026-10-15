// Student Name: Vasukumar Patel
// Student Number: 041153940

#include "SevSeg.h"
#include <stdlib.h>

SevSeg sevseg;

const byte button1 = 2;
const byte button2 = 3;

volatile bool button1Pressed = false;
volatile bool button2Pressed = false;

const unsigned long countInterval = 100;
const unsigned long timeoutDuration = 10000;
const unsigned long debounceInterval = 50;

bool waitingForInput = true;
bool counting = false;
bool timeoutActive = false;

int multiplier = 0;
int countValue = 0;

unsigned long lastCountTime = 0;
unsigned long timeoutStartTime = 0;
unsigned long lastTimeoutMessageTime = 0;
unsigned long lastButton1Time = 0;
unsigned long lastButton2Time = 0;

char inputBuffer[16];
byte inputLength = 0;

void button1ISR() {
  button1Pressed = true;
}

void button2ISR() {
  button2Pressed = true;
}

void requestInput() {
  
  counting = false;
  waitingForInput = true;
  timeoutActive = false;

  multiplier = 0;
  countValue = 0;
  inputLength = 0;

  sevseg.setNumber(0);

  while (Serial.available() > 0) {
    Serial.read();
  }

  Serial.println();
  Serial.println("Enter a number between 2 and 5:");

}

void setup() {
  
  Serial.begin(9600);

  pinMode(button1, INPUT_PULLUP);
  pinMode(button2, INPUT_PULLUP);

  attachInterrupt(digitalPinToInterrupt(button1),button1ISR,FALLING);

  attachInterrupt(digitalPinToInterrupt(button2),button2ISR,FALLING);

  byte numDigits = 4;

  byte digitPins[] = {6,5,4,52};
  byte segmentPins[] = {13, 12, 11, 10, 9, 8, 7};

  bool resistorsOnSegments = true;
  byte hardwareConfig = COMMON_CATHODE;
  bool updateWithDelays = false;
  bool leadingZeros = true;
  bool disableDecPoint = true;

  sevseg.begin(hardwareConfig,numDigits,digitPins,segmentPins,resistorsOnSegments,updateWithDelays,leadingZeros,disableDecPoint);

  sevseg.setBrightness(50);
  sevseg.setNumber(0);

  requestInput();

}

void handleSerialInput() {
  
  while (Serial.available() > 0 && waitingForInput && !timeoutActive) {

    char c = Serial.read();

    if (c == '\r' || c == '\n') {
      
      if (inputLength == 0) {
        continue;
      }

      inputBuffer[inputLength] = '\0';

      char *endPointer;
      long enteredNumber = strtol(inputBuffer, &endPointer, 10);

      while (*endPointer == ' ' || *endPointer == '\t') {
        endPointer++;
      }

      if (endPointer == inputBuffer || *endPointer != '\0' || enteredNumber < 2 || enteredNumber > 5) {

        Serial.println("Invalid input. Enter a number between 2 and 5:");

        inputLength = 0;
      } else {
       
        multiplier = enteredNumber;
        countValue = multiplier;

        counting = true;
        waitingForInput = false;

        lastCountTime = millis();

        sevseg.setNumber(countValue);

        Serial.print("Counting multiples of ");
        Serial.println(multiplier);

        inputLength = 0;
      
      }
    
    } else if (inputLength < sizeof(inputBuffer) - 1) {
 
      inputBuffer[inputLength++] = c;
 
    }
 
  }

}

void loop() {
  
  bool resetRequest;
  bool timeoutRequest;

  noInterrupts();

  resetRequest = button1Pressed;
  timeoutRequest = button2Pressed;

  button1Pressed = false;
  button2Pressed = false;

  interrupts();

  unsigned long currentTime = millis();

  if (resetRequest && currentTime - lastButton1Time >= debounceInterval) {

    lastButton1Time = currentTime;
    requestInput();
  
  } else if (timeoutRequest && currentTime - lastButton2Time >= debounceInterval) {

    lastButton2Time = currentTime;

    timeoutActive = true;
    timeoutStartTime = currentTime;
    lastTimeoutMessageTime = currentTime;

    Serial.println("Button 2 pressed: 10-second timeout started.");
    Serial.println("The counter will reset after 10 seconds.");
  
  }

  currentTime = millis();

  if (timeoutActive) { 
    
    unsigned long elapsedTime = currentTime - timeoutStartTime;

    if (elapsedTime >= timeoutDuration) {
    
      Serial.println("Timeout complete. Resetting counter.");
      requestInput();
    
    } else if (currentTime - lastTimeoutMessageTime >= 1000) {
      
      int secondsRemaining = 10 - (elapsedTime / 1000);

      Serial.print("Seconds remaining: ");
      Serial.println(secondsRemaining);

      lastTimeoutMessageTime = currentTime;
    
    }
  
  }

  if (waitingForInput && !timeoutActive) { handleSerialInput(); }

  if (counting && currentTime - lastCountTime >= countInterval) {

    lastCountTime = currentTime;

    countValue += multiplier;

    if (countValue > 9999) {
      countValue = multiplier;
    }

    sevseg.setNumber(countValue);
  
  }

  sevseg.refreshDisplay();

}