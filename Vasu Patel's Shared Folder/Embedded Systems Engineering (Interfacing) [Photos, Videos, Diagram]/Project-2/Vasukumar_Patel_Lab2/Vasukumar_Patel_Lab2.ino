const int vehicleRed = 8;
const int vehicleYellow = 9;
const int vehicleGreen = 10;

const int walkerRed = 11;
const int walkerGreen = 12;

const int buttonPin = 2;

int pbStatus = HIGH;

void runTrafficCycle(){

  delay(1000);

  digitalWrite(vehicleGreen,LOW);
  digitalWrite(vehicleYellow,HIGH);

  delay(1000);

  digitalWrite(vehicleYellow,LOW);
  digitalWrite(vehicleRed,HIGH);

  delay(1000);

  digitalWrite(walkerRed,LOW);
  digitalWrite(walkerGreen,HIGH);

  delay(1000);

  for (int i = 0; i < 8; i++) {
      
      digitalWrite(walkerGreen,LOW);
      delay(250);

      digitalWrite(walkerGreen,HIGH);
      delay(250);

  }

  digitalWrite(walkerGreen,LOW);
  digitalWrite(walkerRed,HIGH);

  delay(1000);

  digitalWrite(vehicleRed,LOW);
  digitalWrite(vehicleGreen,HIGH);

}

void setup(){

  pinMode(vehicleRed,OUTPUT);
  pinMode(vehicleYellow,OUTPUT);
  pinMode(vehicleGreen,OUTPUT);

  pinMode(walkerRed,OUTPUT);
  pinMode(walkerGreen,OUTPUT);

  pinMode(buttonPin,INPUT_PULLUP);

  
  digitalWrite(vehicleGreen,HIGH);
  digitalWrite(vehicleRed,LOW);
  digitalWrite(vehicleYellow,LOW);

  digitalWrite(walkerRed,HIGH);
  digitalWrite(walkerGreen,LOW);

}

void loop(){

  unsigned long startTime = millis();

  do {
    
    pbStatus = digitalRead(buttonPin);


  } while ( pbStatus == HIGH && millis() - startTime < 10000 );
   


  if (pbStatus == LOW || millis() - startTime >= 10000) {
    
    runTrafficCycle();

  }
    

}