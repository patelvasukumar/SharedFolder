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

/*
 * importing Scanner class from util package
 */
import java.util.Scanner;

/**
 * Lab1 class is the driver class and it has the menu option
 * The programmer has made all methods, constructor and variables private which does not need
 * to be public and called by other classes. 
 *  
 */
public class Lab1 {
    
	/**
	 * default constructor
	 */
    private Lab1() {
        
    }
    
    /**
     * driver method to call and run the methods from Numbers class
     * 
     * @param args - this is the reference of String array. String array is the parameter for main method
     */
    public static void main(String[] args) {
       
    	/*
    	 * Scanner, Lab1, Numbers reference : Object creation
    	 */
        Scanner scanner = new Scanner(System.in); 
        Lab1 lab1 = new Lab1();
        Numbers numbers = new Numbers();
        
        while(true) { // while loop with true boolean value
           
            try {

            int choice = 0;
            boolean loop = true; // local variables creation
            
            while (loop) { // inner while loop for choice variable validation
                try {
                	
                	lab1.displayMainMenu(); //method calling through reference
                    System.out.print("> ");
                	
                    choice = Integer.parseInt(scanner.nextLine()); // converting String to int through parse method
                    loop = false;
                
                } catch (Exception ex) { // exception handling and error message
                    System.out.println("\nPlease, enter a valid Integer ! ");
                   
                }
            }
            
            
            switch (choice) { // switch case with different cases
                
            case 1: // initializing default constructor in case 1
                
                numbers = new Numbers();  
                
                break;
               
            case 2:	// initializing parameterized constructor in case 2
                
                System.out.print("\nEnter a new size for the array: ");
                   
                try { 
                 
                	int size = Integer.parseInt(scanner.nextLine()); // converting from string to int
                    numbers = new Numbers(size); // parameterized constructor, int parameter
               
                } catch (Exception ex) { // exception handling and error message
                    System.out.println("\nYou entered the wrong argument ! Please, enter again ! ");
                }
                
                break;
               
            case 3: // adding single value into the array in case 3 by calling the add method
                
                numbers.addValue(scanner);
                
                break;
            
            case 4: // printing only numbers of the array, not null reference in case 4
                
                System.out.println("\nThe Numbers are:");
                
                for (int i = 0; i < numbers.getNumItems(); i++) {
                    System.out.println(numbers.getNumbers()[i]);
                }
               
                break;
               
            case 5: // calling toString method in case 5 
                
            	// there is two way to print the statement by calling the toString method
            	// or we can also put object reference directly into the print, and then it would 
            	// produce the same result.
            	// the programmer has commented both ways as below.
            	
                System.out.println(numbers.toString()); // 1st way
                // System.out.println(numbers);	//2nd way
                
                break;
            
            case 6: // shutting down the program with programmers name + scanner 
            	
                System.out.println("\nThe program is shutdown.");
                System.out.println("\nThe program is made by Vasukumar Patel");
                scanner.close();
                System.exit(0);
                
                break;
            
            default: // default case when int does not match with above cases
                
                System.out.println("\nYou selected the wrong option ! Please, enter again ! ");
                
                break;
            }
        
        } catch (Exception ex) { // exception handling with error message
            System.out.println("\nYou entered the wrong argument ! Please, enter again !");
        }   
    } 
    }

    private void displayMainMenu() { // private method with print statements and messages
    	
    	System.out.println();
        System.out.println("Please select one of the following:");
        System.out.println("1: Initialize a default array");
        System.out.println("2: Specify the max size of the array");
        System.out.println("3: Add value to the array");
        System.out.println("4: Display values in the array");
        System.out.println("5: Display average of the values, minimum value, maximum value, max mod min, factorialMax");
        System.out.println("6: To Exit");
        System.out.println();
        
    }
}
