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
public class House extends Property {

	//variables
	private int numberOfBedrooms;
	private boolean hasGarage;
	
	//constructer
	public House(String address, double monthlyRent, int numberOfBedrooms, boolean hasGarage) {
		
		super(address, monthlyRent);
	    this.numberOfBedrooms = numberOfBedrooms;
	    this.hasGarage = hasGarage;
		
	}
	
	//overriding method
	@Override
	public void displayDetails() {
		
        System.out.println("House at " + address);
        System.out.println("Monthly Rent: $" + monthlyRent);
        System.out.println("Bedrooms: " + numberOfBedrooms);
        System.out.println("Garage: " + (hasGarage ? "Yes" : "No"));
        System.out.println("Status: " + (isRented ? "Rented" : "Available"));
        System.out.println();
		
	}
	
	
	
}
