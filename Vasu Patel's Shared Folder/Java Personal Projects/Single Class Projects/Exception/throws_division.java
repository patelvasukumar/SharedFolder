package Exception;
import java.util.Scanner;

public class throws_division {
	static Scanner scanner = new Scanner(System.in);
	
	public int DivisionMethod(int numerator, int denomerator) throws ArithmeticException {
		
		if(denomerator == 0) {
			throw new ArithmeticException("You cannot divide by zero.");
		}
		int result = numerator/denomerator;
		
		return result;
	}
	
	public static void main(String[] args) {
		throws_division myObj = new throws_division();
		
		try {
		System.out.print("what is numerator: ");
		int numerator = Integer.parseInt(scanner.nextLine());
		
		System.out.print("what is denomerator");
		int denomerator = Integer.parseInt(scanner.nextLine());
		
		System.out.println("The result is " + myObj.DivisionMethod(numerator,denomerator));
		} catch(ArithmeticException e) {
			System.out.println(e);
			// e.printStackTrace();
		} finally {
			System.out.println("The program is completed");
		}
		scanner.close();
	}
	
}
