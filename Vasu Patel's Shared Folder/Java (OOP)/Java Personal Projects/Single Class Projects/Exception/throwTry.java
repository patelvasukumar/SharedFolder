package Exception;
import java.util.Scanner;

public class throwTry {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
		
		System.out.print("Write the numerator: ");
		int numerator = Integer.parseInt(scanner.nextLine());
		
		System.out.print("Write the denominator: ");
		int denomenator = Integer.parseInt(scanner.nextLine());
		
		if(denomenator == 0) { throw new ArithmeticException("It is devided by zero."); }
		
		int result = numerator/denomenator;
		
		System.out.println("the division is: " + result);
		
		} catch(ArithmeticException e) {
			System.out.println("it is a " + e + e.getMessage());
		} finally {
			System.out.println("The program is completed.");
			scanner.close();
		}
	}
	
}
