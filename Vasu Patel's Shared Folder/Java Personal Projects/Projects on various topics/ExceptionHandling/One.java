package ExceptionHandling;
import java.util.Scanner;

public class One {
	
	public static void main(String[] args) {
		
	Scanner scanner = new Scanner(System.in);
	
	int number;
	boolean isValid = false;
	
	while(!isValid) {
		
		System.out.print("Please enter an integer: ");
		
		try {
			number = Integer.parseInt(scanner.nextLine());
			System.out.println("You entered: " + number);
			
		} catch (Exception e) {
			System.out.println("Invalid");
		}
		
	}
		scanner.close();
	}
	
}
