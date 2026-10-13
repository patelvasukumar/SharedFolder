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
public class NonFictionBook extends Book {
    
	/*
	 * using final modifier to make the variable constant  for genre 
	 * extra variable fieldOfStudy
	 */
	private final String GENRE = "Non-Fiction";
    private String fieldOfStudy;
  
    /**
	 * parameterless that calls the super constructor
	 */
    public NonFictionBook() {
        super();
        this.genre = GENRE;
    }
    
    /**
     *  getter
     * @return - returns the value
     */
    public String getFieldOfStudy() { return fieldOfStudy; }
    
    /**
     * setter
     * @param fieldOfStudy - set the value
     */
    public void setFieldOfStudy(String fieldOfStudy) { this.fieldOfStudy = fieldOfStudy; }

	@Override
    public String toString() {
        
		// formatted string
    	String message = String.format("Book: %d %s %d Author: %s Genre: %s Field of Study: %s",bookCode, title, quantityInStock, author, GENRE, fieldOfStudy);
    	return message; // return the string
    
    }

	 /**
     * This overridden addBook method first calls the superclass’s addBook(scanner) 
     * to input common book details, and if successful, it adds an extra input step 
     * to collect the fieldOfStudy, then returns true; otherwise, it returns false.
     * 
     */
    @Override
    public boolean addBook(Scanner scanner, boolean file) {
       
        boolean result = super.addBook(scanner, file); // true if works. otherwise, false
        
        if (result) {
            
        	if (!file) { // if the input is not from file
            
            	System.out.print("Enter the field of study: ");
          
            }
         
            fieldOfStudy = scanner.nextLine(); // taking the input either from file or user
       
        }
       
        return result; // return true or false
    
    }

}