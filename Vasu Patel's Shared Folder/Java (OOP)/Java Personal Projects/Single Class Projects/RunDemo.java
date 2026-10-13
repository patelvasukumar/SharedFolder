package Week12;
import java.util.Scanner;
/*
 * This class has the main-line logic for the application
 */
public class RunDemo {

	public static void main(String[] args) { // "start"
		Scanner keyboard = new Scanner(System.in);
		LinearSearchDemo demo = new LinearSearchDemo();
		double searchValue;
		int foundAtIndex;
		
		System.out.println("The array currently has:");
		System.out.println(demo.arrayContents());

		System.out.println("Enter search number");
		searchValue = keyboard.nextDouble();
		
		foundAtIndex = demo.findItem(searchValue);
		
		if(foundAtIndex > -1) {
			System.out.print("found at index ");
			System.out.println(foundAtIndex);
		}
		else {
			System.out.println("not found");
		}
		keyboard.close();

	} // "stop"

}
