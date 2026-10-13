/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

import java.io.BufferedReader;
import java.util.Scanner;

/**
 *  Numbers class contains array, methods and data value of the array
 *  The programmer has made all methods, constructor and variables private which does not need
 *  to be public and called by other classes. 
 *  
 */
public class Numbers {
    
	/*
	 * The array is a wrapper type array
	 */
	private Float[] numbers;
    private int numItems;
    private int size;

    /**
	 * default constructor that initialize the value for size, numbers array and numItems variables
	 */
    public Numbers() {
        this.size = 4; // hard coded value
        this.numbers = new Float[size]; // hard coded value
        this.numItems = 0; // hard coded value
    }

    /**
	 * this parameterized constructor sets the value of size into instance variable
	 * that helps to define the size of the array 
	 * 
	 * @param size - it sets the value into size instance variable 
	 */
    public Numbers(int size) {
        this.size = size;
        this.numbers = new Float[size];
        this.numItems = 0;	// hard coded value
    }

    /**
     * getter method for wrapper Float variable
     * @return - it returns the numbers array
     */
    public Float[] getNumbers() { return numbers; }
    
    /**
     * setter method for wrapper Float variable
     * @param numbers - it has array parameter which is wrapper float type
     */
    public void setNumbers(Float[] numbers) { this.numbers = numbers; }

    /**
     * getter method for numItems variable
     * @return - it returns int numItem's value
     */
	public int getNumItems() { return numItems; }

	/**
	 * setter method for numItems variable
	 * @param numItems - int parameter
	 */
	public void setNumItems(int numItems) { this.numItems = numItems; }

	
    /**
     * this method addValue into the array according to the numItem (which contains index number)
     * if the array is full, it returns nothing and print a message on the console 
     * used try catch and while loop for input validation
     * 
     * @param keyboard - reference of Scanner class
     */
    public void addValue(Scanner keyboard) {
        
    	if (numItems >= size) {
            System.out.println("\nThe Array is full");	// print the message on console
            return;	// return nothing
        }
       
    	boolean loop = true;	 // set true into local variable 
    	
        while (loop) {	// loop to repeat if the result is not expected
           
            try {	// try to catch the errors or exceptions 
                  
            	System.out.print("\nEnter the value: ");
            	float value = Float.parseFloat(keyboard.nextLine());	// add new value into local variable 
                value = (value * 2);
                numbers[numItems] = value;
                numItems++;	// increase the value by one
                loop = false;	// changing the value 
           
            } catch (Exception ex) {	// catch block that prints a valid message if error or exception happens
                System.out.println("\nYou entered the wrong value, Please try again ! ");
            }   
       
        }
    }
    
    /**
     * this method inserts multiple values into the array 
     * 
     * @param scanner - scanner reference to take user input
     * @param number - the number of values which user want to insert in the array
     */
    public void addValues(Scanner scanner, int number) {

        if (number > (size - numItems)) {
            System.out.println("\nNo room in array to add all values");
            return;
        }

        for (int i = 0; i < number; i++) { // loop through array

            if (numItems >= size) {
                System.out.println("\nThere is no space to add values into the array");
                return; // return with noting
            }
      
            System.out.print("\nEnter the value: ");
            boolean loop = true;

            while (loop) {
            	
                try {
                    float value = Float.parseFloat(scanner.nextLine());	// taking input from user
                    value = (value * 2);	// multiplication method
                    numbers[numItems] = value;
                    numItems++;
                    loop = false;

                } catch (Exception ex) {
                    System.out.println("\nYou entered the wrong value, Please try again !");
                }
            }
        }
    }

    /**
     * this method read values from the file and add them into array
     * 
     * @param reader - reference of buffer reader 
     * @param number - it says how many index are already filled
     */
    public void addValues(BufferedReader reader, int number) {
       
    	for (int i = 0; i < number; i++) {	// looping through array
           
    		if (numItems >= size) {
                System.out.println("\nNo room in array to add all values");
                return; // return nothing and print the message
            }
    		
    		try {
    		
            String line = reader.readLine(); // reading text file 
            
            if (line == null) { // if there is nothing, return nothing
            	return; // return nothing
            }
            	
            float value = Float.parseFloat(line.trim()); // store the value into float form
            numbers[numItems] = value;
            numItems++; // increase by one
            
            } catch (Exception ex) {	// exception handling
                System.out.println("There is something wrong. Please, try again.");
            }
        
    	}
    
    }

    /**
     * this method returns a float value which is average value of all array's values from the method
     * if the array is null, it returns zero
     * 
     * @return - it returns a float value of average of the total number
     */
    public float calcAverage() {
        
    	if (numItems == 0) { 
        	return 0;
        } 
        
        float average = 0;
        
        for (int i = 0; i < numItems; i++) {
        	average = average + numbers[i];  // it adds all elements into a single local variable
        }
          
        average = (average/numItems); // dividing the total by the array size (total number)
        return average;
    
    }

    /**
     * it returns a string message that contains minimum value, max value and mode value
     * it calculates that which is minimum and maximum value in the array by loop and if statement 
     * and add them into local variables 
     * mode calculates through this % .
     * 
     * @return - A string that shows minimum, maximum and mode values
     */
    public String findMinMax() {
       
    	if (numItems == 0) {
            String message = String.format("Minimum value is %.2f , Maximum value is %.2f , max mod min is %.2f", 0f, 0f, 0f);
            return message;
    	}
       
    	float min = numbers[0];	// local variable sets with first value of the array
    	float max = numbers[0];	// local variable sets with second value of the array
       
    	for (int i = 1; i < numItems; i++) {	// looping through all values
          
    		if (numbers[i] < min) { // if the value is small than the given the current value sets
    			min = numbers[i];	 // sets into min local variable 
    		}
            
    		if (numbers[i] > max) {	// if the value is big than the given the current value sets
            	max = numbers[i]; // sets into max local variable 
            }
        
    	} 
        
         min = Math.round(min); // doing round off on min
         max = Math.round(max);	// doing round off on max
         float mod;
        
        if (min == 0) {
        	  
        	mod = 0;  // if min is zero, set mode to zero in order to avoid divided by zero exception
        
        } else {
        
        	mod = (max % min);	// if min is not zero, then this calculation happens
       
        }
        
        // sets all values into string and the string is stored into local variable 
        // used formatter method to format the string which is also used in printf().
        String message = String.format("Minimum value is %.2f , Maximum value is %.2f , max mod min is %.2f", min, max, mod);
        return message;	// returns the lcoal variable that has string value
   
    }

    /**
     * this method finds the biggest value and then factorized the number by using loop with multiplication operator
     * 
     * @return - it return a int value which is casted from a float value
     */
    public int getFactorialMax() {
        
    	if (numItems == 0) { // if array is zero, it return zero
    		return 0;
    	}
    	
        int max = Math.round(numbers[0]); // max local variable has the first value of the array + round off
        
        for (int i = 1; i < numItems; i++) { // looping through array
          
        	int value = Math.round(numbers[i]); // round off array's value
           
        	if (value > max) {	// if the current max local variable is smaller than the looping variable
            	max = value; // the looping variable sets its value into local variable max
            }
       
        }  
         
        int factorial = 1; // local variable with 1 value
        
        for (int i = 1; i <= max; i++) { // we cannot use zero for index because we do not want zero
        	   
        	factorial = (factorial * i); 	// every result
       
        }
       
        return factorial; // return the int value which converted from float value
    
    }  

    /** 
     * Overriding the in-build ToString() method which is the part of the Object class.
     * Also, Object class is the top class in the java hierarchy. It is the part of
     * the java.lang package which is auto-imported here.
     * 
     * it return a formated string with all printed values.
     * 
     */
    @Override
    public String toString() {
    
    	String message = String.format("\nAverage is %.2f , %s , factorialMax is %d", calcAverage(), findMinMax(), getFactorialMax());
    	// calls the method and sets the into the string
    	return message; // return the local variable that contains the string
   
    }
}