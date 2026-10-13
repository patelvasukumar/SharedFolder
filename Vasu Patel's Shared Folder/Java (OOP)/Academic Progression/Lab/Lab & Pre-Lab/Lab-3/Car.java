/*
 * Student Name: Stanley Pieda
 * Lab Professor: Professor Stanley Pieda
 * Due Date: The due date
 * Description: Sample Solution Exercise 02 (22F)
 */

/*
 * This class models a Car, with attributes of
 * vehicleIdentificationNumber, mileage, and invoicePrice.
 */
public class Car {
	private String vehicleIdentificationNumber; // see [1] for more information
	private double mileage; // kilometers (km)
	private double invoicePrice; // dollars '$'

	/* Accessor for vehicleIdentificationNumber */
	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	/* Mutator for vehicleIdentificationNumber */
	public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	/*
	 * Accessor for mileage.
	 * The returned value is in kilometers
	 */
	public double getMileage() {
		return mileage;
	}

	/*
	 * Mutator for mileage.
	 * The passed value is expected to be in kilometers
	 */
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	/*
	 * Accessor for invoicePrice
	 * The returned value is in dollars.
	 */
	public double getInvoicePrice() {
		return invoicePrice;
	}

	/*
	 * Mutator for invoicePrice
	 * The passed value is expected to be in dollars.
	 */
	public void setInvoicePrice(double invoicePrice) {
		this.invoicePrice = invoicePrice;
	}
	
	/*
	 * This method generates a String to represent the
	 * data held in this object.
	 * Note that the Java API using method String.format
	 * was used to format the decimal places.
	 * Details:
	 * vehicle identification number, followed by a comma
	 * mileage to 1 decimal place, followed by ‘(km)’ and a comma
	 * invoicePrice to 2 decimal places, preceded by ‘$’
	 */
	public String toString() {
		String formatString = "%s, %.1f (km), $%.2f";
		String report;
		report = String.format(formatString, 
				vehicleIdentificationNumber, mileage, invoicePrice);
		return report;
	}
}
