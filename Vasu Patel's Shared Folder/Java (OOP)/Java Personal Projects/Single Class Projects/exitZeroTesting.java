import java.util.Scanner;

public class exitZeroTesting {

	public static void main(String[] args) {
	
		Scanner key = new Scanner(System.in); 
		
		System.out.println("Enter the number 1 or 2");
		int num = key.nextInt();
				  key.nextLine();
				  
		if(num == 1) {
			System.out.println("System.exit(0) worked");
			System.exit(0);
			System.out.println("the number is 1");
		}else if (num == 2) {
			System.out.println("System.exit(0) worked3");
			System.exit(0);
			System.out.println("the number is 2");
		}else {
			System.out.println("The the wrong number typed.");
			System.out.println("The number is something else");
		}
		
	}
}
