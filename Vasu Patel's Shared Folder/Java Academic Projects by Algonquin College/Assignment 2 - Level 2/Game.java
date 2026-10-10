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
 * 
 */
import java.io.Serializable;

/**
 *  Game class manages Game's variables and override the ToString method for the object output.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class Game implements Serializable {

	/*
	 * SerialVersionUID to check that the object has not been changed while serialization  
	 */
	private static final long serialVersionUID = 2005L;
	
	private String team1;
	private String team2;
	private String date;
	private String location;
	private String score;
	
	/*
	 * Parameterized constructor for object's initialize
	 */
	
	/**
	 * 
	 * @param team1 - the name of the homeground team
	 * @param team2 - the name of the visitor team
	 * @param date -  the date of the game
	 * @param location - the location of the game
	 * @param score - the score of the game
	 */
	public Game (String team1, String team2, String date, String location, String score) { 
        this.team1 = team1; 
        this.team2 = team2; 
        this.date = date; 
        this.location = location; 
        this.score = score; 
    } 
	
	/**
	 * 
	 * Overriding the in-build ToString() method which is the part of the Object class.
	 *  Also, Object class is the top class in the java hierarchy. It is the part of
	 *  the java.lang package which is auto-imported here.
	 * 
	 */
	@Override
	public String toString() {
		
		String message = String.format("%-15s %-15s %-15s %-15s %-15s",team1,team2,date,location,score);
		return message;
		
	}
	
	
	
}
