/* Assignment: Lab - 03
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Lab Professor: Professor Syed Bilgrami
 * Due Date: Novermber 19th
 * Description: to get even and odd values (even: square, odd: cube)
 */ 

// import it because we want to use Scanner.
import java.util.Scanner;

		// Driver class created
public class Driver {
	
		// Java starting point. Main method
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // to input the values
		int numbersToCollect; // variables declaration
		int[] numbers;	// variables declaration
		int count = 0;	// variables declaration
		ArrayModifier arrayModifier; // variables declaration
		
		System.out.println("How many numbers would you like to collect?"); // display the message on the screen
		numbersToCollect = input.nextInt(); // input from the console
		input.nextLine();
		numbers = new int[numbersToCollect];
		
		// used while loop to make an array 
		while (count < numbersToCollect) {
			System.out.printf("Enter value #%d: ", count + 1);
			int number = input.nextInt();
			input.nextLine();
			numbers[count] = number;
			++count;
		}
		
		// setting the values
		arrayModifier = new ArrayModifier();
		arrayModifier.setValues(numbers);
		
		arrayModifier.modifyArray(); // calling the method
		
		System.out.println("The modifier array is:"); // display to the console
		
			// for loop to display the value on the console
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
			// display my name.
		System.out.println("Program modified by Vasukumar Patel");
			// close the scanner
		input.close();
	}
}
