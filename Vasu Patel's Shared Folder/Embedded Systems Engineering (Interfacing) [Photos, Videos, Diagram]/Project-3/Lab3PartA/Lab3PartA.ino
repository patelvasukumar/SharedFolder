// Vasukumar Patel [041153940]

// Lab 3 - LED Chase Effect

byte ledPin[] = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

int ledDelay(65);
int direction = 1;
int currentLED = 0;
unsigned long changeTime;

int varPin = A0;
int varStore = 0;

int topYellow = 13;
int bottomYellow = 2;

void setup() {

  for (int x = 0; x < 10; x++) {
    pinMode(ledPin[x], OUTPUT);
    digitalWrite(ledPin[x], HIGH);
  }

  pinMode(topYellow, OUTPUT);
  pinMode(bottomYellow, OUTPUT);

  digitalWrite(bottomYellow, LOW);
  digitalWrite(topYellow, HIGH);

  changeTime = millis();

}

void loop() {

  varStore = analogRead(varPin);
  ledDelay = map(varStore, 0, 1023, 20, 500);

  if ((millis() - changeTime) > ledDelay) {

    changeLED();
    changeTime = millis();

  }

}

void changeLED() {

  for (int x = 0; x < 10; x++) {
    digitalWrite(ledPin[x], HIGH);
  }

  digitalWrite(ledPin[currentLED], LOW);

  if (direction == 1) {

    if (currentLED == 9) {

      delay(500);

      digitalWrite(ledPin[9], HIGH);

      delay(500);

      digitalWrite(bottomYellow, HIGH);

      delay(500);

      digitalWrite(topYellow, LOW);

      direction = -1;

      currentLED = 9;
    }
    else {
      currentLED++;
    }

  }
  else {

    if (currentLED == 0) {

      delay(500);

      digitalWrite(ledPin[0], HIGH);

      delay(500);

      digitalWrite(topYellow, HIGH);

      delay(500);

      digitalWrite(bottomYellow, LOW);

      direction = 1;

      currentLED = 0;
    }
    else {
      currentLED--;
    }

  }

}