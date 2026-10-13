/**
 * 
 * Program: Computer Engineering Technology - Computing Science [Academic Level: 2 ]
 * Course: CST8132 Object Oriented Programming 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Section #: 312
 * Professor: Dr. James Mwangi
 * L4
 */

/*
 * Main - driving method.
 */
public class HousingRentalTest {

	public static void main(String[] args) {
		
		// Property class array to store the values.
		Property[] properties = new Property[3];
		
		properties [0] = new Apartment ("123 Main St", 1200.0, 5, true); 
		properties[1] = new House ("456 Oak Ave", 2500.0, 3, true); 
		properties [2] = new Apartment ("789 Pine Rd", 1000.0, 2, false);
		
		// each loop to open all indexes, and call the method
        for (Property property : properties) {
            property.displayDetails();
        }
        
        System.out.println("Renting out '123 Main St' and '456 Oak Ave'...");
        System.out.println();
        properties[0].rentOut();
        properties[1].rentOut();
        
        System.out.println();
        System.out.println("Vacating '123 Main St'...");
        System.out.println();
        properties[0].vacate();
        
        System.out.println();
        System.out.println("Updated Property Status:");
        System.out.println();
       // each loop to open all indexes, and call the method.
        for (Property property : properties) {
            property.displayDetails();
        }
        
        System.out.println();
        System.out.println("Explanation:");
        System.out.println("This program shows abstraction with a Property class. "
        		+ "\nApartment and House extend it and add their own details."
        		+ "\nThere are two concreate method."
        		+ "\nThe property is storing the values and using the abstract method to display the result.");
        
        System.out.println();
        System.out.println("The program is made by Vasukumar Patel.");
	
	}
}
