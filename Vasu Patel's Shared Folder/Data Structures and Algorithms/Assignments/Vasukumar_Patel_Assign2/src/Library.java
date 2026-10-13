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
 * importing Scanner class for the scanner object and other classes to read the file, handle the 
 * exception and more...
 * 
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *  Library class that is the factory class which generate object according to the user input
 */
public class Library {
    
	/*
	 * instance variables
	 */
	private ArrayList<Book> catalog = new ArrayList<>();
    private final int MAX_SIZE = 20;


    /**
     * parameterless constructor
     */
    public Library() {}

    /**
     *  this method adds books' details into the catalog array
     * 
     * @param scanner - scanner reference  
     * @return - return true if success. Otherwise, false
     */
    public boolean addBook(Scanner scanner) {
       
    	// Check max book limit
    	if (catalog.size() >= MAX_SIZE) {
            System.out.println("\nYou cannot add more books");
            return false;
        }  
    	
    	  // Ask user for book type
    	System.out.print("\nDo you wish to add a Fiction (f), Non-Fiction (n), or Reference (r) book? ");
        String type = scanner.nextLine().trim();
        
        Book bookFactory;
       
        // Create book object based on user input
        switch (type) {
            
        	case "f":
     
        			bookFactory = new FictionBook();
        			break;
            
        	case "n":
         
                	bookFactory = new NonFictionBook();
                	break;
            
        	case "r":
         
                	bookFactory = new ReferenceBook();
                	break;
            
        	default:
                	System.out.println("\nInvalid input. Please enter a valid option.");
                	return false;
        }
        
        // Input book code
        System.out.print("Enter the code for the book: ");
       
        if (!bookFactory.inputCode(scanner, false)) {
            System.out.println("Invalid book code.");
            return false;
        }
        
        // Check for duplicate book code
        for (Book book : catalog) {
                
        	if (book.getBookcode() == bookFactory.getBookcode()) {
                System.out.println("\nBook code already exists.");
                return false;
            }
        
        }
       
        // Add book details
        if (!bookFactory.addBook(scanner, false)) {
            System.out.println("Invalid book details.");
            return false;
        }
        
        // Add to catalog and sort
        catalog.add(bookFactory);
        Collections.sort(catalog);
        return true;
    
    }

    /**
     * returns nothing and display content
     */
    public void displayCatalog() {
       
    	System.out.println("\nLibrary Catalog:");
       
    	if (catalog.isEmpty()) {
            return;
        }
        
        for (Book book : catalog) {
            System.out.println(book);
        }
    
    }

    /**
     *  this method borrow the books from catalog
     * 
     * @param scanner - scanner reference 
     */
    public void borrowBook(Scanner scanner) {
        
    	System.out.print("\nEnter the code of the book to borrow: ");
        int code;
        
        try {
           
        	code = Integer.parseInt(scanner.nextLine());
       
        } catch (Exception ex) {
               
        	System.out.println("Invalid book code.");
            return;
        
        }
        // Search for the book by code
        int index = binarySearch(code, 0, catalog.size() - 1);
        
        if (index < 0) {
            System.out.println("Book not found in catalog.");
            return;
        }

        Book bookToBorrow = catalog.get(index);
        
        System.out.print("Enter the quantity to borrow: ");
        
        int quantity;
        try {
            
        	quantity = Integer.parseInt(scanner.nextLine());
        
        } catch (Exception ex) {
          
        	System.out.println("Invalid quantity.");
            return;
        
        }

        // Check quantity and update stock
        if (quantity <= 0) {
          
        	System.out.println("Quantity must be positive.");
            return;
          
        }

        if (bookToBorrow.updateQuantity(-quantity)) {
          
        	
       
        } else {
          
        	System.out.println("\nNot enough books");
    
        }
    
    }

    /**
     * this method returns the book
     * @param scanner - scanner reference 
     */
    public void returnBook(Scanner scanner) {
       
    	System.out.print("\nEnter the code of the book to return: ");
        int code;
        
        try {
          
        	code = Integer.parseInt(scanner.nextLine());
       
        } catch (Exception ex) {
          
        	System.out.println("Invalid book code.");
            return;
       
        }

     // Find the book
        int index = binarySearch(code, 0, catalog.size() - 1);
        
        if (index < 0) {
            System.out.println("Book not found in catalog.");
            return;
        }

        Book bookToReturn = catalog.get(index);
       
        System.out.print("Enter the quantity to return: ");
        
        int quantity;
       
        try {
          
        	quantity = Integer.parseInt(scanner.nextLine());
        
        } catch (Exception ex) {
         
        	System.out.println("Invalid quantity.");
            return;
        
        }

        // Return books
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        bookToReturn.updateQuantity(quantity);
    
    }

    
    /**
     * Searches for a book by its code and displays it.
     * 
     * @param scanner Scanner object for user input
     */
    public void searchForBook(Scanner scanner) {
       
    	System.out.print("\nEnter the code for the book: ");
        int code;
        
        try {
         
        	code = Integer.parseInt(scanner.nextLine());
        
        } catch (Exception ex) {  
          
        	System.out.println("Code not found in catalog...");
            return;
       
        }
       
        int index = binarySearch(code, 0, catalog.size() - 1);
       
        if (index < 0) {
       
        	System.out.println("Code not found in catalog...");
       
        } else {
         
        	System.out.println(catalog.get(index));
        
        }
    }

    /**
     * Performs binary search on the catalog for a book code.
     * 
     * @param code Book code to search
     * @param low  Start index
     * @param high End index
     * @return Index of found book or -1 if not found
     */
    private int binarySearch(int code, int low, int high) {
       
    	if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;
        Book book = catalog.get(mid);

        if (book.getBookcode() == code) {
            return mid;
        }

        if (book.getBookcode() < code) {
         
        	return binarySearch(code, mid + 1, high);
        
        } else {
        
        	return binarySearch(code, low, mid - 1);
       
        }
    
    }
    
    /**
     * Skips 'n' lines in a BufferedReader.
     * 
     * @param reader BufferedReader to read from
     * @param n      Number of lines to skip
     * @throws IOException if reading fails
     */
    private String dataNext(BufferedReader reader) throws IOException {
        
    	String line;
       
    	while ((line = reader.readLine()) != null) {
            
    		if (!line.trim().isEmpty()) {
            	return line.trim();
            }
        
    	}
       
        return null;
    
    }

    /**
     * 
     * @param reader - reference
     * @param n - integer parameter
     * @throws IOException - exception handling
     */
    private void dataSkip(BufferedReader reader, int n) throws IOException {
      
    	for (int i = 0; i < n; i++) {
            reader.readLine(); 
        }
    
    }


    /**
     * Saves all books in the catalog to a file.
     * 
     * @param scanner Scanner object for filename input
     */
    public void saveToFile(Scanner scanner) {
        
    	System.out.print("\nEnter the filename to save to: ");
        String fileName = scanner.nextLine();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            
        	for (Book book : catalog) {
                
        		writer.write(book.genre); 
        		writer.newLine();
                
        		writer.write(Integer.toString(book.bookCode)); 
        		writer.newLine();
                
        		writer.write(book.title); 
        		writer.newLine();
                
        		writer.write(book.author); 
        		writer.newLine(); 
                
        		writer.write(Integer.toString(book.quantityInStock)); writer.newLine(); 
                
        		 // Write extra data if needed
                if (book instanceof NonFictionBook) {
                 
                	writer.write(((NonFictionBook)book).getFieldOfStudy());
                    writer.newLine();    
                
                } else if (book instanceof ReferenceBook) {
                 
                	writer.write(((ReferenceBook)book).getEdition());
                    writer.newLine();
                
                }
            
        	}
        
        } catch (IOException e) {
            System.out.println("\nError while saving files ! ");
        }
    
    }

    /**
     * Loads books from a file and updates the catalog.
     * 
     * @param scanner Scanner object for filename input
     */
    public void readFromFile(Scanner scanner) {
       
    	System.out.print("\nEnter the filename to read from: ");
        String fileName = scanner.nextLine();
        ArrayList<Book> tempCatalog = new ArrayList<>(); 
       
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            
        	String type;
            
        	// Read and process each book
        	while ((type = dataNext(reader)) != null) {
                
        		Book book;
                switch (type.toLowerCase()) { 
                    
                case "fiction":       
                	
                	book = new FictionBook();     
                	break;
                    
                case "non-fiction":   
                	
                	book = new NonFictionBook();  
                	break;
                    
                case "reference":     
                	
                	book = new ReferenceBook();   
                	break;
                    
                default:
                        
                    dataSkip(reader, 4); 
                    continue;
                
               }

                // Read lines from file
                String codeLine   = dataNext(reader);
                String titleLine  = dataNext(reader);
                String authorLine = dataNext(reader); 
                String countLine    = dataNext(reader);
                String extraLine = null; 
                
                if (book instanceof NonFictionBook || book instanceof ReferenceBook) {
                    extraLine = dataNext(reader);
                }

                // Validate
                if (codeLine==null || titleLine==null || authorLine==null || countLine==null ||
                   
                		((book instanceof NonFictionBook || book instanceof ReferenceBook) && extraLine==null)) {
                    	System.out.println("Error : while reading the file");
                    	return; 
                }

                if (extraLine == null) {
                    extraLine = "";
                }
                
                // Use scanner to parse book
                Scanner scan = new Scanner(String.join("\n", codeLine, titleLine, authorLine, countLine, extraLine));
                
                boolean result = book.inputCode(scan, true);
                
                if (result) {
                    result = book.addBook(scan, true);
                }
                
                scan.close();

                if (!result) {
                    return; 
                }

             // Check duplicate
                if (binarySearch(book.getBookcode(), 0, catalog.size() - 1) != -1) {
                	System.out.println("\nBook code already exists\nError Encountered while reading the file");
                    return; 
                }
                
                for (Book existingTemp : tempCatalog) {
                    
                	if (existingTemp.getBookcode() == book.getBookcode()) {
                        System.out.println("\nBook code already exists\nError Encountered while reading the file");
                        return; 
                    }
                
                }

                tempCatalog.add(book); 
            }
           
        	// Load all books
            catalog.clear();
            catalog.addAll(tempCatalog);
            Collections.sort(catalog);
            System.out.println("\nCatalog loaded successfully.");
       
        } catch (FileNotFoundException e) {
          
        	System.out.println("\nFile Not Found Error");
        
        } catch (IOException e) {
          
        	System.out.println("\nI/O error while reading file");
       
        }
    
    }

}