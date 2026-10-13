/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class is the parent class and contains attributes and methods for its child classes
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 * This project follows the factory class design pattern.
 * 
 */

/*
 * importing Scanner class for the scanner object
 */
import java.util.Scanner;

/**
 * this class is the driver class that class the method from factory class
 */
 public class Assign2 {
   
	 /**
	 * parameterless constructor
	*/
	public Assign2() {} 
	 
	/**
	 *  main() method to call the class according to the user input
	 * @param args - Array String parameter 
	 */
	public static void main(String[] args) {
		
		/**
    	 * object creation 
    	 */
		Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        while (true) {
            
        	displayMenu(); // print menu
            String choice = scanner.nextLine().trim();
            
            switch (choice) { // Comparing string with string 
                
            case "1": // adding book
                    library.addBook(scanner);
                    break;
                
            case "2": // displaying book
                    library.displayCatalog();
                    break;
                
            case "3": // borrowing book
                    library.borrowBook(scanner);
                    break;
                
            case "4": // returning book
                    library.returnBook(scanner);
                    break;
                
            case "5": // searching book
                    library.searchForBook(scanner);
                    break;
                
            case "6": // saving book
                    library.saveToFile(scanner);
                    break;
                
            case "7": // reading book
                    library.readFromFile(scanner);
                    break;
                
            case "8": // shutting down program
                    System.out.println("...Exiting...Program by Vasukumar Patel");
                    scanner.close();
                    System.exit(0);
                    break;
                
            default: // default
                    System.out.println("\nYou entered the wrong option !");
                    break;
            }
        }
    }
	
	private static void displayMenu() { // private static method 
		
		System.out.println("\nPlease select one of the following:");
	    System.out.println("1: Add Book to Library");
	    System.out.println("2: Display Current Library Catalog");
	    System.out.println("3: Borrow Book(s)");
	    System.out.println("4: Return Book(s)");
	    System.out.println("5: Search for Book");
	    System.out.println("6: Save Library Catalog to File");
	    System.out.println("7: Read Library Catalog from File");
	    System.out.println("8: To Exit");
	    System.out.print("> ");
		
	}
	
}

