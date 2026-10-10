/**
 * 
 * Program: Computer Engineering Technology - Computing Science [Academic Level: 2 ]
 * Course: CST8132 Object Oriented Programming 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Section #: 312
 * Professor: Dr. James Mwangi
 * L5
 */

// implements the super class interface
class Circle implements Shape {
	 
	private static final double PI = 3.14;
	private int radius;

	 public Circle(int radius) {
	     
		 this.radius = radius;
	 
	 }

	 // overriding the method to calculate perimeters.
	 @Override
	 public double calcPerimeter() {
	    
		 return 2 * PI * radius;
	 
	 }

	 // overriding the method to calcutae area.
	 @Override
	 public double calcArea() {
	 
		 return PI * radius * radius;
	 
	 }
	
}