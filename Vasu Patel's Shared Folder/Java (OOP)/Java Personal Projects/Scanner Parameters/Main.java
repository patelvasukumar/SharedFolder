import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		InputHandler handler = new InputHandler();
		
		handler.takeInput(scanner);
		handler.closeScanner(scanner);
		
	}
	
}
