package Exception;
import java.util.Scanner;

class IllegalArgumentException extends Exception {
	public IllegalArgumentException(String sms) {
		super(sms);
	}
}

public class negativeException {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
		System.out.print("Enter the square root integer: ");
		double number = Double.parseDouble(scanner.nextLine());
		
		if(number < 0) {
			throw new IllegalArgumentException("You cannot enter negative number.");
		}
		
		double squareRoot = Math.sqrt(number);
		
		System.out.println("The square root is: " + squareRoot);
		
		} catch( IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			System.out.println();
		//	System.out.println(e.getStackTrace());
			System.out.println();
	    //	System.out.println(e.toString());
			System.out.println();
			
			
			
			
		} finally {
			System.out.println("The program has been finished by Vasukumar Patel.");
			scanner.close();
		}
					 
					 
	}
	
}
