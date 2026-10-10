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
 * extended sub-class from super class.
 */
public class Apartment extends Property {

	// private variables
	private int floorNumber;
	private boolean hasBalcony;
	
	//constructer
	public Apartment(String address, double monthlyRent,int floorNumber, boolean hasBalcony) {
		
		super(address, monthlyRent);
		this.floorNumber = floorNumber;
		this.hasBalcony = hasBalcony;
	
	}
	
	//overiding method
	@Override 
	public void displayDetails() {
		
		System.out.println("Apartment at " + address);
        System.out.println("Monthly Rent: $" + monthlyRent);
        System.out.println("Floor: " + floorNumber);
        System.out.println("Balcony: " + (hasBalcony ? "Yes" : "No"));
        System.out.println("Status: " + (isRented ? "Rented" : "Available"));
        System.out.println();
		
	}
	
}
