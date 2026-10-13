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
 * This class contains the methods and attributes for Book which is parent class
 * 
 */
public class Book implements Comparable<Book> {
    
	/**
	 *  attributes with protected to allow access to the child classes
	 */
	protected int bookCode;
    protected String title;
    protected String author;
    protected String genre;
    protected int quantityInStock;
    protected int initialQuantity;


    /**
     *  parameterless constructor 
     */
    public Book() {}
    
    /**
     *  getter
     * @return - return book code
     */
    public int getBookcode() { return bookCode; }
    /**
     *  setter
     * @param bookCode - set the book code
     */
    public void setBookCode(int bookCode) { this.bookCode = bookCode; }

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
        
		// formatted String 
    	String message = String.format("Book: %d %s %d Author: %s Genre: %s",bookCode, title, quantityInStock, author, genre);
        return message; // return the message
    
    }

	
	 /**
     * Updates the amount if the result is non-negative.
     *  
     * @param amount - take the amount which is int
     * @return - return true if positive and false if negative 
     */
    public boolean updateQuantity(int amount) {
        
    	if (quantityInStock + amount < 0) {
           
    		// if condition that use operators to check the condition
    		return false; // return false
        
    	} else {
            quantityInStock = quantityInStock + amount;	// adding the values
        }
        return true;	// return true
    }

    /**
     * Checks if two books have the same title and author (case-insensitive)
     * 
     * @param book - take book class reference as parameter
     * @return - return true and false according to the condition 
     */
    public boolean isEqual(Book book) {
     
    	boolean result;
    	result = (this.title.equalsIgnoreCase(book.title) && this.author.equalsIgnoreCase(book.author));
    	return result;
    
    }

    /**
     *  Reads and sets book title, author, and 
     *  a valid positive quantity from user input
     * 
     * @param scanner - taking scanner reference as parameter
     * @param file - if file exists, method takes input from file 
     * @return - return true if success. Otherwise false
     */
    public boolean addBook(Scanner scanner, boolean file) {
       
    	if (file) { // taking input  from file otherwise from user 
         
        	title = scanner.nextLine();
            author = scanner.nextLine();
        
        } else {
          
        	System.out.print("Enter the title of the book: ");
            title = scanner.nextLine();
            System.out.print("Enter the author of the book: ");
            author = scanner.nextLine();
       
        }

        int quantity = 0;
       
        try {
           
        		if (file) {
        			
        			 // quantity will be read from file
          
        		} else {
          
        			System.out.print("Enter the quantity of the book: ");
          
        		}
         
        		quantity = Integer.parseInt(scanner.nextLine()); // from user if not from file
        
        } catch (Exception ex) {
         
        	return false; // zero error message and return false 
       
        }

        if (quantity <= 0) {
         
        	return false; // return false if the quantity goes down 0
       
        } else {
          
        	quantityInStock = quantity;
            initialQuantity = quantity;
        
        }

        return true; // return true
    }

    /**
     * Reads and sets a valid integer book code from user input
     * 
     * @param scanner - taking Scanner reference as object
     * @param file - takes input from file if it is there 
     * @return - if successful return true. Otherwise false
     */
    public boolean inputCode(Scanner scanner, boolean file) {
       
    	int code = 0;
      
    	try {
          
    		code = Integer.parseInt(scanner.nextLine()); // taking code from user
    		bookCode = code;
       
    	} catch (Exception ex) {
         
    		return false; // zero error message but returns false when fail 
    	
    	}
       
        return true; // returns true
    
    }

    /**
     * overriding compareTo method to compare objects and .Sort() method uses it to comapre
     * objects 
     */
    @Override
    public int compareTo(Book book) {
        
    	if (this.bookCode < book.bookCode) { // comparing two objects
           
    		return -1;
        
    	} else if (this.bookCode > book.bookCode) { // // comparing two objects
          
    		return 1;     
        
    	} else {
        
    		return 0; // default if both above not works
           
    	}
    
    }
  
}