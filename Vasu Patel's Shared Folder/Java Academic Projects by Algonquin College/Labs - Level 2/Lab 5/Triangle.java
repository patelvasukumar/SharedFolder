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
class Triangle implements Shape {
    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

 // overriding the method to calculate perimeters.
    @Override
    public double calcPerimeter() {
        return a + b + c;
    }

 // overriding the method to calculate area.
    @Override
    public double calcArea() {
        double s = calcPerimeter() / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
