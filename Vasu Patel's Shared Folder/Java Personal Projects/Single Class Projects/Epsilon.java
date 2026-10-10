	public class Epsilon {
    public static void main(String[] args) {
        
        double a = 0.1 + 0.2;
        double b = 0.3;
        double epsilon = 0;

        
        if (Math.abs(a - b) < epsilon) {
            System.out.println("same");
        } else {
            System.out.println("not");
        }
    }
}
