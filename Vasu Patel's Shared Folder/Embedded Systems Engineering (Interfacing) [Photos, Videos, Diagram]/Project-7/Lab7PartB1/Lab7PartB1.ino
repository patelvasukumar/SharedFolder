// student name: vasukumar patel
// student number: 041153940

int buzzerPin = 20;
int buttonPin = 2;

void setup() {
  pinMode(buzzerPin, OUTPUT);
  pinMode(buttonPin, INPUT_PULLUP);

}

void loop() {

  int buttonState = digitalRead(buttonPin);

  if (buttonState == LOW) {
    
    digitalWrite(buzzerPin, HIGH);
  
  } else {
    
    digitalWrite(buzzerPin, LOW);
  
  }

}