package Week5_Quiz;

public class Test5 {
    public static void main(String[] args) {
        Test5 v = new Test5();
        v.findSum(1, 2, 3, 4, 5, 6);  // Example with an extra argument for 'd'
    }

    public void findSum(int a, int b, int d, int ...c) {
        int sum = a + b;  // Start sum with a + b
        for (int n : c) {  // Add all values from c (varargs) to sum
            sum += n;
        }
        System.out.println(sum);  // Print the total sum
    }
}
