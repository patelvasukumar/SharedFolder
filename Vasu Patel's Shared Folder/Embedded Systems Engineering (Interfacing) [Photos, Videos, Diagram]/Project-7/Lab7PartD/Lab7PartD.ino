// student name: vasukumar patel
// student number: 041153940

const int led = 21;
const int sensor = 13;
const int buzzer = 20;

int state = LOW;
int val = 0;
int motionCount = 0;

void setup() {
  pinMode(led, OUTPUT);
  pinMode(sensor, INPUT);
  pinMode(buzzer, OUTPUT);

  digitalWrite(buzzer, LOW);

  Serial.begin(9600);
}

void loop() {

  val = digitalRead(sensor);

  if (val == HIGH) {

    digitalWrite(led, HIGH);
    digitalWrite(buzzer, HIGH);

    if (state == LOW) {
      Serial.println("Motion detected");

      motionCount++;

      Serial.print("Total motion events: ");
      Serial.println(motionCount);

      state = HIGH;
    }

  } else {

    digitalWrite(led, LOW);
    digitalWrite(buzzer, LOW);

    if (state == HIGH) {
      Serial.println("The action/motion has stopped");
      state = LOW;
    }
  }
}