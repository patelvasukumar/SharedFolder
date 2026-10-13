/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Vasukumar Patel
 * Student Number: 041153940    
 * Section #: 310
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/**
 * importing build-in methods from arrays class
 */
import java.util.Arrays;

/**
 * here we used compareTo by extending comparable interface to compare non-primitive type data for all methods
 * 	This class has generic type class to perform operations on all types of data
 * @param <T> - making generic type class 
 */
public class SortingAlgorithms <T extends Comparable<T> > {

	/** parameterless constructor
	 *  
	 */
	public SortingAlgorithms() {}
	
	/**
	 * this method use bubble sort to perform sorting
	 * @param array - reference
	 */
    public void BubbleSort(T[] array) {
        
    	// print the array
    	printArray(array);
        
    	// two loops for iteration
        for (int i = 0; i < array.length - 1; i++) { // for pass
        	
            for (int j = 0; j < array.length - i - 1; j++) { // for searching
            	
                if (array[j].compareTo(array[j + 1]) > 0) { // using compare to and temp to change the value
                    T temp = array[j]; 
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
               }
           
            } 
        
        }
        
        System.out.println("\nBubble Sort ( Simple sorting algorithm  - O(n2) Complexity ) - ");
        System.out.print("\nSorted : ");
        printArray(array); // print sorted array
    
    }

    /**
     * this method use insertion sort 
     * @param array - reference 
     */
    public void InsertionSort(T[] array) {
        
    	printArray(array); // print array
        
        for (int i = 1; i < array.length; i++) { // condition
              
        	T key = array[i];
            int j = i - 1;
                 
            	while (j >= 0 && array[j].compareTo(key) > 0) { // iterating through the loop and comparing the elements
            		array[j + 1] = array[j];
            		j--;
            	}  
          
            array[j + 1] = key; 
        
        }
        
        System.out.println("\nInsertion Sort ( Simple sorting algorithm  - O(n2) Complexity ) - ");
        System.out.print("\nSorted : ");
        printArray(array); // print the sorted array
   
    }

    /**
     * this method use selection sort
     * @param array - reference
     */
    public void SelectionSort(T[] array) {
        
    	printArray(array); // printing the array
        
    	for (int i = 0; i < array.length - 1; i++) { // looping through the iteration
           
    		int minIdx = i;
           
    			for (int j = i + 1; j < array.length; j++) {
           
    				if (array[j].compareTo(array[minIdx]) < 0) { // comparing the elements
    					minIdx = j;
    				}
           
    			}     
          
    		T temp = array[i]; // changing values by temp 
    		array[i] = array[minIdx];
    		array[minIdx] = temp;
        
    	}
        
    	System.out.println("\nSelection Sort ( Simple sorting algorithm  - O(n2) Complexity ) - ");
    	System.out.print("\nSorted : ");
        printArray(array); // print the array 
    
    }

    /**
     *  this method use merge sort 
     * @param array - passing array's reference
     */
    public void MergeSort(T[] array) {
        
        if (array.length <= 1) { return; } // base case
       
        int mid = array.length / 2; // mid point
        T[] left = Arrays.copyOfRange(array, 0, mid); // left array from mid point
        T[] right = Arrays.copyOfRange(array, mid, array.length); // right array from mid point
         
        MergeSort(left); // calling the function
        MergeSort(right); // calling the function
        merge(left, right, array); // calling the function
    
    }

    /**
     * this is a supporting method to support the merge sort method. this method place the element into the main array, and sort. 
     * @param left - left array reference
     * @param right - right array reference
     * @param array - the main array reference
     */
    private void merge(T[] left, T[] right, T[] array) {
       
    	int i = 0, l = 0, r = 0;
          
    	while (l < left.length && r < right.length) { // looping
            
    		if (left[l].compareTo(right[r]) <= 0) { // comparing
    		
    			array[i] = left[l];
    			i++;
    			l++;
    			
    		} else {
            	
    			array[i] = right[r];  
    			i++;
    			r++;
            
    		}
          
    	}
        
    	while (l < left.length) {  // looping for left array if any element is still remaining
    		array[i] = left[l];
    		i++;
    		l++;
    	}
       
    	while (r < right.length) { // looping for right array if any element is still remaining
    		array[i] = right[r];
    		i++;
    		r++;
    	}
   
    }

    /**
     * this method use quick sort to sort
     * @param array - reference
     * @param low - lower boundary 
     * @param high - higher boundary
     */
    public void QuickSort(T[] array, int low, int high) {
       
    	if (low >= high) { return; } // base case
            
        int pivot = partition(array, low, high); // taking pivot index
        QuickSort(array, low, pivot - 1); // recursion
        QuickSort(array, pivot + 1, high); // recursion
    
    }

    /**
     * this method do partition in array with pivot index. smaller are left side and bigger are right side
     * @param array - reference
     * @param low - lower boundary
     * @param high - higher boundary  
     * @return - returning the index
     */
    private int partition(T[] array, int low, int high) {
       
    	T pivot = array[high];   
        int i = low - 1;
       
        	for (int j = low; j < high; j++) { // iterating 
           
        		if (array[j].compareTo(pivot) < 0) { // comparing the values and changing using temp
        			i++;
        			T temp = array[i];
        			array[i] = array[j];
        			array[j] = temp;
        		}
        
        	}
            // if remaining any value in the array, it add them to the array
        	T temp = array[i + 1];
        	array[i + 1] = array[high];
        	array[high] = temp;
          
        	return i + 1;  // returning the index
   
    }
    
    /**
     * printing the array
     * @param array - reference 
     */
    public void printArray(T[] array) {
        
    	System.out.println(Arrays.toString(array));
        
    }  
    
}
