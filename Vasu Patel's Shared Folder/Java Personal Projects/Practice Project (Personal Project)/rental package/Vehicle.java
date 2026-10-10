package rental;

public abstract class Vehicle {

	protected String model;
	protected String brand;
	protected int plateNumber;
	protected boolean isAvailable;
	
	public Vehicle() {}
	
	public Vehicle(String model, String brand, int plateNumber, boolean isAvailable) {
		this.model = model;
		this.brand = brand;
		this.plateNumber = plateNumber;
		this.isAvailable = isAvailable;
	}
	
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }
	
	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
	
	public int getPlateNumber() { return plateNumber; }
	public void setPlateNumber(int plateNumber) { this.plateNumber = plateNumber; }
	
	public boolean isAvailable() { return isAvailable; }
	public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
	
	public abstract void displayInfo();
	
	
}
