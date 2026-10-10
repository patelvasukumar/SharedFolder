package Week5_Quiz;

public class Test4 {
    public static void main(String[] args) {
        Test4 v = new Test4();
        v.findSum(1, 2, 3, 4, 5);
    }

    public void findSum(int a, int b, int ...c) {
        int sum = a + b;  // Initialize sum with a and b
        for (int n : c) {  // Loop through the varargs
            sum += n;  // Add each number in c to sum
        }
        System.out.println(sum);  // Print the sum
    }
}

