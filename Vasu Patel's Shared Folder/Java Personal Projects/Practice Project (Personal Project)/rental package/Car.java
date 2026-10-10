package rental;

public class Car extends Vehicle implements RentalAction {

	public Car() {
		super();
	}

	public Car(String model, String brand, int plateNumber, boolean isAvailable) {
		super(model, brand, plateNumber, isAvailable);
	}

	@Override
	public void displayInfo() {
		
		System.out.println("Model: " + model);
		System.out.println("Brand: " + brand);
		System.out.println("Plate Number: " + plateNumber);
		System.out.println("is Available: " + isAvailable);
		
	}
	
	@Override
	public void rentVehicle(String model) {
		System.out.println("is Available: " + isAvailable);
	}

	@Override
	public void returnVehicle(String model) {
		System.out.println("Model: " + model);
		System.out.println("Brand: " + brand);
	}
	
}
