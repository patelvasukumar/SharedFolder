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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Lab2 class is the driver class and it has the menu option
 * The programmer has made all methods, constructor and variables 
 *  
 */
public class Lab2 {
    
	/**
	 * default constructor
	 */
	private Lab2() {}

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
        Lab2 lab2 = new Lab2();
        Numbers numbers = new Numbers();

        
        while (true) {	// while loop with true boolean value
          
        	
        	try {
        	
        	lab2.displayMainMenu(); //method calling through reference
            int choice = 0;
           boolean loop = true;		// local variables creation
            
            while(loop){		// inner while loop for choice variable validation
           
            try {
            	
            	System.out.print("> ");
                choice = Integer.parseInt(scanner.nextLine());	// converting String to int through parse method
                loop= false;
           
            } catch (Exception ex) {	// exception handling and error message
                System.out.println("\nPlease, enter a valid option from option 1 to 9.");
            }
           
            } 
            
            switch (choice) {	// switch case with different cases
            
            case 1:	// initializing default constructor in case 1
                    numbers = new Numbers();
                    break;
                
            case 2:	// initializing parameterized constructor in case 2
                    System.out.print("\nEnter a new size for the array: ");
                   
                    try {
                       
                    	int size = Integer.parseInt(scanner.nextLine());	// converting from string to int
                        numbers = new Numbers(size);	// parameterized constructor, int parameter
                     
                    } catch (Exception ex) {	// exception handling and error message
                        System.out.println("\nYou entered the wrong argument! Please, enter an integer.");
                    }
                    
                    break;
               
            case 3:	// adding single value into the array in case 3 by calling the add method
                    numbers.addValue(scanner);
                    break;
               
            case 4:	// printing only numbers of the array, not null reference in case 4
            	
                    System.out.println("\nThe Numbers are:");
                   
                    for (int i = 0; i < numbers.getNumItems(); i++) {
                   
                    	System.out.println(numbers.getNumbers()[i]);
                    
                    }
                   
                    break;
                
            case 5: // calling toString method in case 5 
            	
                    System.out.println(numbers);
                    break;
               
            case 6:	// putting multiple values into the array in case 6 
                   
                    try {
                    	   
                    	System.out.print("\nHow many values do you wish to add ? ");
                    	int number = Integer.parseInt(scanner.nextLine()); // taking user input 
                        numbers.addValues(scanner, number); // calling method
                   
                    } catch (Exception ex) { // exception handling
                        System.out.println("\nYou entered the wrong argument! Please, enter an integer.");
                    }
                    
                    break;
               
            case 7: // reading values from the file in case 7
                    System.out.print("\nName of the file to read from: ");
                    String fileName = scanner.nextLine(); // storing the file name
                     
                    /*
                     *  Since we used Buffered reader into the brackets of try block,
                     *  we do not need to close it because it closed automatically
                     *  
                     *  using buffer reader with file reader. So, rather than a single character,
                     *  it reads the whole line through making buffer by buffer reader.
                     *  
                     */
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        
                    	// reading the first line and then taking the value which says how many
                    	// index's are already filled by the array
                        int value = Integer.parseInt(reader.readLine());
                        numbers.addValues(reader, value);   // buffer reader
                          
                       // if file is not here, this exception is used 
                    } catch (FileNotFoundException ex) {
                     
                    	System.out.println("\nThe file is not on the system.");
                  
                    	// for other kind of any exception
                    } catch (Exception ex) {
                       
                    	System.out.println("\nThere is a problem. Please, try again !");
                    
                    }    
                    
                    break;
              
            case 8: // to store all values into the file in case 8
                    System.out.print("\nName of the file to save to: ");
                    String newfile = scanner.nextLine(); // file name from user
                   
                    
                    /*
                     *  Since we used Buffered writer into the brackets of try block,
                     *  we do not need to close it because it closed automatically
                     *  
                     *  using buffer writer with file writer. So, rather than a single character,
                     *  it writes the whole words or lines through making buffer by buffer writer.
                     *  
                     */
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(newfile))) {   
                        
                    	// storing current total filled size
                    	String value = String.format("%d", numbers.getNumItems());
                    	writer.write(value); // writing into the file
                        writer.newLine(); //new line 
                    	
                    	for (int i = 0; i < numbers.getNumItems(); i++) { // looping through arrays
                          
                    		String number = String.format("%.2f", numbers.getNumbers()[i]); // converting into String
                        	writer.write(number);   // writing the number into the file
                            writer.newLine(); // new line
                       
                        }
               
                    	// if there is any exception, this one is used
                    } catch (Exception e) {
                     
                    	System.out.println("\nThere is a problem. Please, try again !");
                   
                    }
                    
                    break;
              
            case 9:	// shutting down the program with programmers name + scanner 
                    System.out.println("\nThe program is shutdown.");
                    System.out.println("\nThe program is made by Vasukumar Patel.");
                    scanner.close();
                    System.exit(0);
                    break;
               
            default:	// default case when int does not match with above cases
                    System.out.println("Please, enter a valid option from option 1 to 9.");
                    break;
                    
            }
        } catch (Exception ex) {	// exception handling with error message
        	System.out.println("\nYou entered the wrong argument ! Please, enter again !");
        }
        }
    }
    
    
    private void displayMainMenu() {	// private method with print statements and messages
     
    	System.out.println();
        System.out.println("Please select one of the following:");
        System.out.println("1: Initialize a default array");
        System.out.println("2: To specify the max size of the array");
        System.out.println("3: Add value to the array");
        System.out.println("4: Display values in the array");
        System.out.println("5: Display the average, minimum, maximum, max mod min, factorialMax");
        System.out.println("6: Enter multiple values");
        System.out.println("7: Read values from file");
        System.out.println("8: Save values to file");
        System.out.println("9: To Exit");
        System.out.println();
   
    }
}