/*
 * Program: Computer Engineering Technology - Computing Science [Academic Level: 2 ]
 * Course: CST8132 Object Oriented Programming 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Section #: 312
 * Professor: Dr. James Mwangi
 * Assignment - 2 : NFL Management System
 * Due Date: 30th March 2025 at 23:59
 * Description: NFL Management System to manage Player's, team's, game's, coach's data
 * Note: Used parse in order to convert from String to int or double.
 */

/*
 * Import interface for serializing 
 */
import java.io.Serializable;


/**
 *  Coach class manages coach's variables and override the ToString method for the object output.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class Coach implements Serializable {

	/*
	 * SerialVersionUID to check that the object has not been changed while serialization  
	 */
	private static final long serialVersionUID = 2005L;
	
	private String name;
	private int yearsOfExperience;
	
	/*
	 * Parameterized constructor for object's initialize
	 */
	
	/**
	 * 
	 * @param name - the name of the coach
	 * @param yearsOfExperience - the year of experience 
	 */
	public Coach(String name, int yearsOfExperience) { 
        this.name = name;  
        this.yearsOfExperience = yearsOfExperience; 
    } 
	 
	/**
	 * Overriding the in-build ToString() method which is the part of the Object class.
	 *  Also, Object class is the top class in the java hierarchy. It is the part of
	 *  the java.lang package which is auto-imported here.
	 */
	@Override
	public String toString() {
		
		String message = String.format("%-15s (%-2d years)",name,yearsOfExperience);
		return message;
		
	}
	
}
