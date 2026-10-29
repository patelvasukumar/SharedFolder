// student name: vasukumar patel
// student number: 041153940

int buzzerPin = 19;
int buttonPin = 3;

void setup() {

  pinMode(buzzerPin, OUTPUT);
  pinMode(buttonPin, INPUT_PULLUP);

}

void loop() {

  if (digitalRead(buttonPin) == LOW) {

    delay(50);

    if (digitalRead(buttonPin) == LOW) {

      while (digitalRead(buttonPin) == LOW) {
      
      }

      delay(50);

      tone(buzzerPin, 440); // A4
      delay(1000);

      tone(buzzerPin, 494); // B4
      delay(1000);

      tone(buzzerPin, 523); // C4
      delay(1000);

      tone(buzzerPin, 587); // D4
      delay(1000);

      tone(buzzerPin, 659); // E4
      delay(1000);

      tone(buzzerPin, 698); // F4
      delay(1000);

      tone(buzzerPin, 784); // G4
      delay(1000);

      noTone(buzzerPin);
      delay(1000);

    }
  }

}