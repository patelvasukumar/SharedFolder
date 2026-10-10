package Exception;
import java.util.Scanner;

class InvalidAgeException extends Exception {
	public InvalidAgeException(String sms) {
		super(sms);
	}
}

public class AgeCheck {

	public static void main(String[] args) {
		
		try {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("So, this movie is 18+. Could you Please, tell me your age? ");
		int age = Integer.parseInt(scanner.nextLine());
		
		if(age < 18) {
			throw new InvalidAgeException("You are under age!");
		}
		
		System.out.println("Yes, you are eligible!");
		
		} catch(InvalidAgeException exception) {
			System.out.println("it is " + exception);
		} finally {
			System.out.println("The program is completed!");
		} 
	}
	
}
