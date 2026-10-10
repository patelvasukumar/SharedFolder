package eachLoop;

public class findMax {

    static int[] numbers = {5, 2, 8, 1, 9};

    public static void main(String[] args) {
        int max = Integer.MIN_VALUE; // Initialize max with the smallest possible value

        for (int value : numbers) {
            if (value > max) {
                max = value; // Update max if a larger value is found
            }
        }

        System.out.println("The maximum value in the array is: " + max);
    }
}
