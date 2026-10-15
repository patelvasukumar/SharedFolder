// Vasukumar Patel [041153940]

// Lab 3 - Serial Controlled Tricolor LED

// References :
// 1. https://docs.arduino.cc/language-reference/en/functions/analog-io/analogWrite/
// 2. https://docs.arduino.cc/language-reference/en/functions/communication/serial/println/
// 3. https://docs.arduino.cc/language-reference/en/functions/communication/serial/readStringUntil/ 
// 4. https://docs.arduino.cc/language-reference/en/functions/communication/serial/available/ 
// 5. https://www.youtube.com/watch?v=qd32N6u-mN0 

#define redLed 19
#define greenLed 20
#define blueLed 21

String command;

void setup() {

  Serial.begin(9600);

  pinMode(redLed, OUTPUT);
  pinMode(greenLed, OUTPUT);
  pinMode(blueLed, OUTPUT);

  delay(2000);

  Serial.println("Type your command (red, green, blue, all, off).");
}

void loop() {

  if (Serial.available() > 0) {

    command = Serial.readStringUntil('\n');
    command.trim();

    if (command.equals("red")) {

      digitalWrite(redLed, HIGH);
      digitalWrite(greenLed, LOW);
      digitalWrite(blueLed, LOW);

    }
    else if (command.equals("green")) {

      digitalWrite(redLed, LOW);
      digitalWrite(greenLed, HIGH);
      digitalWrite(blueLed, LOW);

    }
    else if (command.equals("blue")) {

      digitalWrite(redLed, LOW);
      digitalWrite(greenLed, LOW);
      digitalWrite(blueLed, HIGH);

    }
    else if (command.equals("all")) {

      digitalWrite(redLed, HIGH);
      digitalWrite(greenLed, HIGH);
      digitalWrite(blueLed, HIGH);

    }
    else if (command.equals("off")) {

      digitalWrite(redLed, LOW);
      digitalWrite(greenLed, LOW);
      digitalWrite(blueLed, LOW);
    }
  }
}