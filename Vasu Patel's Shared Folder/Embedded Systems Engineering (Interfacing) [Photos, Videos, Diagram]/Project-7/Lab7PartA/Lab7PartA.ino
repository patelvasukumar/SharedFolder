// Student Name: Vasukumar Patel
// Student Number: 041153940

const int sensorPin = A0;   
const int ledPin = 21;     


int sensorValue = 0;   
int sensorMin = 1023;  
int sensorMax = 0;     


void setup() {
  
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, HIGH);

 
  while (millis() < 5000) {
    
    sensorValue = analogRead(sensorPin);

   
    if (sensorValue > sensorMax) {
      sensorMax = sensorValue;
    }

    if (sensorValue < sensorMin) {
      sensorMin = sensorValue;
    }

  }

  
  digitalWrite(ledPin, LOW);

}

void loop() {
  
  sensorValue = analogRead(sensorPin);

  
  sensorValue = constrain(sensorValue, sensorMin, sensorMax);

  
  sensorValue = map(sensorValue, sensorMin, sensorMax, 0, 255);

  
  analogWrite(ledPin, sensorValue);

  Serial.print("The sensor value is: ");
  Serial.println(sensorValue);


}

