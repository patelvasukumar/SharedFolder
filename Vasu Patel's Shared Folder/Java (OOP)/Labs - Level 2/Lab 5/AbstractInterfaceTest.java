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

// Test class to function the methods.
public class AbstractInterfaceTest {
    
	//main method to display the details
	public static void main(String[] args) {
        
		// scanner making
    	Scanner scanner = new Scanner(System.in);

    	// used scanner and put the parameters in the constructer.
        System.out.println("Enter the radius of the Circle object: ");
       
     // used integer parse to convert string to int
        System.out.print("Enter the radius:");
        int radius = Integer.parseInt(scanner.nextLine());
       
      //object creation
        Shape circle = new Circle(radius);

     // used scanner and put the parameters in the constructer.
        System.out.println("Enter the length and width of the Rectangle object: ");
        
     // used integer parse to convert string to int
        System.out.print("Enter the length:");
        int length = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the width:");
        int width = Integer.parseInt(scanner.nextLine());
       
      //object creation
        Shape rectangle = new Rectangle(length, width);

     // used scanner and put the parameters in the constructer.
        System.out.println("Enter the length of the Triangle object: ");
       
     // used integer parse to convert string to int
        System.out.print("Enter the length 1:");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the length 2:");
        int b = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the length 3:");
        int c = Integer.parseInt(scanner.nextLine());
       
        //object creation
        Shape triangle = new Triangle(a, b, c);

        // displaying the details
        System.out.println("Circle perimeter: " + circle.calcPerimeter());
        System.out.println("Circle area: " + circle.calcArea());

     // displaying the details
        System.out.println("Rectangle perimeter: " + rectangle.calcPerimeter());
        System.out.println("Rectangle area: " + rectangle.calcArea());

     // displaying the details
        System.out.println("Triangle perimeter: " + triangle.calcPerimeter());
        System.out.printf("Triangle area: %.2f ", triangle.calcArea());
        
     // displaying the programmer name
        System.out.println();
        System.out.println("The program is made by Vasukumar Patel.");
    }
}
