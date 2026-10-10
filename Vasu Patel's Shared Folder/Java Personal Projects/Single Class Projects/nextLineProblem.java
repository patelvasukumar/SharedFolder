import java.util.Scanner;

public class nextLineProblem {
	
	public static void main(String[] args) {
		
	
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Enter a number:");
	int num = scanner.nextInt(); // Reads the integer, leaves the newline
	
	System.out.println("Enter a string:");
	String str = scanner.nextLine(); // Reads the leftover newline, effectively skipping input
	System.out.println("You entered: " + num + " and " + str);
	
	scanner.close();
	}
}
