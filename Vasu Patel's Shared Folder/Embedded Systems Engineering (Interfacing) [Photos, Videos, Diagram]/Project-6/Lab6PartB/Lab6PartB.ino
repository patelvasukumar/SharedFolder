// Student Name: Vasukumar Patel
// Student Number: 041153940

#include "SevSeg.h"
#include <LiquidCrystal.h>

int ThermistorPin = A0;
int Vo;


float R1 = 10000.0f;
float logR2, R2, T;
float c1 = 1.009249522e-03, c2 = 2.378405444e-04, c3 = 2.019202697e-07;

const int rs = 21, en = 20, d4 = 19, d5 = 18, d6 = 17, d7 = 16;


SevSeg sevseg;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void setup() {

  lcd.begin(16,2);

  lcd.setCursor(0,0);
  lcd.print("Created By Vasu");

  lcd.setCursor(0,1);
  lcd.print("Patel");
  
  Serial.begin(9600);
  byte numDigits = 4;

  byte digitPins[4] = {6,5,4,3};
  byte segmentPins[8] = {13,12,11,10,9,8,7,22};

  bool resistorsOnSegments = true; 
  byte hardwareConfig = COMMON_CATHODE;
  bool updateWithDelays = false; 
  bool leadingZeros = true; 
  bool disableDecPoint = false; 

  sevseg.begin(hardwareConfig, numDigits, digitPins, segmentPins, resistorsOnSegments,
  updateWithDelays, leadingZeros, disableDecPoint);
  sevseg.setBrightness(90); 

}

void loop() {
  
  float sumTemp = 0;
 
  for(int i = 0; i < 20; i++) {
    
    Vo = analogRead(ThermistorPin);
    
    R2 = R1 * (1023.0 / (float) Vo - 1.0);
    
    logR2 = log(R2);
    
    T = (1.0 / (c1 + c2 * logR2 + c3 * logR2 * logR2 * logR2));
    
    T = T - 273.15;
    
    sumTemp += T;
  
  }

  float avgTemp = sumTemp / 20.0;
  Serial.print("Average Temperature: ");
  Serial.println(avgTemp);

  sevseg.setNumberF(avgTemp, 2);

  unsigned long displayTime = millis();
 
  while(millis() - displayTime < 1000) {
    sevseg.refreshDisplay();
  }

}
