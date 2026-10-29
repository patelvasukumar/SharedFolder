// student name: vasukumar patel
// student number: 041153940

// https://docs.arduino.cc/language-reference/en/functions/advanced-io/pulseIn/

const int trigPin = 6;
const int echoPin = 7;
const int buzzerPin = 20;

float duration;
float distance;

unsigned long beepInterval = 0;
unsigned long lastBeepTime = 0;
unsigned long beepStartTime = 0;
unsigned long lastPrintTime = 0;

const unsigned long beepDuration = 100;

bool buzzerOn = false;

void setup() {

    pinMode(trigPin, OUTPUT);
    pinMode(echoPin, INPUT);
    pinMode(buzzerPin, OUTPUT);

    digitalWrite(buzzerPin, LOW);

    Serial.begin(9600);

}

void loop() {

    digitalWrite(trigPin, LOW);
    delayMicroseconds(2);

    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);

    digitalWrite(trigPin, LOW);

    duration = pulseIn(echoPin, HIGH, 30000UL);
    distance = (duration * 0.0343) / 2;

    unsigned long currentTime = millis();

  
    if (currentTime - lastPrintTime >= 250) {

        Serial.print("Distance: ");
        Serial.print(distance);
        Serial.println(" cm");

        lastPrintTime = currentTime;
    }


    if (duration == 0) {
        beepInterval = 0;
    }
    else if (distance >= 30 && distance <= 40) {
        beepInterval = 1000;
    }
    else if (distance >= 20 && distance < 30) {
        beepInterval = 500;
    }
    else if (distance >= 10 && distance < 20) {
        beepInterval = 250;
    }
    else {
        beepInterval = 0;
    }

    if (beepInterval == 0) {

        digitalWrite(buzzerPin, LOW);
        buzzerOn = false;

    }
    else {

    if (!buzzerOn && currentTime - lastBeepTime >= beepInterval) {

      digitalWrite(buzzerPin, HIGH);

      buzzerOn = true;
      beepStartTime = currentTime;
      lastBeepTime = currentTime;
    }

    if (buzzerOn && currentTime - beepStartTime >= beepDuration) {

      digitalWrite(buzzerPin, LOW);
      buzzerOn = false;
    
    }

  }

  static unsigned long previousInterval = 0;

    if (beepInterval != previousInterval) {

      if (beepInterval > 0) {
        lastBeepTime = currentTime - beepInterval;
      }

      previousInterval = beepInterval;
  
    }

}