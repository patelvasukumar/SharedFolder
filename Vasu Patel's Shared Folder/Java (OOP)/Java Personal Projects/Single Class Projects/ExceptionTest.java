package week2_inclass;
import java.util.*;

public class ExceptionTest {

	
	
	public static void main(String[] args) {
		
		int num = 0, den = 0;
		
		try {
			Scanner input = new Scanner(System.in);
			
				System.out.println("Math Calculation");
				System.out.println("Enter a numerator");
				
				num = input.nextInt();
				
				System.out.println("Enter a denominator:");
				
				den = input.nextInt();
				
				System.out.println(num + "/" + den + " = " + (num/den));
				
				input.close();
				
		} catch (ArithmeticException ae) {
			System.out.println("Math problem: " + ae.toString());
			
		}catch (Exception e) {
			
			System.out.println("Other Exceptions");
			e.printStackTrace();
		}
		
		System.out.println("\nProduct is: " + (num*den));
		System.out.println("\nOperation done");
		
		
		
	}
	
}
