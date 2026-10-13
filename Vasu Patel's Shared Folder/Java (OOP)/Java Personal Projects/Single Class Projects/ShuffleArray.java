import java.util.Arrays;

public class ShuffleArray {

    // Method to shuffle the array
    static void shuffle(int[] A) {
        for (int lastPlace = A.length - 1; lastPlace > 0; lastPlace--) {
            // Choose a random location from among 0, 1, ..., lastPlace
            int randloc = (int)(Math.random() * (lastPlace + 1));
            
            // Swap items in locations randloc and lastPlace
            int temp = A[randloc];
            A[randloc] = A[lastPlace];
            A[lastPlace] = temp;
        }
    }

    public static void main(String[] args) {
        // Sample array to shuffle
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Print the original array
        System.out.println("Original Array: " + Arrays.toString(numbers));

        // Shuffle the array
        shuffle(numbers);

        // Print the shuffled array
        System.out.println("Shuffled Array: " + Arrays.toString(numbers));
    }
}
