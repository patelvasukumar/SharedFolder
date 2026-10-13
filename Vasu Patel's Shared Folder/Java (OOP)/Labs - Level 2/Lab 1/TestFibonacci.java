/* Program: CET LEVEL 2
 * STUDENT NAME: VASUKUMAR PATEL
 * STUDENT NUMBAR: 041153940
 * COURSE: CST8132_310 OOP
 * PROFESSOR: JAMES MWANGI PHD.
 */

import java.util.Scanner;

public class TestFibonacci {

	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		String answer;
		
		do{
			
		System.out.print("Enter the number of Fibonacci numbers to generate: ");
		int number = key.nextInt();
					key.nextLine(); // Do not forget.
		
		FibonacciGenerator myObj = new FibonacciGenerator();
		
		
		myObj.GenerateFibonacciSequence(number);
		
		System.out.println();
		System.out.println("Do you want to quit? If yes, press Q or q. If no, press anything. ");
		        // key.nextLine();  // If you do not use this one, it gives you error.
		answer = key.nextLine();
		
		
		}while(!answer.equalsIgnoreCase("q"));
		
		key.close();
	}
	
	
}

