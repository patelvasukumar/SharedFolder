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
 * make package and import class
 */
package mainPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import supportPackage.PostalCodeEntry;

/**
 * this class has main method which calls other methods
 */
public class PostalCodeSearchTest {
	
	/**
	 * parameterless constructor
	 */
	public PostalCodeSearchTest() {}
	
	/**
	 * main method
	 * @param args - arguments
	 */
	public static void main(String[] args) {
		
		/**
		 * fileName and storing it into path variables, and Map reference
		 */
		String filename = "OttawaCityPostalCodes.csv";
		Path file = Paths.get(filename);
		Map<String, PostalCodeEntry> map = new HashMap<>();
		
		/**
		 * try and catch block which will close BufferedReader
		 */
		try (BufferedReader input = Files.newBufferedReader(file)) {
			
			String line = null; /* string line */
			
			/**
			 * reading every line from the file
			 */
			while ((line = input.readLine()) != null) {
				
				String[] fields = line.split(","); // dividing and storing into an array
				
				/**
				 * putting all the things into a Map 
				 */
				if (fields.length >= 5) {
				
					PostalCodeEntry entry = new PostalCodeEntry(fields);
					map.put(entry.getPrefix(), entry);
				
				}
				
			}
		
		} catch (IOException ioException) { /* exception handling */
		
			System.err.println("Error opening file");
			ioException.printStackTrace();
		
		}

		/* for loop to print all the values and keys */ 
		for (int i = 0; i < 10; i++) {
			/* storing into variables */
			String key = PostalCodeEntry.getRandomPrefix();
			System.out.println("Retrieving: " + key);
			PostalCodeEntry result = map.get(key);
			
			/**
			 * if and else condition
			 */
			if (result != null) {
				System.out.print("\t" + result);
			} else {
				System.out.println("\tNOT found");
			}
		
		}
	
	}

}
