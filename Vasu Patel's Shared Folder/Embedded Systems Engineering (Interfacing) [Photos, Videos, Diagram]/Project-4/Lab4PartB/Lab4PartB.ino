// Student Name: Vasukumar Patel
// Student number: 041153940
// Lab - 4 PART-B
// Note : Lab Required Values code are commented because the coder wanted better
//        observation of the Lab - 4 part B. So, he (Vasu) used different values which 
//        is better for human observation. 

// Reference:
// 1. https://docs.arduino.cc/language-reference/en/functions/external-interrupts/attachInterrupt/
// 2. https://github.com/kotwatthana/TimerOne_V2


#include <TimerOne_V2.h>

String LEDStatus = "OFF";
const int YellowLED = 1;
const int RedLED = 5;

void BlinkYellow();

void setup() {
  
  pinMode(YellowLED,OUTPUT);
  pinMode(RedLED,OUTPUT);

   // Timer1.initialize(250000); Lab Required Value
   Timer1.initialize(1000000); // 1 sec
   Timer1.attachInterrupt(BlinkYellow);


}

void loop() {
  
  digitalWrite(RedLED,HIGH);
  // delay(1000); Lab Required Value
  delay(5000); // 5 sec

  digitalWrite(RedLED,LOW);
  // delay(1000); Lab Required Value 
  delay(5000);  // 5 sec


}


void BlinkYellow(){

  if (LEDStatus == "ON") {

    digitalWrite(YellowLED,LOW);
    LEDStatus = "OFF";

  } else {

    digitalWrite(YellowLED,HIGH);
    LEDStatus = "ON";

  }

}
