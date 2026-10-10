/* 
 * Assignment Title: The Pyramid Cylinder
 * Student Full Name: Vasukumar Patel
 * Due Date of the Assignment : October 6th
 * 	Description: to calculate the total volume of the pyramid
 * Professor Name = Dr.James Mwangi 
 * 
 * */

//  Sir, I wrote comments a little bit far from code because I wanted make a less messy program. So, I can easily read it whenever I need it.

import java.lang.Math; 							// to use mathematics functions 

												// PyramidCylinder named Class created, which is storing the values for radius and height. 
public class PyramidCylinder {
	
												// data types initialization.
	private double radius;
	private double height; 
	
												// Constructor which is parameterized.
	public PyramidCylinder(double newRadius, double newHeight) {
		
		this.radius = newRadius;					// this for using class variables.
		this.height = newHeight;					
	}
	
												// Getter for radius.
	public double getRadius() {
	 return radius;								// to return the value. So, Driver class can access.
	}

												// Setter for radius.
	public void setRadius(double newRadius) {	// void because it is just conveying the data.
	 this.radius = newRadius;					
	}
	
												// Getter for height.
	public double getHeight() {
		return height;							// to return the value. So, Drive class can access.
	}

												// Setter for height.
	public void setHeight(double newHeight) {	// void because it is not generating the data.
		this.height = newHeight;
	}

												// method created to calculate the total volume of the single cylinder.
	
	public double calculateSingleVolume(double radius, double height) {
		
		return Math.PI*Math.pow(radius,2)*Math.abs(height);  // I used Math.abs. So, I only get value in positive.
												// returning the formula to the upcoming method
	}	
												// method created. To calculate the pyramid cylinder calculation. Also, to use it in Driver class.
	
	public double calculatePyramidVolume (double radius, double height) {
		
		double theTotalVolumeFirst = calculateSingleVolume (radius, height);
		double theTotalVolumeSecond = calculateSingleVolume (radius*0.8,height);
		double theTotalVolumeThird = calculateSingleVolume (radius*0.6,height);
		
		double theTotalVolume = theTotalVolumeFirst + theTotalVolumeSecond + theTotalVolumeThird; // adding above three values into one.
		
		return theTotalVolume;							// returning the volume.
	}
}

/*	
 * The constructor in a class serves several important purposes:
 * 
 * 1. Initialization of Object State
 * The primary purpose of a constructor is to initialize the instance variables (attributes) of a class when an object is created. This ensures that the object starts its life in a valid state with defined values. In your PyramidCylinder class, the constructor initializes the radius and height attributes with the values provided when an object is instantiated.
 * 
 * 2. Control Object Creation
 * Constructors can enforce certain conditions or invariants when creating an object. For example, you could check if the radius or height is negative and throw an error if it is, ensuring that the object cannot exist in an invalid state.
 * 
 * 3. Overloading
 * Constructors can be overloaded, meaning you can have multiple constructors with different parameter lists in the same class. This allows objects to be created in different ways. For instance, you might have a constructor that accepts only the radius, assigning a default value to height.
 * 
 * 4. Setting Defaults
 * If no arguments are provided during object creation, you can define a no-argument constructor (also called a default constructor) that sets default values for the instance variables.
 * 
 * 5. Establish Relationships
 * Constructors can also help establish relationships between different objects or classes by initializing objects with references to other objects.
 *
 * */
 