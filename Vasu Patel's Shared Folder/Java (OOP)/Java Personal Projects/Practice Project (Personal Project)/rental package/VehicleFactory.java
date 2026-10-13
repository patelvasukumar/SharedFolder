package rental;

public class VehicleFactory {

	public VehicleFactory() {}
	
	public Vehicle getVehicle(String type, String model, String brand, int numberPlate, boolean isAvailable) {
		
		if(type.equalsIgnoreCase("Car")) {
			
			return new Car(model,brand,numberPlate,isAvailable);
			
		} else if (type.equalsIgnoreCase("Bike")) {
			
			return new Bike(model,brand,numberPlate,isAvailable);
		}
		
		return null;
	
	}
	
	
}
