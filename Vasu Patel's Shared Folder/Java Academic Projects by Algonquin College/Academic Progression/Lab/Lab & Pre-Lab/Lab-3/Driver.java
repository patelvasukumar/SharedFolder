/*
 * Student Name: SAMPLE SOLUTION
 * Lab Professor: PROFESSOR NAME
 * Due Date: DUE DATE
 * Description: Sample solution for Lab Assignment 01 (24F).
 */

import java.util.Scanner;

// This class contains method main and is used to run the program.
public class Driver {
	/*
	 * The entry point for the application. A scanner object is used to obtain
	 * the user inputs for a cylinder pyramid's radius and height.
	 * Then, the volume of the volume of the cylinder is calculated using
	 * a CylinderPyramid object, and printed, formatted to 3 decimal places.
	 */
	public static void main(String[] args) {
		double radius = 0.0; // inputted radius of base cylinder
		double height = 0.0; // inputted height of base cylinder
		double volume = 0.0; // calculated volume of cylinder pyramid
		CylinderPyramid cylinderPyramid;

		Scanner input = new Scanner(System.in);

		System.out.print("Enter base cylinder radius: ");
		radius = input.nextDouble();
		input.nextLine();

		System.out.print("Enter base cylinder height: ");
		height = input.nextDouble();
		input.nextLine();

		cylinderPyramid = new CylinderPyramid(radius, height);
		volume = cylinderPyramid.calculatePyramidVolume();

		System.out.printf("The cylinder pyramid volume: %.3f%n", volume);
		System.out.println("Program by: SAMPLE SOLUTION");
		input.close();
	}
}
