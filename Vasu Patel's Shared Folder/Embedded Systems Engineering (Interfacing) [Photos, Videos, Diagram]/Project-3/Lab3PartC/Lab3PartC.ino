// Vasukumar Patel [041153940]

// Lab 3 - Pulsating Lamps

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

int value = 0;

float sinVal = 0;
int ledVal = 0;

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

  for (int x = 0; x <= 180; x++) {

    sinVal = sin((PI * x) / 180);

    ledVal = int(sinVal * 255);   
   
    analogWrite(ledPin3, ledVal);
    analogWrite(ledPin4, ledVal);
    analogWrite(ledPin5, ledVal);
    analogWrite(ledPin6, ledVal);
    analogWrite(ledPin7, ledVal);
    analogWrite(ledPin8, ledVal);
    analogWrite(ledPin9, ledVal);
    analogWrite(ledPin10, ledVal);
    analogWrite(ledPin11, ledVal);
    analogWrite(ledPin12, ledVal);

    delay(10);
  }

}
