/*

Student Name: Vasukumar Patel
Student Number: 041153940
Lab - 2 : Interfacing
Part - B

*/

int led = 10;

void setup() {

  pinMode(led, OUTPUT);

}


void loop() {
  
	// 3 times faster
  	for (int i = 0; i < 3; i++) {
    		
		digitalWrite(led, HIGH);
    		delay(5000);
    		digitalWrite(led, LOW);
    		delay(1000);
  	}    

	// Slow blink 3 times
  	for (int i = 0; i < 3; i++) {
    
		digitalWrite(led, HIGH);
    		delay(900);
    		digitalWrite(led, LOW);
    		delay(900);
  	}            

}

/*
1. What changes do you need to make?
=> need to change the value of output LEDBUILTIN

2. What is the best coding practice to make these changes? Why?
=> The best coding practice is to reassign the new node (10) value to the LEDBUILTIN micro or to make a new variable led and then assign the value to the necessary methods. The best method is to make a global variable. So, In the future, if we need to change the value, then we can do it without going through the whole code. 
*/
