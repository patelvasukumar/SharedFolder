package Array;

import java.util.Scanner;

public class fifth {
    public static void main(String[] args) {
       
    	// Declare and initialize an array to store double values
        double[] values = new double[5]; // Array size is 5
        int currentSize = 0; // Keeps track of how many numbers are stored
        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter up to 5 double values (type a non-number to stop):");

        // Loop to read values
        while (in.hasNextDouble()) {
          
        	if (currentSize < values.length) {
                values[currentSize] = in.nextDouble(); // Store the value
                currentSize++; // Increment the counter
            } else {
                System.out.println("Array is full. Ignoring extra input.");
                break; // Exit the loop if array is full
            }
        	
        }
        
        // Print the values stored in the array
        System.out.println("You entered the following values:");
        for (int i = 0; i < currentSize; i++) {
            System.out.println(values[i]);
        }

        in.close(); // Close the scanner
    }
}
