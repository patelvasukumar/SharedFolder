/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/*
 * importing java Scanner class
 */
import java.util.Scanner;
import java.util.Arrays;

/**
 * this class has binary search and linear search methods that use recursion 
 * Also, valid input and printing array method for primitive type
 */
public class Searching {
    
	/**
	 *  parameterless constructor 
	 */
	public Searching() {}
	
	 /**
	  * This method calls binary recursion method and take valid search value from another method
	  * @param array - passing array reference 
	  * @param scanner - passing scanner reference
	  */
	  public void binarySearch(int[] array, Scanner scanner) {
	       
		    int key = validSearchValue(scanner);	// taking valid search value
	        int index = binarySearchRecursive(array, 0, array.length - 1, key);	// taking index value
   
	        System.out.println(Arrays.toString(array));	// printing array 
	        if (index == -1) {    // printing message according to the founded index
	            System.out.println("\nThe element is not in the array");
	        } else {	// printing value and index
	            System.out.println("\n" + key + " was found at index position " + index + " : Recursive Binary Search");
	        }
	    
	  }

	  /**
	   *  This method use recursion to do binary search
	   * 
	   * @param array - passing reference
	   * @param low - lower boundary
	   * @param high - last boundary
	   * @param key - search key
	   * @return - return index
	   */
	    private int binarySearchRecursive(int[] array, int low, int high, int key) {
	       
	    	if (low > high) {  // base case
	            return -1; // return -1
	        }
	       
	    	int mid = low + (high - low) / 2;	// mid point
	       
	    	if (array[mid] == key) {
	           
	    		return mid; // return mid
	       
	    	} else if (key < array[mid]) { // if key is lower than mid point
	        
	        	return binarySearchRecursive(array, low, mid - 1, key); // return method
	       
	        } else { //if higher
	         
	        	return binarySearchRecursive(array, mid + 1, high, key); // return method
	       
	        }
	    
	    }

	    /**
	     * 	This method use linear search to find the element's index
	     * 
	     * @param array - reference 
	     * @param scanner - reference
	     */
	    public void linearSearch(int[] array, Scanner scanner) {
	      
	    	int key = validSearchValue(scanner); // validation method
	        linearSearchRecursive(array, 0, key); // calling recursive linear search
	   
	    }

	    /**
	     *  use recursion to find the element
	     * 
	     * @param array reference
	     * @param index - index value
	     * @param key - search element
	     */
	    private void linearSearchRecursive(int[] array, int index, int key) {
	      
	    	
	    	if (index >= array.length) { // if index is more than array's length
	          
	    		System.out.println(Arrays.toString(array));
	            System.out.println("\nThe element is not in the array");
	            return;
	        
	    	} else if (array[index] == key) {   // if index's value is == key
	          
	    		System.out.println(Arrays.toString(array));
	            System.out.println("\n" + key + " was found at index position " + index + " : Recursive Linear Search");
	            return;
	        
	    	}
	     
	    	linearSearchRecursive(array, index + 1, key); // recursion
	    
	    }

	/**
	 * checking value and returning
	 * 
	 * @param scanner - reference
	 * @return - return index
	 */
    private static int validSearchValue(Scanner scanner) {
       
    	int value = 0;
    	boolean loop = true;
        
    	while (loop) { // looping if value is not valid
            
    		try {
              
    			System.out.print("\nPlease enter an integer value to search:");
                value = Integer.parseInt(scanner.nextLine().trim()); // from string to int, taking value
                loop = false; // looping false
           
    		} catch (Exception ex) { // error message if false value
             
    			System.out.println("\nYou entered a wrong input. Please, try again");
           
    		}
        
    	}
    	
    	return value; // return value
   
    }
    
    /**
     * this method prints array for primitive type int
     * 
     * @param array - reference
     */
    public void printArray(int[] array) {
        
    	System.out.print("[");     
        
    	for (int i = 0; i < array.length; i++) { // for loop 
           
    		if (i == array.length - 1) { // condition
               
    		  System.out.print(array[i]);
           
    		} else {
            
              System.out.print(array[i] + ","); // condition
            
            }
        
    	}
        
    	System.out.print("]\n"); // the last box to print
    
    }
   
}
