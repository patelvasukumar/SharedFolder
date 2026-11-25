// Student Name: Vasukumar Patel
// Student Number: 041153940

int SER = 21;
int LATCH = 20;
int SRCLK = 19;

const int switch1 = 2;
const int switch2 = 3;

int myDelay = 500;

volatile byte counter = 0;
volatile byte countMode = 0;
volatile bool resetRequested = false;

unsigned long previousMillis = 0;

byte sevenSegmentDisplay[10] = {
  B11111100,  // 0
  B10100000,  // 1 
  B11011010,  // 2
  B11110010,  // 3
  B10100110,  // 4
  B01110110,  // 5
  B01111111,  // 6
  B11100000,  // 7
  B11111111,  // 8
  B11110110   // 9
};

void setup() {
  
  pinMode(SER, OUTPUT);
  pinMode(LATCH, OUTPUT);
  pinMode(SRCLK, OUTPUT);

  pinMode(switch1, INPUT_PULLUP);
  pinMode(switch2, INPUT_PULLUP);

  attachInterrupt(digitalPinToInterrupt(switch1), evenMode, FALLING);
  attachInterrupt(digitalPinToInterrupt(switch2), oddMode, FALLING);

  displayNumber(0);

}

void loop() {
  
  byte displayValue;
  bool reset;

  noInterrupts();
  displayValue = counter;
  reset = resetRequested;
  resetRequested = false;
  interrupts();

  if (reset) {
    previousMillis = millis();
  }

  displayNumber(displayValue);

  if (millis() - previousMillis >= myDelay) {
    previousMillis = millis();

    noInterrupts();

    if (countMode == 0) {
      counter = (counter + 1) % 10;
    } else {
      counter = (counter + 2) % 10;
    }

    interrupts();
  }

}

void evenMode() {
  counter = 0;
  countMode = 1;
  resetRequested = true;
}

void oddMode() {
  counter = 1;
  countMode = 2;
  resetRequested = true;
}

void displayNumber(byte number) {

  delay(1000);

  digitalWrite(LATCH,LOW);
  shiftOut(SER, SRCLK,LSBFIRST,sevenSegmentDisplay[number]);
  digitalWrite(LATCH,HIGH);
  
}