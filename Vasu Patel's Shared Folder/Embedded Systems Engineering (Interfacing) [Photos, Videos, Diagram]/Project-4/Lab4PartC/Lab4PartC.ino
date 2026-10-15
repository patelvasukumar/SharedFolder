// Studetn Name: Vasukumar Patel
// Studetn Number: 041153940
// Lab - 4 Part - C

// Note : The base code value is changed because the coder wanted to use same circuit for all three parts (B,C,D) of the lab.

// int yellowLED = 11; base code value
// int redLED = 12; base code value
// int buttonPin = 4; base code value

// https://docs.arduino.cc/language-reference/en/functions/external-interrupts/attachInterrupt/

int yellowLED = 1;
int redLED = 5;
int buttonPin = 21;

void buttonInterrupt();


void setup() {
 
  
  pinMode(yellowLED, OUTPUT);
  pinMode(redLED, OUTPUT);
  pinMode(buttonPin, INPUT_PULLUP);

  attachInterrupt(digitalPinToInterrupt(21),buttonInterrupt,CHANGE);

}

void loop() {
  
  buttonInterrupt();

  digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  delay(1000);

}

void buttonInterrupt(){

  volatile int buttonState = digitalRead(buttonPin);
  
  if (buttonState == LOW){
    digitalWrite(yellowLED, HIGH);
  }
  
  if (buttonState == HIGH){
    digitalWrite(yellowLED, LOW);
  }
  

}
