// Student Name: Vasukumar Patel
// Student Number: 041153940

int dataPin = 21;
int latchPin = 20;
int clockPin = 19;

void setup() {
  
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(dataPin, OUTPUT);

}

void loop() {

  for (int position = 0; position < 8; position++) {
    
    byte pattern = 1 << position;

    digitalWrite(latchPin, LOW);
    shiftOut(dataPin, clockPin, MSBFIRST, pattern);
    digitalWrite(latchPin, HIGH);

    delay(250);
  
  }

  for (int position = 6; position > 0; position--) {
    
    byte pattern = 1 << position;
  
    digitalWrite(latchPin, LOW);
    shiftOut(dataPin, clockPin, MSBFIRST, pattern);
    digitalWrite(latchPin, HIGH);

    delay(250);
  
  }

}
