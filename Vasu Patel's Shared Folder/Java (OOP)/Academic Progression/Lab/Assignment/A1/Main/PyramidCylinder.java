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
	public PyramidCylinder(double radius, double height) {
		
		this.radius = radius;					// this for using class variables.
		this.height = height;					
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





