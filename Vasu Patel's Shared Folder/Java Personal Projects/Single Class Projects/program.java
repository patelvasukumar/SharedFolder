import java.util.Scanner;

public class program {

		public static void main(String[] args) {
			Scanner keyboard = new Scanner(System.in);
			
			int input;
			int runningTotal = 0;
			int count = 0;
			String answer;
			int min = Integer.MAX_VALUE; 
			int max = Integer.MIN_VALUE;
			
		do	{
			System.out.print("Enter an interger value");
			input = keyboard.nextInt();
					keyboard.nextLine();
					
					runningTotal = runningTotal + input;
					
					count++;
					
					if(input < min) {
						min = input;
					}
					
					if(input > max) {
						max = input;
					}
					
					System.out.print("Do you want to continue entering interger value? (y/n):");
					answer = keyboard.nextLine();
		}
		while (answer.equalsIgnoreCase("y"));
		
		System.out.println("The total is" + runningTotal);
		System.out.println("The average is" + 1.0 * runningTotal / count);
		System.out.println("min:" + min);
		System.out.println("max:" + max);
		keyboard.close();
}
}
