package Exception;
import java.util.Scanner;
import java.util.InputMismatchException;

public class DivideBy {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Enter the first interger:");
			int first = scanner.nextInt();
						scanner.nextLine();
						
			System.out.println("Enter the second interger:");
			int second = scanner.nextInt();
						scanner.nextLine();
						
			int result = first/second;
			System.out.println("The result is this one: " + result);
			
		} catch(ArithmeticException e) {
			System.out.println("Error: cannot divide by zero"); }
		
		catch(InputMismatchException e) {
			System.out.println("Error: Invalid Input. Please, Enter a numerical value."); }
		
		finally{
			System.out.println("The program has been completed!"); }
		
	scanner.close();
	}
	
}
