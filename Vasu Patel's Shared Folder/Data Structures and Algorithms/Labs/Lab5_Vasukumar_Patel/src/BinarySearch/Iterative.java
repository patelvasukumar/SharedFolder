package BinarySearch;

public class Iterative {

    static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static int Binary(int target) {
        int low = 0;
        int high = numbers.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (numbers[mid] == target) {
                return mid;
            } else if (target < numbers[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
       
    	System.out.println(System.nanoTime());
    	System.out.println(Binary(4));
    	System.out.println(System.nanoTime());
    
    	// static
    	// where is brackets {} for nano
    }
}
