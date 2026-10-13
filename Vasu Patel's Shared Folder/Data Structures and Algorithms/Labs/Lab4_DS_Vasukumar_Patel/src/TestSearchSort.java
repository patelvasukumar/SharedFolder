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
 * importing build-in methods from arrays class and secure random class
 */
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * this class has main class and it calls other method from other classes to perform the task
 */
public class TestSearchSort {

	/**
	 *  parameterless constructor 
	 */
	public TestSearchSort() {}
	
	/**
	 * driver class to perform the action
	 * @param args - arguments reference
	 */
    public static void main(String[] args) {
        
    	/*
    	 * object and variable creation
    	 */
    	int[] randomArr = null;
    	boolean sorted = false;
    	int[] sortedArr = new int[1000];
        Scanner scanner = new Scanner(System.in);
        Searching searchObj = new Searching();
        
        /**
         * using Integer wrapper type for generic class
         */
        SortingAlgorithms<Integer> sortObj = new SortingAlgorithms<>(); 

        do { // do while loop
           
        	try { // try block
               
        		printMainMenu();
                System.out.print("> ");
                int option = Integer.parseInt(scanner.nextLine().trim()); // taking value from string to int
  
                switch (option) { // switch case
                    
                case 1:
                        randomArr = new int[1000];   // array capacity 1000                           
                        genRandomInts(randomArr);	// generating 1000 random values and storing into array
                        System.out.println("\nArray of randomly generated integers:");
                        searchObj.printArray(randomArr); // printing the array
                        break;

                            
                case 2:    
                        if (randomArr == null) { // if null, print the message
                            System.out.println("\nPlease, enter option 1 first");
                        } else if (!sorted) { // if not sorted, so error message 
                        	System.out.println("\nBefore using binary search, first sort the array, use option 4.");
                        } else { 
                            searchObj.binarySearch(sortedArr,scanner);  
                        }
                        break;

                    
                case 3:
                        if (randomArr == null) { // if null, print the message
                            System.out.println("\nPlease, enter option 1 first");
                        } else {
                            searchObj.linearSearch(randomArr,scanner);      
                        }
                        break;

                    
                case 4:  
                	
                        if (randomArr == null) { // if null, print the message
                            System.out.println("\nPlease, enter option 1 first");
                            break;
                        }
                        
                        sorted = true;
                      
                        // making copy of the main array
                        Integer[] bubbleArray = new Integer[randomArr.length];
                        for (int i = 0; i < randomArr.length; i++) {
                        	 bubbleArray[i] = randomArr[i]; 
                        }

                        // making copy of the main array
                        Integer[] insertArray = new Integer[randomArr.length];
                        for (int i = 0; i < randomArr.length; i++) {
                        	 insertArray[i] = randomArr[i]; 
                        }
 
                        // making copy of the main array
                        Integer[] selectArray = new Integer[randomArr.length];
                        for (int i = 0; i < randomArr.length; i++) {
                        	 selectArray[i] = randomArr[i];  
                        }
   
                        // making copy of the main array
                        Integer[] mergeArray = new Integer[randomArr.length];
                        for (int i = 0; i < randomArr.length; i++) {
                        	 mergeArray[i] = randomArr[i];  
                        }

                        // making copy of the main array
                        Integer[] quickArray = new Integer[randomArr.length];
                        for (int i = 0; i < randomArr.length; i++) {
                        	 quickArray[i] = randomArr[i]; 
                        }    

                        boolean loop = true;
                        
                        while(loop) {
                        
                        sortSubMenu(); // printing submenu
                        System.out.print("\n>");
                        String subMenu = scanner.nextLine().trim(); // taking user input
                        
                        // in switch case, every sort has the total duration that took it to do the sort in milliseconds and seconds
                       // also, storing every sorted values into sorted array
                        switch (subMenu) {
                        
                        case "B":
                               
                        		long nanoStartB = System.nanoTime(); 
                                long milliStartB = System.currentTimeMillis();
                               
                                sortObj.BubbleSort(bubbleArray); 
                                  
                                System.out.println("\nTime taken in nanoseconds : " + (System.nanoTime() - nanoStartB));
                                System.out.println("Time taken in milliseconds : " + (System.currentTimeMillis() - milliStartB));
                                
                                for (int i = 0; i < bubbleArray.length; i++) {
                                	 sortedArr[i] = bubbleArray[i]; 
                                }
                                
                                break;
                            
                        case "I":
                                
                        		long nanoStartI = System.nanoTime(); 
                                long milliStartI = System.currentTimeMillis();
                               
                                sortObj.InsertionSort(insertArray); 
                                
                                System.out.println("\nTime taken in nanoseconds : " + (System.nanoTime() - nanoStartI));
                                System.out.println("Time taken in milliseconds : " + (System.currentTimeMillis() - milliStartI));
                                
                                for (int i = 0; i < bubbleArray.length; i++) {
                               	 sortedArr[i] = insertArray[i]; 
                                }
                                
                                break;
                            
                        case "S":
                                
                        		long nanoStartS = System.nanoTime(); 
                                long milliStartS = System.currentTimeMillis();
                                
                                sortObj.SelectionSort(selectArray); 
                                
                                System.out.println("\nTime taken in nanoseconds : " + (System.nanoTime() - nanoStartS));
                                System.out.println("Time taken in milliseconds : " + (System.currentTimeMillis() - milliStartS));
                                
                                for (int i = 0; i < selectArray.length; i++) {
                               	 sortedArr[i] = selectArray[i]; 
                                }
                                
                                break;
                            
                        case "M":
                               
                        		long nanoStartM = System.nanoTime(); 
                                long milliStartM = System.currentTimeMillis();
                              
                                sortObj.printArray(mergeArray);
                                sortObj.MergeSort(mergeArray); 
                                System.out.println("\nMerge Sort ( Recursive Divide-And-Conquer  - O(n log n) Complexity ) - ");
                                System.out.print("\nSorted : ");
                                sortObj.printArray(mergeArray);
                                
                                System.out.println("\nTime taken in nanoseconds : " + (System.nanoTime() - nanoStartM));
                                System.out.println("Time taken in milliseconds : " + (System.currentTimeMillis() - milliStartM));
                               
                                for (int i = 0; i < mergeArray.length; i++) {
                               	 sortedArr[i] = mergeArray[i]; 
                                }
                                
                                break;
                            
                        case "Q":
                               
                        		long nanoStartQ = System.nanoTime(); 
                        		long milliStartQ = System.currentTimeMillis();
                                
                        		sortObj.printArray(quickArray);
                        		sortObj.QuickSort(quickArray, 0, quickArray.length - 1); 
                        		System.out.println("\nQuick Sort ( Recursive Divide-And-Conquer - O(n log n) Complexity ) - ");
                        		System.out.print("\nSorted : "); 
                        		sortObj.printArray(quickArray);
                                  
                        		System.out.println("\nTime taken in nanoseconds : " + (System.nanoTime() - nanoStartQ));
                                System.out.println("Time taken in milliseconds : " + (System.currentTimeMillis() - milliStartQ));
                                
                                for (int i = 0; i < quickArray.length; i++) {
                               	 sortedArr[i] = quickArray[i]; 
                               }
                                
                                break;
                           
                        case "R": // returning to the main menu
                               
                        		System.out.println("\nGoing back to the main menu...");
                        		loop = false;
                                break;
                            
                        default: // default for any error
                                
                        		System.out.println("\nYou selected the wrong option. Please, try again ! ");
                                break;
                       
                        }
                        }
                        
                        break;

                    case 5: // closing the program
                       
                    	System.out.println("\nThe program is shut-down");
                        System.out.println("\nThe program is made by Vasukumar Patel");                        
                        scanner.close();
                        System.exit(0);
                       
                        break;

                    default: // error message for invalid option
                        
                    	System.out.println("\nYou selected the wrong option, Please, try again ! ");
                        break;
                }

           
        	} catch (Exception ex) { // exception handling
                System.out.println("\nError : You cannot enter a string and a float value !");
            }

        } while (true); // while loop
    
    }

    /**
     * supporting method to print the menu
     */
    private static void printMainMenu() {
       
    	System.out.println("\nSelect your option in the menu below:");
        System.out.println("1: Initialize and populate an array of 1000 random integers.");
        System.out.println("2: Perform recursive binary search.");
        System.out.println("3: Perform recursive linear search.");
        System.out.println("4: Sort the array -> Go to submenu");
        System.out.println("5: Quit");
    
    }

    /**
     * supporting method to print the submenu
     */
    private static void sortSubMenu() {
          
    	System.out.println("\nSelect a sorting algorithm to sort the data array");
        System.out.println("B. Bubble Sort");
        System.out.println("I. Insertion Sort");
        System.out.println("S. Selection Sort");
        System.out.println("M. Merge Sort");
        System.out.println("Q. Quick Sort");
        System.out.println("R. Return to main menu");
    
    }

    /**
     * supporting method to generate random values and to fill the array
     * @param array
     */
    private static void genRandomInts(int[] array) {
        
    	SecureRandom secure = new SecureRandom(); // using secure random class
        
    	for (int i = 0; i < array.length; i++) { // looping and filling the array with random values
           
    		array[i] = secure.nextInt(120, 1000); // lower and upper boundary parameters
         
    	}
    
    }

}
