
import java.util.Scanner;

public class BinarySearchExample {
    // Binary search function
    static int binarySearch(int[] A, int N) {
        int lowestPossibleLoc = 0; // Start from index 0
        int highestPossibleLoc = A.length - 1;

        while (highestPossibleLoc >= lowestPossibleLoc) {
            int middle = (lowestPossibleLoc + highestPossibleLoc) / 2;

            if (A[middle] == N) { 
                return middle; // Found N at index middle
            } else if (A[middle] > N) { 
                highestPossibleLoc = middle - 1; // Search in left half
            } else { 
                lowestPossibleLoc = middle + 1; // Search in right half
            }
        }
        return -1; // N not found
    }

    public static void main(String[] args) {
        // Example sorted array
        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};

        // Taking user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to search: ");
        int number = scanner.nextInt();
        scanner.close();

        // Performing binary search
        int result = binarySearch(sortedArray, number);

        // Output the result
        if (result != -1) {
            System.out.println("Number found at index: " + result);
        } else {
            System.out.println("Number not found in the array.");
        }
    }
}

