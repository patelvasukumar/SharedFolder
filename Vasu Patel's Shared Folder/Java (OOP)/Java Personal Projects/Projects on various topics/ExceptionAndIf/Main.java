package ExceptionAndIf;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter the number for repeat: ");
		int a = myObj.nextInt();
				myObj.nextLine();
		Boolean thinking = true;
				
		while(thinking) {
		System.out.println("Enter X: ");
		int x = myObj.nextInt();
				myObj.nextLine();
		
		System.out.println("Enter X: ");
		int y = myObj.nextInt();
			    myObj.nextLine();
		Calculation.calculation(x, y);
		
		
		}	
		
	}
	
}
