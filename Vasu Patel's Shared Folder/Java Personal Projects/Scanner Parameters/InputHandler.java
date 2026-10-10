import java.util.Scanner;

public class InputHandler {

	public void takeInput(Scanner scanner) {
		
		System.out.println("Enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Hello" + name);
		
	}
	
	public void closeScanner(Scanner scanner) {
			
		if(scanner != null) {
			scanner.close();
		}
		
	}
	
}
