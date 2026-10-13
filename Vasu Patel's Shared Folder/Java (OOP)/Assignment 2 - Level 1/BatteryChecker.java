/* Assignment: Assignment 2
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Lab Professor: Professor Syed Bilgrami
 * Due Date: Novermber 24th
 * Description: finding battery tolerance and number of batteries which are not tolerance.
 */

			// the class checks diameter and height tolerance, and return a boolean value.
public class BatteryChecker {
	
			// declaration of EPSILON_DIAMETER and EPSILON_HEIGHT. We cannot change them due to final.
	private static final double EPSILON_DIAMETER =  0.1 + 1.0E-14;
	private static final double EPSILON_HEIGHT = 0.3 + 1.0E-14 ;
	
			// this method will return a boolean value. Also, it is a parameterized methods.
	public boolean checkWithinTolerance(Battery battery) {
		
		// declaring variables and using getter taking values from Battery class. Also, static method was used.
		double diameter = battery.getDiameter();  // giving the values to the variable
		double height = battery.getHeight();	// giving the values to the variable
		
		boolean backerDi;	// boolean data type declaration.
		
		// if-then-else-endif condition will return a boolean value.
		if (Math.abs(diameter - Battery.EXPECTED_DIAMETER) <= EPSILON_DIAMETER ) 
		{
			backerDi = true;
		} 
		else	{
			backerDi = false;
		}

		boolean backerHi; // // boolean data type declaration.
		
		// if-then-else-endif condition will return a boolean value.
		if (Math.abs(height - Battery.EXPECTED_HEIGHT) <= EPSILON_HEIGHT) { 
			backerHi = true;
		}
		else	{
			backerHi = false;
		}
		
		// returning the boolean value for diameter and height by using and operator.
		return backerDi && backerHi;
				
	}
}
