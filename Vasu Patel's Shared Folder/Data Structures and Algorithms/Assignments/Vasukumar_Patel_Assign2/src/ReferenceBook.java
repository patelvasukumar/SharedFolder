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
 * this class has its personal attribute + it override its super class methods
 */
public class ReferenceBook extends Book {
    
	/**
	 * using final modifier to make the variable constant  for genre 
	 */
	private final String GENRE = "Reference";
    private String edition;
  
    /**
	 * parameterless that calls the super constructor
	 */
    public ReferenceBook() {
        super();
        this.genre = GENRE;
    }
    
    /**
     *  getter
     * @return - return the value
     */
    public String getEdition() { return edition; }
    
    /**
     *  setter
     * @param edition - set the value
     */
    public void setEdition(String edition) { this.edition = edition; }

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
        // formatted string
    	String message = String.format("Book: %d %s %d Author: %s Genre: %s Edition: %s",bookCode, title, quantityInStock, author, GENRE, edition);
    	return message; // return string
    	
    }

	/**
     * This overridden addBook method first calls the superclass’s addBook(scanner) 
     * to input common book details, and if successful, it adds an extra input step 
     * to collect the edition, then returns true; otherwise, it returns false.
     * 
     */
	@Override
	public boolean addBook(Scanner scanner, boolean file) {
	    genre = GENRE;

	    boolean result = super.addBook(scanner, file); // if works, return true or false

	    if (result) {
	        
	    	if (file) { // taking input from file, otherwise user
	          
	        } else {
	        
	        	System.out.print("Enter the edition of the book: ");
	        
	        }
	        
	    	edition = scanner.nextLine();  // taking input 
	    
	    } else {
	        
	    }

	    return result; // return true or false
	}

	
}