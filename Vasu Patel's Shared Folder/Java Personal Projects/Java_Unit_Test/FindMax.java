package Java_Unit_Test;

public class FindMax {

    public static double findMax(double a, double... values) {
        
        if (values.length == 0) {
            System.out.println("No values provided. Returning Double.MIN_VALUE: " + Double.MIN_VALUE);
            return Double.MIN_VALUE;
        }
        
        double largest = values[0];
        
        for (int i = 0; i < values.length; i++) {
            if (values[i] > largest) {
                largest = values[i];
            }
        }
        
        return largest;
    }
    
    public static void main(String[] args) {
        System.out.println(findMax(25555.5, 30, 60, 45, 300.25, 5, 0));
        System.out.println(findMax(4.2, 4));
    }
}
