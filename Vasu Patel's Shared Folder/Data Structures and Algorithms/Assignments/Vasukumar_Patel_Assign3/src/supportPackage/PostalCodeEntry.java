/**
 * Computer Engineering Technology-Computer Science: Level 3
 * 
 * Student Name: Vasukumar Patel
 * Student ID: 041153940
 * Program: Computer Engineering Technology - Computing Science
 * Course: Data Structure
 * Lab Section: 301
 * Professor: James Mwangi PhD.
 */

/**
 * make package and import class
 */
package supportPackage; 
import java.security.SecureRandom;

/**
 * class which has private fields and methods for postal code data
 */
public class PostalCodeEntry {
	
	/**
	 * variables 
	 */
	private String prefix;
	private String place_name;
	private String province;
	private String latitude;
	private String longitude;

	/**
	 * parameterize constructor
	 * 
	 * @param prefix - prefix
	 * @param place_name - name of the bus
	 * @param province - province name
	 * @param latitude - latitude
	 * @param longitude - longitude
	 */
	public PostalCodeEntry(String prefix, String place_name, String province, String latitude, String longitude) {
		this.prefix = prefix;
		this.place_name = place_name;
		this.province = province;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * parameterless constructor
	 */
	public PostalCodeEntry() {
	}

	/**
	 * parameterized constructor
	 * @param fields - field name
	 */
	public PostalCodeEntry(String[] fields) {
		this(fields[0], fields[1], fields[2], fields[3], fields[4]);
	}

	/**
	 * to get prefix
	 * @return - return prefix
	 */
	public String getPrefix() {
		return prefix; /* return prefix */
	}

	/**
	 * override ToString method
	 */
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s\n", prefix, place_name, province, latitude, longitude);
	}

	/**
	 * random class, its reference and private string variables
	 */
	final static SecureRandom rand = new SecureRandom();
	final static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final static String number = "0123456789";

	/**
	 * Method to generate random prefix
	 * @return - return prefix
	 */
	public static String getRandomPrefix() {
		int randA = rand.nextInt(alpha.length());
		int randN = rand.nextInt(number.length());
		return "K" + number.charAt(randN) + alpha.charAt(randA);
	}
	
}
