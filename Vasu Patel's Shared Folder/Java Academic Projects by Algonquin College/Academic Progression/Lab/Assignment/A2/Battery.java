/* Assignment: Assignment 2 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Lab Professor: Professor Syed Bilgrami
 * Due Date: Novermber 24th
 * Description: finding battery tolerance and number of batteries which are not tolerance.
 */ 
 
/*
 *  This class models batteries with fields for diameter and height (in mm)
 */
public class Battery {
	// Declared expected dimensions of the battery (in mm)
	public static final double EXPECTED_DIAMETER = 3.5; 
	public static final double EXPECTED_HEIGHT = 10.3;

	// Declared measured dimensions of the battery (in mm)
	private double diameter;
	private double height;
	
	/*
	 * No-argument constructor, sets dimensions to the expected values
	 */
	public Battery() {
		this(EXPECTED_DIAMETER, EXPECTED_HEIGHT); 
	}
	
	/*
	 * Overloaded constructor, sets dimensions as specified
	 */
	public Battery(double diameter, double height) {
		this.diameter = diameter;
		this.height = height;
	}

	/*
	 * Accessor for diameter (in mm)
	 */
	public double getDiameter() {
		return diameter;
	}

	/*
	 * Mutator for diameter (in mm)
	 */
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	/*
	 * Accessor for height (in mm)
	 */
	public double getHeight() {
		return height;
	}

	/*
	 * Mutator for height (in mm)
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/*
	 * Returns a String that represents the data held within this object
	 */
	public String toString() {
		String formatString;
		String result;
		
		formatString = "%s: diameter %.2f, height %.2f";
	    result = String.format(formatString,"Battery", diameter, height);
	    
		return result;
	}
}
