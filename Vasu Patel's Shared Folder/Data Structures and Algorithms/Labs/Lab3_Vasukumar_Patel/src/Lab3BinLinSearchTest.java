/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class is the driver class.
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/**
 *  importing exception class to handle exeption
 */
import java.lang.NumberFormatException; 

/**
 *  The class has main method and it is responsible to run the code and calling other 
 *  methods from other classes.
 */
public class Lab3BinLinSearchTest {
	
	/**
	 * parameterless constructor
	 */
	public Lab3BinLinSearchTest() {}
	
	/**
	 * Main method to run the code
	 * @param args - Array String values
	 */
    public static void main(String[] args) {
        
    	/**
    	 * local variable and object creation
    	 */
        BinaryLinearSearch searchObj = new BinaryLinearSearch();
        char[] randomChars = null;
        int choice = 0;
        
        /**
         *  loop with true attribute 
         */
        while (true) { 

        	/**
        	 * local variable
        	 */
            boolean valueChecker = true;
            while (valueChecker == true) { 
            	printMenu(); // calling static method 
                try {
                    // Converting from String to int 
                    choice = Integer.parseInt(searchObj.getScanner().nextLine());
                    valueChecker = false; 
                } catch (NumberFormatException ex) { // exception handling
                    System.out.println("\nPlease, Enter an Integer ! "); 
                }
            }
   
            /**
             * switch case for different scenarios 
             */
            switch (choice) {
                
                case 1: // initializing 30 size array
                  
                    searchObj.generateRandomChars();
                    randomChars = searchObj.getRandomChars();
                      
                    break;
                
                case 2: // Recursive Binary and linear Search
                
                	/*
                	 * if array size is not decide yet, it shows a customize error message 
                	 */
                    if (randomChars == null) {
                    
                    	System.out.println("\nPlease initialize the array first (Option 1).");
                   
                    } else {
                    	
                    	/**
                    	 * local variables 
                    	 */
                    	boolean keyCheck = true;
                    	char searchKey = 0;       
                    	
                    	// check the value 
                    	while(keyCheck) {
                    	try {
                    	System.out.print("\nPlease enter a character to search: ");
                        searchKey = searchObj.getScanner().nextLine().charAt(0);  // charAt() to select the specific character from the word.
                        keyCheck = false; // loop closed
                    	} catch (Exception ex) { // error handling
                    			System.out.print("\nThe argument is invalid. Please, Try Again !");
                    	}
                    	}          
                        
                    	/*
                    	 * local variables to store the time value
                    	 */
                        long timeNanoStart; 
                        long timeNanoEnd;
                        long timeMilliStart;
                        long timeMilliEnd;
                        int result;
                        System.out.println("\nRemaining elements during Recursive Binary Search:");
                        timeNanoStart = System.nanoTime(); // calling in-build method
                        timeMilliStart = System.currentTimeMillis(); // calling in-build method
                        result = searchObj.recursiveBinarySearch(randomChars, 0, randomChars.length - 1, searchKey); // storing into a variable
                        timeNanoEnd = System.nanoTime(); // calling in-build method
                        timeMilliEnd = System.currentTimeMillis(); // calling in-build method
  
                        /*
                         * if the key found it do the first block. Otherwise, second.
                         */
                        if (result != -1) {
                         
                        	System.out.println(searchKey + " was found at index position " + result + ": Recursive Binary Search");
                        
                        } else {
                        
                        	System.out.println(searchKey + " was not found: Recursive Binary Search");
                       
                        }   
                               
                        System.out.println("Time taken in nanoseconds: " + (timeNanoEnd - timeNanoStart));
                        System.out.println("Time taken in milliseconds: " + (timeMilliEnd - timeMilliStart));

                        System.out.println("\nRemaining elements during Recursive Linear Search:");
                        timeNanoStart = System.nanoTime(); // calling in-build method
                        timeMilliStart = System.currentTimeMillis();// calling in-build method
                        result = searchObj.recursiveLinearSearch(randomChars, 0, searchKey); // storing into a local variable
                        timeNanoEnd = System.nanoTime();// calling in-build method
                        timeMilliEnd = System.currentTimeMillis();// calling in-build method

                        /*
                         * if the key found it do the first block. Otherwise, second.
                         */
                        if (result != -1) {
                       
                        	System.out.println(searchKey + " was found at index position " + result + ": Recursive Linear Search");
                       
                        } else {
                        
                        	System.out.println(searchKey + " was not found: Recursive Linear Search");
                        }
                       
                        System.out.println("Time taken in nanoseconds: " + (timeNanoEnd - timeNanoStart));
                        System.out.println("Time taken in milliseconds: " + (timeMilliEnd - timeMilliStart));
                   
                    }
                  
                    break;
                
                case 3: // Iterative Binary and linear Search
                   
                	/*
                	 * if array size is not decide yet, it shows a customize error message 
                	 */
                     if (randomChars == null) {
                       
                    	 System.out.println("\nPlease initialize the array first (Option 1).");
                     
                     } else {
                    	 
                    	// local variable to check the value
                        boolean keyCheck = true;
                     	char searchKey = 0;       
                     	
                     	while(keyCheck) {
                     	try {
                     	System.out.print("\nPlease enter a character to search: ");
                         searchKey = searchObj.getScanner().nextLine().charAt(0); // charAt() to select the specific character from the word.
                         keyCheck = false; // loop closed
                     	} catch (Exception ex) { // exception handling
                     			System.out.print("\nThe argument is invalid. Please, Try Again !");
                     	}
                     	} 
                         
                     	// local variables
                         long startNanoTime;	
                         long endNanoTime;
                         long startMilliTime;
                         long endMilliTime;
                         int result;

                         System.out.println();
                         startNanoTime = System.nanoTime();   // calling in-build method   
                         startMilliTime = System.currentTimeMillis();  // calling in-build method
                         result = searchObj.iterativeBinarySearch(randomChars, searchKey); // storing into a local variable
                         endNanoTime = System.nanoTime();        // calling in-build method
                         endMilliTime = System.currentTimeMillis();  // calling in-build method

                         /*
                          * if the key found it do the first block. Otherwise, second.
                          */
                         if (result != -1) {      
                         
                        	 System.out.println(searchKey + " was found at index position " + result + ": Iterative Binary Search");
                        
                         } else {
                          
                        	 System.out.println(searchKey + " was not found: Iterative Binary Search");
                        
                         }
                         
                         System.out.println("Time taken in nanoseconds: " + (endNanoTime - startNanoTime));
                         System.out.println("Time taken in milliseconds: " + (endMilliTime - startMilliTime));
    
                         System.out.println();
                         startNanoTime = System.nanoTime();      // calling in-build method
                         startMilliTime = System.currentTimeMillis(); // calling in-build method
                         result = searchObj.iterativeLinearSearch(randomChars, searchKey); // storing into a local variable
                         endNanoTime = System.nanoTime();        // calling in-build method
                         endMilliTime = System.currentTimeMillis();  // calling in-build method

                         /*
                          * if the key found it do the first block. Otherwise, second.
                          */
                         if (result != -1) {
                           
                        	 System.out.println(searchKey + " was found at index position " + result + ": Iterative Linear Search");
                       
                         } else {
                         
                        	 System.out.println(searchKey + " was not found: Iterative Linear Search");
                         }
                       
                         System.out.println("Time taken in nanoseconds: " + (endNanoTime - startNanoTime));
                         System.out.println("Time taken in milliseconds: " + (endMilliTime - startMilliTime));
                     
                     }
                  
                    break;
               
                case 4: // program shut-down
                  
                    System.out.println("\nThe program is shut-down."); 
                    System.out.println("The program is made by Vasukumar Patel ");
                    searchObj.getScanner().close();  // closing scanner obj
                    System.exit(0); // closing the system
                    
                    break;
                   
                default: // default case for any other argument than a valid option (1 to 4)
                	
                    System.out.println("\nPlease choose a valid option (1 to 4).");
                    
                    break;
            }
        }
    }
    
    /**
     * static private method to print the menu
     */
    private static void printMenu() {
    	
    	System.out.println();
    	System.out.println("Select your option in the menu:");
        System.out.println("1. Initialize and populate an array of 32 random characters.");
        System.out.println("2. Perform recursive binary and linear search.");
        System.out.println("3. Perform iterative binary and linear search.");
        System.out.println("4. Exit.");
        System.out.print("Enter your choice: ");
    	
    }
}