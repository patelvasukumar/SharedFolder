/* Assignment: Assignment 2 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Lab Professor: Professor Syed Bilgrami
 * Due Date: Novermber 24th
 * Description: finding battery tolerance and number of batteries which are not tolerance.
 */ 

			// by using static method the class models diameter and height tolerance.
public class Driver {

			// Main method starting of the program.
	public static void main(String[] args) {
		
		// object creations.
		BatteryChecker batteryChecker = new BatteryChecker();
		Battery battery = new Battery();
		
		// declaration for two variables with values.
		 int notRunning = 0;
		 String again = "yes";
		
		 // do-while loop is used.
	do {	
		// displaying the message.
		System.out.println("Enter bettery dimensions (expected: 3.500 for diameter, 10.300 for height)");
		
		// static method to call the method from user class for both declared variables.
		double diameter = User.inputDouble("Enter the diameter");
		double height = User.inputDouble("Enter the height");
		
		// setter to set the variables into Battery class.
		battery.setDiameter(diameter);
		battery.setHeight(height);
		
		// displaying the result and calling the method through object from battery class.
		System.out.println(battery.toString());
		
		// making boolean variable and storing the value in it and taking the value from Battery
		// checker class by using given object.
		boolean result = batteryChecker.checkWithinTolerance(battery);
		
		// using if-then-else-endif condition. 
		// If true, the message will be: The battery is within tolerance.
		// If false, the message will be: The battery is not within tolerance.
		if (result == true) {
			System.out.println("The battery is within tolerance");
		} else {
			System.out.println("The battery is not within tolerance");
			notRunning++; // increasing the magic number by using addition operator. 
		}
			// displaying my name.
		System.out.println("The program is made by Vasukumar Patel");
			
			//using static method from User class.
		again = User.inputString("Do you want to enter the values again ? (YES/NO)");
		} while(again.equalsIgnoreCase("yes")); // repeating the loop if user write "YES". Also, ignoring the cases.
		
		// printing the total out of tolerance battery.
		System.out.println("The total number of out of tolerance battery is: " + notRunning);
	}
}