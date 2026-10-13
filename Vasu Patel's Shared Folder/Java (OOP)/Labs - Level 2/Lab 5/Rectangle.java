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

import java.util.Scanner;

//implements the super class interface
public class Rectangle implements Shape {
   
	private int length;
    private int width;

    public Rectangle(int length, int width) {
       
    	this.length = length;
        this.width = width;
   
    }

 // overriding the method to calculate perimeters.
    @Override
    public double calcPerimeter() {
   
    	return 2 * (length + width);

    }

 // overriding the method to calculate area.
    @Override
    public double calcArea() {
   
    	return length * width;
   
    }

}