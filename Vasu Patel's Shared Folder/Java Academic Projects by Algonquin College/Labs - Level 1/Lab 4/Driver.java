// Student Name: Vasukumar Patel
// Student Number: 041153940
// Assignment Name: in-Lab 04
// Lab Professor Name: : Syed Bilgrami 
// Due Date: 05-11-2024
// Objective: to make a program which give UK and EZ size according to input of Canadian Size

import java.util.Scanner;

/* 
 * Simple class, contains main line logic for application.
 */
public class Driver {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		int ringSize 	= 0;	// User's Canadian ring size
		String gender 	= null;	// User's gender
		String report	= null; // report from object

		System.out.print("Please enter your Canadian ring size ");
		ringSize = keyboard.nextInt();
		keyboard.nextLine();

		System.out.print("Please enter your gender (male or female) ");
		gender = keyboard.nextLine();
		
		RingSizeConverter ringSizeConverter = new RingSizeConverter(ringSize, gender);
		
		report = ringSizeConverter.createReport();

		System.out.println(report);
		// update the line below to include your name
		System.out.println("Program by Vasukumar Patel");
		System.out.println();
		
	}
}