package library;

import java.util.Scanner;

public class LibraryManagement {
   
	public static void main(String[] args) {
        
    	Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        BookFactory factory = new BookFactory();
        boolean running = true;

        while (running) {
      
            System.out.println("\n==== Library Management System ====");
            System.out.println("1 - Add a book");
            System.out.println("2 - View All Books");
            System.out.println("3 - Search for a Book");
            System.out.println("4 - Exit");
            System.out.print("Select your choice: ");

            try {
               
            	int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                  
                case 1:
                        System.out.print("Enter book type (FictionBook/NonFictionBook): ");
                        String type = scanner.nextLine();
                       
                        Book book = factory.getBook(type);
                       
                        if (book != null) {
                        
                        	book.readDetails();
                            library.addBook(book);
                            System.out.println("Book added successfully!");
                        
                        } else {
                        
                        	System.out.println("Invalid book type.");
                       
                        }
                   
                        break;
                   
                case 2:
                        System.out.println("\n--- All Books ---");
                       
                        if (library.getAllBooks().isEmpty()) {
                       
                        	System.out.println("No books in the library.");
                     
                        } else {
                       
                        	for (Book b : library.getAllBooks()) {
                                System.out.println(b.toString());
                                System.out.println("----------------------");
                       
                        	}
                      
                        }
                  
                        break;
                  
                case 3:
                        System.out.print("Enter title to search: ");
                        String title = scanner.nextLine();
                      
                        Book found = library.searchByTitle(title);
                        
                        if (found != null) {
                        
                        	System.out.println("Book found:\n" + found.toString());
                        
                        } else {
                        
                        	System.out.println("No book found with that title.");
                        
                        }
                        
                        break;
                  
                case 4:
                        running = false;
                       
                        System.out.println("Exiting...");
                        
                        break;
                    
                default:
                        System.out.println("Invalid option. Please select again.");
                }
           
            } catch (NumberFormatException e) {
             
            	System.out.println("Please enter a valid number for your choice.");
           
            } catch (Exception e) {
             
            	System.out.println("An error occurred: " + e.getMessage());
            
            }
        
        }
        
        scanner.close();
    
    }
}