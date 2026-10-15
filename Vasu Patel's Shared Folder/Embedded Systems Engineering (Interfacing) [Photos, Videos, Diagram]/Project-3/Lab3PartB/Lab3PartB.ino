// Vasukumar Patel [041153940]

// Lab 3 - LED Fire Effect

// References :
// 1. https://docs.arduino.cc/language-reference/en/functions/analog-io/analogWrite/

int ledPin2 = 2;
int ledPin3 = 3;
int ledPin4 = 4;
int ledPin5 = 5;
int ledPin6 = 6;
int ledPin7 = 7;
int ledPin8 = 8;
int ledPin9 = 9;
int ledPin10 = 10;
int ledPin11 = 11;
int ledPin12 = 12;
int ledPin13 = 13;

int analogPin = A0;
int value = 0;

void setup() {

  pinMode(ledPin2, OUTPUT);
  pinMode(ledPin3, OUTPUT);
  pinMode(ledPin4, OUTPUT);
  pinMode(ledPin5, OUTPUT);
  pinMode(ledPin6, OUTPUT);
  pinMode(ledPin7, OUTPUT);
  pinMode(ledPin8, OUTPUT);
  pinMode(ledPin9, OUTPUT);
  pinMode(ledPin10, OUTPUT);
  pinMode(ledPin11, OUTPUT);
  pinMode(ledPin12, OUTPUT);
  pinMode(ledPin13, OUTPUT);


}

void loop() {

  value = random(120) + 135;

  analogWrite(ledPin3, value);
  analogWrite(ledPin4, value);
  analogWrite(ledPin5, value);
  analogWrite(ledPin6, value);
  analogWrite(ledPin7, value);
  analogWrite(ledPin8, value);
  analogWrite(ledPin9, value);
  analogWrite(ledPin10, value);
  analogWrite(ledPin11, value);
  analogWrite(ledPin12, value);

}
