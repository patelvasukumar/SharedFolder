/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/*
 * importing packages and classes from the Junit and Java library 
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

/*
 * Junit class contains the test methods to test all methods
 * 
 */
class Numtests {

	/*
	 * private parameterized constructor 
	 */
	private Numtests() {}
	
	/*
	 * private variables. So, other class cannot access.
	 */
	private Scanner scanner = new Scanner(System.in);
	private Numbers numbers;
	
	/*
	 * this method test whether  min, max and mod added to the string or not. 
	 * 
	 */
	@Test
	void testFindMinMax() {
		
		numbers = new Numbers(4);	// setting size 4
		numbers.setNumbers(new Float[] {1f,2f,3f,4f}); // adding 4 values to the array
		numbers.setNumItems(4); // adding the index size 
		
		String result = "Minimum value is 1.0 , Maximum value is 4.0 , max mod min is 0.0";
		// expected string
		
		assertEquals(result,numbers.findMinMax()); // calling the findMinMax method and checking
													// both result
	}
	
	/*
	 * this method test. if size is zero, the method put zero for min, max and mod.
	 * 
	 */
	@Test
	void testFindMinMaxZero() {
		
		numbers = new Numbers(0); // setting the size to zero
		numbers.setNumItems(0); // setting index size
		
		String result = "Minimum value is 0.0 , Maximum value is 0.0 , max mod min is 0.0";
		// expected string 
		
		assertEquals(result,numbers.findMinMax()); // calling both method and checking both.
		
	}
	
	/*
	 * this test method checks whether, it is finding the max value and 
	 * finding the factorial or not.
	 * 
	 */
	@Test
	void testGetFactorialMax() {
		
		numbers = new Numbers(3); // setting the size to 3
		numbers.setNumbers(new Float[] {1f,2f,3f}); // making new array and adding values 
		numbers.setNumItems(3); // index size is 3
		
		assertEquals(6,numbers.getFactorialMax()); // calling the method and checking whether 
												// is it finding the max value and doing factorial on it or not.  
		
	}
	
	/*
	 * this test method checks whether it sets min, max and mod value to zero or
	 * not if the size is zero.
	 * 
	 */
	@Test
	void testGetFactorialMaxZero() {
		
		numbers = new Numbers(0); // setting size zero
		numbers.setNumItems(0); // setting index size zero
		
		assertEquals(0,numbers.getFactorialMax()); // getting zero value as expected after calling the method
		
	}
	
	/*
	 * this method checks whether calcAverage method returns a average value
	 * of the array in float or not.
	 * 
	 */
	@Test
	void testCalcAverage() {
		 
		numbers = new Numbers(3); // setting the size to 3
		numbers.setNumbers(new Float[] {1f,2f,3f}); // making an array and putting the values 
		numbers.setNumItems(3); // setting the index value
		
		assertEquals(2.0,numbers.calcAverage()); // check that is it returning average value or not.
		
	}
	
	/*
	 * this method checks if the size is zero, is it returning zero average value
	 * or not.
	 * 
	 */
	@Test
	void testCalcAverageZero() {
		
		numbers = new Numbers(0); // setting zero size
		numbers.setNumItems(0); // 0 index number
		
		assertEquals(0.0,numbers.calcAverage()); // check if the size is zero, is it returning zero or not.
		
	}
	
	/*
	 * this method checks that the value is adding to the array 
	 * or not.
	 * 
	 */
	 @Test
	    void testAddValueNormal() {
	       
		 numbers = new Numbers(1); // setting size 1
	     scanner = new Scanner("5.0"); // putting the string value into scanner
	     numbers.addValue(scanner); // calling the add value method
	     
	     assertEquals(1, numbers.getNumItems()); // checking the index is it 1 or not after adding the value.
	     assertEquals(5.0f, numbers.getNumbers()[0]); // checking the value by calling the array with index 0.
	     											// is it 5 float or not.
	    
	 }
	 
	 /*
	  * this method checks that the value is not adding to the array if
	  * it is full.
	  * 
	  */
	 @Test
	    void testAddValueArrayFull() {
	        
		 numbers = new Numbers(0); // setting the size that is zero
	     scanner = new Scanner("2.0\n"); // string which is value 2
	     numbers.addValue(scanner); // calling the method
	       
	        assertEquals(0, numbers.getNumItems()); // checking that the array is full
	        										// then tried to add value. Whether is it 
	        										// zero or not.
	   
	 }
	
	
}
