package Final;

import java.util.Scanner;

public class Final {
	
	public static void main(String[] args) {
		
		System.out.println("Enter Number:");
		Scanner key = new Scanner(System.in);
		int x =  key.nextInt();
		String voice = "Out of the range";
		
		if (x >= 1 && x <= 10 ) {
			System.out.println("In the range");
		} else
		{
			 System.out.printf("%s %n", voice);
		}
		
		if (x <= -1 && x <= -5 ) {
			System.out.println("In the range");
		} else
		{
			 System.out.printf("%s %n", voice);
		}
		
		if (x <= -1 || x >= -5 ) {
			System.out.println("In the range");
		} else
		{
			 System.out.printf("%s %n", voice);
		}
		
		key.close();
	}
}
