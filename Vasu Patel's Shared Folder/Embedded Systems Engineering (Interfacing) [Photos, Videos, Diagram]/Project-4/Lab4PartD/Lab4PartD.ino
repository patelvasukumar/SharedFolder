// Studetn Name: Vasukumar Patel
// Studetn Number: 041153940
// Lab - 4 Part - 

// Refrences : 1. https://www.youtube.com/watch?v=H8tf0oFBuyU&t=1s 
#include <TimerOne_V2.h>

const int redLED = 5;
const int greenLED = 6;
const int blueLED = 7;

const int rgbRedLED = 11;
const int rgbGreenLED = 12;
const int rgbBlueLED = 13;

const int buttonPin1 = 21;
const int buttonPin2 = 20;
const int buttonPin3 = 19;

volatile bool pb1Event = false;
volatile bool pb2Event = false;
volatile bool buttonsLocked = false;

volatile int buttonCount = 0;

volatile bool separateLEDsEnabled = true;
volatile bool separateLEDState = true;
volatile bool rgbLEDState = true;

unsigned long lockoutStartTime = 0;
bool pb3WaitingForRelease = false;

void pb1ISR();
void pb2ISR();
void timerISR();

void setup() {
 
    Serial.begin(9600);

    pinMode(redLED, OUTPUT);
    pinMode(greenLED, OUTPUT);
    pinMode(blueLED, OUTPUT);

    pinMode(rgbRedLED, OUTPUT);
    pinMode(rgbGreenLED, OUTPUT);
    pinMode(rgbBlueLED, OUTPUT);

    pinMode(buttonPin1, INPUT_PULLUP);
    pinMode(buttonPin2, INPUT_PULLUP);
    pinMode(buttonPin3, INPUT_PULLUP);

    digitalWrite(redLED, HIGH);
    digitalWrite(greenLED, HIGH);
    digitalWrite(blueLED, HIGH);

    digitalWrite(rgbRedLED, HIGH);
    digitalWrite(rgbGreenLED, HIGH);
    digitalWrite(rgbBlueLED, HIGH);

    attachInterrupt(digitalPinToInterrupt(buttonPin1), pb1ISR, FALLING);
    attachInterrupt(digitalPinToInterrupt(buttonPin2), pb2ISR, FALLING);

    Timer1.initialize(500000);
    Timer1.attachInterrupt(timerISR);
    Timer1.start();


}


void loop()
{
    
    if (digitalRead(buttonPin3) == LOW && !buttonsLocked && !pb3WaitingForRelease){
        
        unsigned long currentTime = millis();

        noInterrupts();

        buttonsLocked = true;

        pb1Event = false;
        pb2Event = false;

        interrupts();

        lockoutStartTime = currentTime;

        pb3WaitingForRelease = true;
    
    }

    if (digitalRead(buttonPin3) == HIGH){
        pb3WaitingForRelease = false;
    }

    if (buttonsLocked && millis() - lockoutStartTime >= 10000){
        
        noInterrupts();

        buttonsLocked = false;

        interrupts();
    
    }

    if (buttonsLocked){
        return;
    }

    if (pb1Event){
        noInterrupts();
        pb1Event = false;
        buttonCount++;
        interrupts();

        separateLEDsEnabled = false;
        separateLEDState = false;

        digitalWrite(redLED, LOW);
        digitalWrite(greenLED, LOW);
        digitalWrite(blueLED, LOW);

        Serial.print("PB1 pressed: ");
        Serial.println(buttonCount);
    }

    if (pb2Event){
        noInterrupts();
        pb2Event = false;
        interrupts();

        separateLEDsEnabled = true;
        separateLEDState = false;
    }

}



void pb1ISR(){
    
    if (!buttonsLocked){
        pb1Event = true;
    }

}

void pb2ISR(){
    
    if (!buttonsLocked){
        pb2Event = true;
    }

}

void timerISR(){

    if (separateLEDsEnabled){
        
        separateLEDState = !separateLEDState;

        digitalWrite(redLED, separateLEDState);
        digitalWrite(greenLED, separateLEDState);
        digitalWrite(blueLED, separateLEDState);
    
    } else {
        
        digitalWrite(redLED, LOW);
        digitalWrite(greenLED, LOW);
        digitalWrite(blueLED, LOW);
    
    }

    static int timerCount = 0;

    timerCount++;

    if (timerCount >= 2){
      
        timerCount = 0;

        rgbLEDState = !rgbLEDState;

        digitalWrite(rgbRedLED, rgbLEDState);
        digitalWrite(rgbGreenLED, rgbLEDState);
        digitalWrite(rgbBlueLED, rgbLEDState);
    }

}