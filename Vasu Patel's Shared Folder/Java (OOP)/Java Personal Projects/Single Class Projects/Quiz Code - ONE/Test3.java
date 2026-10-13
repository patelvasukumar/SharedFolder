package Week5_Quiz;

public class Test3 {
    public static void main(String[] args) {
        new VarargTest().printResult(1, "Hello", "World");
    }

    public void printResult(int x, String ...y) {
        System.out.println(y[x-1]);
    }
}
