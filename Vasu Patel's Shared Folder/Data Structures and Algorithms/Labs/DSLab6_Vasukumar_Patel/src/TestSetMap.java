/**
 * Computer Engineering Technology-Computer Science: Level 3
 * 
 * Student Name: Vasukumar Patel
 * Student ID: 041153940
 * Program: Computer Engineering Technology - Computing Science
 * Course: Data Structure
 * Lab Section: 301
 * Professor: James Mwangi PhD.
 */

/**
 * importing Scanner
 */
import java.util.Scanner;

/**
 * this class is the driver class which calls the methods and run the program
 */
public class TestSetMap {

	/**
	 * parameterless constructor
	 */
	 public TestSetMap() {}
	
	 /**
	  * main method to run the code 
	  * @param args - arguments
	  */
	 public static void main(String[] args) {
	       
		 // Scanner and SetMapLib class reference
		 Scanner scanner = new Scanner(System.in);
	     SetMapLib setMapLibrary = new SetMapLib();

	     
	     	while (true) { // Continuous while loop
	           
	     		displayMenu(); // menu

	            String choice = scanner.nextLine(); // choice

	            switch (choice) {
	                
	            case "1": // open the file and read the data
	                   
	            	setMapLibrary.option1("customer.txt");
	                System.out.println("TreeMap is created ! ");
	                break;

	            case "2": // displays all the data
	                    
	            	setMapLibrary.option2();
	                break;
	                
	            case "3": // search for the key 
	                    
	            	System.out.print("Enter a key to search: ");
	                String option = scanner.nextLine();
	                    
	                /**
	                 * if it is integer, it is going to search key, otherwise value
	                 */
	                try {
	                     
	                	int keyToSearch = Integer.parseInt(option);
	                    setMapLibrary.option3(keyToSearch, null); 
	                    
	                } catch (Exception ex) { // exception handling
	                       
	                	setMapLibrary.option3(-1, option);
	                
	                }
	                   
	                break;
	                
	            case "4": // list all key and values without duplicates
	                    
	            	setMapLibrary.option4();
	                break;
	                
	            case "5": // list names in reverse order
	                    
	            	setMapLibrary.option5();
	                break;
	                
	            case "6": // add values
	                    
	            	System.out.print("Enter name: ");
	                String name = scanner.nextLine();
	                    
	                System.out.print("Enter key: ");
	                String keyInput = scanner.nextLine();
	                    
	                try {
	                        
	                	int key = Integer.parseInt(keyInput);
	                    setMapLibrary.option6(name, key);
	                    
	                } catch (Exception ex) { // check exception
	                        
	                	System.out.println("It is not valid. Please, enter again ! ");
	                    
	                }
	                break;
	                
	            case "7": // close the program
	                    scanner.close();
	                    System.out.println("\nThe program is shutdown, and it is made by Vasukumar Patel");
	                    System.exit(0);
	                    break;
	                
	            default: // if user select any-other option
	                    System.out.println("\nYou selected the wrong option ! ");
	                    break;
	            
	            }
	        }
	    }
	
	 /**
	  * displays all the menu
	  */
	 private static void displayMenu() {
	 
		 System.out.println("\n1. Create a TreeMap of data from the text file.");
	     System.out.println("2. Display the TreeMap.");
	     System.out.println("3. Search for a given key or value in the TreeMap.");
	     System.out.println("4. Create and display a keys TreeSet and a values TreeSet from the TreeMap.");
	     System.out.println("5. Sort and display values TreeSet in descending order.");
	     System.out.println("6. Add new key-value data to the TreeMap.");
	     System.out.println("7. Exit");
	     System.out.print("\nEnter your choice: ");
	 
	 }
	
}
