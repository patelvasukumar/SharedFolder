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
 * importing all classes
 */
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * this class contains different methods for different operation on TreeMap
 */
	public class SetMapLib {

	/**
	 * TreeMap attribute : Integer - key (customer number) and String - Value (customer name)
	 */
	private TreeMap<Integer,String> customerTree;
	
	/**
	 * parameterless constructor which is invoking TreeMap's constructor
	 */
	public SetMapLib() {
		customerTree = new TreeMap<>();
	}
	
	/**
	 * this method opens the file and read and stores the data into variables and array
	 * @param fileName - providing file name
	 */
	public void option1(String fileName) {
		
		/**
		 * used try catch to catch exception and to close bufferreader
		 */
			try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
				
				String line; 
				
					/**
					 * reading every line until the file finish 
					 */
					while((line = reader.readLine()) != null) {
						
						  String[] data = line.split(","); // storing each data set into array which is split by ','.
						  
						  /**
						   * making condition that every line must have two data sets which are customer number and name
						   */
						  if(data.length == 2) {
							  // Storing values into variables and then setting them into the TreeMap
							  String name = data[0].trim(); 
							  int value = Integer.parseInt(data[1].trim());
							  customerTree.put(value, name);
							  
						  }
						
					}
			
					/**
					 * Handling file exception
					 */
			} catch (FileNotFoundException ex) {
			
				System.out.println("\nThe file is not on the system ! ");
			
				/**
				 * any other exception
				 */
			} catch (Exception ex) {
			
				System.out.println("\nThere is something wrong ! ");
			
			}
			
	}
	
	/**
	 * displaying all the data of TreeMap
	 */
	public void option2() {
		
		// if there is no data
		if(customerTree.isEmpty()) {
		
			System.out.println("\nThere is no data in the tree ! ");
		
		} else { // if there is data
			
			for(
				
					Map.Entry<Integer,String> map : customerTree.entrySet()	
					
				) { // used for each loop to get key and values
			
			System.out.println("Customer Number (Key) :" + map.getKey() + ", and Customer Name (Value) : " + map.getValue());
			
			}
			
		}
			
	}
	
	/**
	 * search for the keys and values
	 * @param key - key parameter
	 * @param value - value parameter
	 */
	public void option3(int key, String value) {
		
		// if user enters int value, it checks that does key it is int the Tree or not
		if(key != -1) {
			
			if(customerTree.containsKey(key)) { // calls in-builded method contains-key
				
				System.out.println("Key " + key + " found");
				
			} else {
				
				System.out.println("Key " + key + " was not found");
				
			} 
			
			
		} else if (value != null) { // if user enter string it checks whether the value is in the Tree or not
			
			boolean found = false;
			
			 for (
			        Map.Entry<Integer, String> entry : customerTree.entrySet()
			      ) {  // for each loop to check all the tree values
	               
				 if (entry.getValue().equalsIgnoreCase(value)) {
	                    System.out.println("Value found: " + entry.getValue() + " at Key : " + entry.getKey());
	                    found = true;
	                }
	           
			 }
	            
			 if (!found) { // if not found 
	                System.out.println(value + " not found ");
	            }
			
		}
		
		
		
	}
	
	/**
	 *  this method list all the keys and values without any duplicates in right order 
	 */
	public void option4() {
		
		if(customerTree.isEmpty()) {
			
			System.out.println("\nThere is no data in TreeMap.");
		
		} else {
			
			TreeSet<Integer> noKeyDupli = new TreeSet<>(customerTree.keySet());
			TreeSet<String> noValueDupli = new TreeSet<>(customerTree.values());
			
			System.out.println("Key TreeSet is " + noKeyDupli);
			System.out.println("Name TreeSet is " + noValueDupli);
			
		}
		
		System.out.println("\nTreeSet removes repeated values and keeps the elements in order.");
		System.out.println("In contrast, a TreeMap lets you have repeated values but not repeated keys, because each key is linked to only one value.");
		System.out.println("This means that if many items in the TreeMap have the same value, the TreeSet will keep only one of those values.");
		
	}
	
	/**
	 * this method sorts everything and use comparator class to sort and make it reverse
	 */
	public void option5() {
		
		if(customerTree.isEmpty()) {
			
			System.out.println("There is no data in TreeMap.");
			
		} else {
			
			// setting all values into array and reversing the names
			  ArrayList<String> valuesList = new ArrayList<>(customerTree.values());
		      Collections.sort(valuesList, new NameTreeDown());

		      // using for-each loop to print
		        System.out.println("Values sorted in descending order:");
		        for (String name : valuesList) {
		            System.out.println(name);
		        }
				
		}
		
	}
	
	/**
	 * this method adds the values into tree
	 * @param name - parameter name
	 * @param key - parameter key
	 */
	public void option6(String name, int key) {
		
		// if key is already there, it updates values, and if it is not there, it adds new key and values
		if (customerTree.containsKey(key)) {
			
			System.out.println("A key is already in the system ! Updating the Value ! ");
			
		} else {
		
			System.out.println("New Data has been added to the system with new key ! ");
			
		}
		
		customerTree.put(key,name);
		
	}
	
	
	
	
}


/**
 * this class override compare method and implements comparator interface String generic type
 */
	class NameTreeDown implements Comparator<String> {

	/**
	 * parameterless constructor
	 */
	public NameTreeDown() {}
	
	/**
	 * method has two parameters : both are Strings 
	 * it returns an integer value which one is higher and which one is lower
	 */
	@Override
	public int compare(String name1, String name2) {
		
		/**
		 * returns 0 if same; 0 < if second one is bigger; 0 > if first one is higher
		 */
		 return name2.compareToIgnoreCase(name1);
		
	}  
	
}

