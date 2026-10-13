/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contain methods and the process for binary and linear search
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/*
 *  SecureRandom class for random character generation
 *  Scanner for taking user's input
 *  Arrays for sort in-build method
 */
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Arrays;

/**
 * this class has all the methods that for linear and binary search in two way. 
 * 1 : iteration 
 * 2 : recursion
 * 
 * Also, it has method to generate random character and printing them on console.
 * 
 */
public class BinaryLinearSearch {
    
	/**
	 *  private variables
	 */
	private char[] randomChars;
    private Scanner scanner = new Scanner(System.in);
    private char key;
       
    /** getter for randomChars
     * @return - returning the array's character
     */
    public char[] getRandomChars() { return randomChars; }
    /** setter for randomChars
     * @param randomChars - setting the value for array's character 
     */
    public void setRandomChars(char[] randomChars) { this.randomChars = randomChars; }

    /** getter for Scanner
     * @return - scanner reference
     */
	public Scanner getScanner() { return scanner; }
	/**setter for scanner
	 * @param scanner - reference of Scanner
	 */
	public void setScanner(Scanner scanner) { this.scanner = scanner; }
	
	/**
	 * Parameterless Constructor
	 */
	public BinaryLinearSearch() {}
  
	/**
	 * this method generate random 32 characters into an array and returns nothing 
	 */
    public void generateRandomChars() {
        
    	SecureRandom secureRandom = new SecureRandom(); // Reference generation
        randomChars = new char[32]; // array size set 

        for (int i = 0; i < randomChars.length; i = i + 1) { // looping
            
        		int alphaType = secureRandom.nextInt(2); // adding int values according to the type Upper or Lower
               
        		if (alphaType == 0) { // if Upper case this block works
            
        			randomChars[i] = (char) (secureRandom.nextInt(26) + 'A');
           
        		} else { // if lower case this block works
                     
        			randomChars[i] = (char) (secureRandom.nextInt(26) + 'a');
            
        		}
        
        }

        System.out.print("\nUnsorted array: [");
        /*
         * printing unsorted array 
         */
        for (int i = 0; i < randomChars.length; i = i + 1) {
          
        	System.out.print(randomChars[i]); 	// printing the array's element through iteration
            
        	 		if (i < randomChars.length - 1) {
        					System.out.print(", ");
        				}
        
        }
           
        System.out.println("]");
        // calling .sort() method
        Arrays.sort(randomChars);

        System.out.print("Sorted array: [");
        /*
         * printing sorted array
         */
        for (int i = 0; i < randomChars.length; i = i + 1) {
         
        	System.out.print(randomChars[i]); // printing the array's element through iteration.
           
        			if (i < randomChars.length - 1) {
              
        					System.out.print(", ");
          
        				}
       
        }
        
        System.out.println("]");

    }

    /**
     *  this method prints the remaining characters of the array
     * 
     * @param array - passing array as an parameters 
     * @param low - passing lowest value
     * @param high - passing highest value
     */
    public void remainingElements(char[] array, int low, int high) {
       
    	System.out.print("[");
       
    	for (int i = low; i <= high; i = i + 1) { // looping
         
    			System.out.print(array[i]); // printing the array's value through iteration
          
    			if (i < high) {
    				System.out.print(", ");
    			}
        
    	}
           
    	System.out.println("]");
   
    }

    /**
     *  this method do Binary Serach through Iteration. we need a sorted array for binary search.
     * 
     * @param array - passing array as an parameter 
     * @param key - passing the search key through parameter
     * @return - it return an integer that gives the number of the found character's index
     * 
     * 	if the method does not found, it returns -1.
     * 
     */
    public int iterativeBinarySearch(char[] array, char key) {
       
    		this.key = key;
    		int low = 0;
    		int high = array.length - 1;
        
    
    		while (low <= high) { // looping 
    			
    			int mid = ( low + (high - low) / 2 );

    				if (array[mid] == this.key) {
            	
    						return mid; // returning the index value if it is found on middle
           
    				} else {
                
    							if (array[mid] < this.key) {
                  
    								low = mid + 1; // increasing the lower boundary value by adding one and middle index value
              
    							} else {
                
    								high = mid - 1; // decreasing the higher boundary value by subtracting middle value with one
                
    							}
            	}
        }
        
        return -1; // retuning -1 if not found.
    
    }

    /**
     *  this method do Binary Search through recursion. we need a sorted array for binary search.
     * 
     * @param array - passing an array as a parameter 
     * @param first - passing the first value
     * @param last - passing the last value 
     * @param key - passing the search key character
     * @return - return the index where character found or return -1 if not found
     */
    public int recursiveBinarySearch(char[] array, int first, int last, char key) {
       
    		this.key = key;
    	
    		/*
    		 * base condition or termination condition.
    		 */
    		if (first > last) {
    			return -1;
    		}

    		remainingElements(array, first, last); // calling the method

    		int mid = ( first + (last - first) / 2 );

    		/*
    		 * if the character found on middle the first block works, otherwise, other block works
    		 * according to the condition.
    		 */
    		if (array[mid] == this.key) {
        
    				return mid; // returning the index
       
    		} else {
            
    			if (array[mid] < this.key) {
    				// calling the same method
    				return recursiveBinarySearch(array, mid + 1, last, this.key);
           
    			} else {
    				// calling the same method
    				return recursiveBinarySearch(array, first, mid - 1, this.key);
           
    			}
    		}
   
    }

    /**
     *  this method do Linear search through iteration. Does not require sorted array.
     * 
     * @param array - passing array as a parameter 
     * @param key - search key character passing as a parameter
     * @return - it return the index number where the key found. If not found, it returns -1.
     */
    public int iterativeLinearSearch(char[] array, char key) {
        
    	this.key = key;

    		for (int i = 0; i < array.length; i = i + 1) { // looping 
         
    			if (array[i] == this.key) {
    				return i; // returning the index number
    			}
      
    		} 
      
    		return -1; // if not found, returning the -1.
    
    }

    /**
     *  this method do Linear search through recursion. Does not require sorted array.
     * 
     * @param array - passing array as a parameter
     * @param index - passing index as a parameter 
     * @param key - search key character passing as a parameter
     * @return - it return the index number where the key found. If not found, it returns -1.
     */
    public int recursiveLinearSearch(char[] array, int index, char key) {
       
    		this.key = key;
    	 /*
    	  * base condition or termination condition.
    	  */
    		if (index >= array.length) {
    			return -1; // if not found, returning the -1.
    		}  
        
    		/*
    		 * self- calling if index is zero
    		 */
    		if (index == 0) {
    			
    			remainingElements(array, 0, array.length - 1);
    		
    		}    

    		/*
    		 * self - calling  according to the condition 
    		 */
    		if (array[index] == this.key) {
    			return index;
    		} else {
    			return recursiveLinearSearch(array, index + 1, this.key);
    		}
    
    }

}