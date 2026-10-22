/*
  The circuit:
 * LCD RS pin to digital pin 12
 * LCD Enable pin to digital pin 11
 * LCD D4 pin to digital pin 5
 * LCD D5 pin to digital pin 4
 * LCD D6 pin to digital pin 3
 * LCD D7 pin to digital pin 2
 * LCD R/W pin to ground
 * LCD VSS pin to ground
 * LCD VCC pin to 5V
 * 10K resistor:
 * ends to +5V and ground
 * wiper to LCD VO pin (pin 3)

 http://www.arduino.cc/en/Tutorial/LiquidCrystalHelloWorld

*/

// Student Name: Vasukumar Patel
// Student Number: 041153940

#include <LiquidCrystal.h>
#include <DHT11.h>

const int rs = 21, en = 20, d4 = 19, d5 = 18, d6 = 17, d7 = 16;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
DHT11 dth11(15);

bool showingError = false;
volatile bool showName = false;


void setup() {

  lcd.begin(16,2);
  pinMode(2,INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(2),buttonISR,FALLING);

}

void loop() {


  if (showName) {
        
        showName = false;
        displayName();
        delay(10000);
        return;
  
  }

  int temperature = 0; 
  int humidity = 0;

  int result = dth11.readTemperatureHumidity(temperature, humidity);

  if (result == 0) {


      if (showingError) {
          lcd.clear();
          showingError = false;
      }    
       
       lcd.setCursor(0,0); lcd.print("Temp = ");
       lcd.setCursor(7,0); lcd.print(temperature);
       lcd.setCursor(9,0); lcd.print((char)223);
       lcd.setCursor(10,0); lcd.print("C");
       lcd.setCursor(11,0); lcd.print("     ");

       lcd.setCursor(0,1); lcd.print("Humi = ");
       lcd.setCursor(7,1); lcd.print(humidity);
       lcd.setCursor(10,1); lcd.print("%");
       lcd.setCursor(12,1); lcd.print("RH");
       lcd.setCursor(11,1); lcd.print("     ");

      
    } else {

      if (!showingError) {
      
        lcd.clear();
        lcd.setCursor(0, 0); lcd.print("DHT11 read error");
        lcd.setCursor(0, 1); lcd.print("Check connection");
        showingError = true;
    
      }      

    
    }

}

void buttonISR() { showName = true }

void displayName() {

  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Created By Vasu");
  lcd.setCursor(0, 1);
  lcd.print("Patel");

}