package ConstructerChaining;

public class Car {

	String line = System.lineSeparator();
	
	public Car(){
		System.out.println("No details available");
	}
	
	public Car(String modelName, int year) {
		
		this();
		System.out.println("Model Name: " + modelName + line + "Year: " + year);
	
	}
	
	public Car(String modelName, int year, double price) {
		
		this(modelName,year);
		System.out.println("Model Name: " + modelName + line + "Year: " + year + line + "Price: " + price);
	
	}
	
	
	
}
