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
 * Abstract class made. it contains two solid method and one abstract without body.
 */
public abstract class Property {

	// three protected variables. So, only sub-class can use them.
	protected String address;
	protected double monthlyRent;
	protected boolean isRented;
	
	// constructoer
	public Property(String address, double monthlyRent) {
		
		this.address = address;
		this.monthlyRent = monthlyRent;
		this.isRented = isRented;
	
	}
	
	//abstract method
	public abstract void displayDetails();
	
	// concreate method
	public void rentOut() {
       
		if (!isRented) {
            
        	isRented = true;
            System.out.println("Property at " + address + " has been rented.");
       
        } else {
        
        	System.out.println("Property at " + address + " is already rented.");
        
        }
    }
	
	// concreate method
	 public void vacate() {
	      
		 if (isRented) {
	            isRented = false;
	            System.out.println("Property at " + address + " is now vacant.");
	        
		 } else {
	          
			 System.out.println("Property at " + address + " is already vacant.");
	        
		 }
	  
	 }
	
}
