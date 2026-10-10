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
 *	Import interface for serializing  
 */

import java.io.Serializable;

/**
 *  Player class manages players' variables and override the ToString method for the object output.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class Player implements Serializable {

	/*
	 * SerialVersionUID to check that the object has not been changed while serialization 
	 * 
	 */
	private static final long serialVersionUID = 2005L;
	
	private String playerId;
	private String name;
	private int age;
	private String teamName;
	private String position;
	
	/*
	 *  Parameterized constructor for object's initialize
	 */
	
	/**
	 * 
	 * @param playerId - the unique id for players which should be different for every one
	 * @param name - the name of the player
	 * @param age - the age of the player
	 * @param teamName - the name of the player's team
	 * @param position - the name of the player's position
	 */
	public Player(String playerId, String name, int age, String teamName, String position) {
		this.playerId = playerId;
		this.name = name;
		this.age = age;
		this.teamName = teamName;
		this.position = position;
	}
	
	/**
	 * 
	 * @return - the player id
	 */
	public String getPlayerId() { return playerId; }
	
	/**
	 * 
	 * @return - the player name
	 */
	public String getName() { return name; }
	
	/**
	 * 
	 * @return - the player age
	 */
	public int getAge() { return age; }
	
	/**
	 * 
	 * @return - the team name
	 */
	public String getTeamName() { return teamName; }
	
	/**
	 * 
	 * @return - the position
	 */
	public String getPosition() { return position; }
	
	/**
	 *  Overriding the in-build ToString() method which is the part of the Object class.
	 *  Also, Object class is the top class in the java hierarchy. It is the part of
	 *  the java.lang package which is auto-imported here.
	 *  
	 */
    @Override
	public String toString() {
	
		String message = String.format("%-5s %-17s %-5d %-10s %-15s",playerId,name,age,teamName,position);
		return message;
	
	}
	
}
