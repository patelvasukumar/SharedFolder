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
public class FictionBook extends Book {
   
	/**
	 * using final modifier to make the variable constant 
	 */
	private final String GENRE = "Fiction";

	/**
	 *  parameterless that calls the super constructor
	 */
    public FictionBook() {
        super();
        this.genre = GENRE;
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
       
    	// formatted string
    	String message = String.format("Book: %d %s %d Author: %s Genre: %s",bookCode, title, quantityInStock, author, GENRE);
    	return message; // return string
    	
    }

    /**
    *
    *  The method overrides the addBook method and simply calls the 
    *  superclass's addBook(scanner) method, 
    *  returning true if it succeeds, otherwise false.
    *
    */
    @Override
    public boolean addBook(Scanner scanner, boolean file) {
       
        return super.addBook(scanner, file); // return true if works otherwise false
    
    }

}