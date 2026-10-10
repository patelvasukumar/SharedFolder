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
 *Import interface for serializing
 * Also, ArrayList which is the part of the List. Which contains in-builded methods 
 *   
 */
import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Team class manages Team's variables and override the ToString method for the object output.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class Team implements Serializable {

	/*
	 * SerialVersionUID to check that the object has not been changed while serialization  
	 */
	private static final long serialVersionUID = 2005L;
	
	private String teamName;
	private String city;
	private Coach coach;
	private ArrayList<Player> playersList;
	
	/*
	 * Parameterized constructor for object's initialize. Also, It invokes super class constructor. 
	 */
	
	/**
	 * It invokes super class constructor
	 * @param teamName - the name of the team
	 * @param city - the name of the city
	 * @param coach - the name of the coach
	 */
	public Team(String teamName, String city, Coach coach) {
		this.teamName = teamName;
		this.city = city;
		this.coach = coach;
		this.playersList = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return - the name of the team
	 */
	public String getTeamName() { return teamName; }
	
	/**
	 * 
	 * @return - the list of the player
	 */
	public ArrayList<Player> getPlayers() { return playersList; }
	
	/*
	 * this method adds players into the list
	 */
	
	/**
	 * 
	 * this method adds players into the list
	 * 
	 * @param player - the reference of the player class
	 * 
	 */
    public void addPlayer(Player player) {
    	playersList.add(player); 
    	
    }
    
    /**
     * Overriding the in-build ToString() method which is the part of the Object class.
	 *  Also, Object class is the top class in the java hierarchy. It is the part of
	 *  the java.lang package which is auto-imported here.
     */
	@Override 
	public String toString() {
		
		String message = String.format("%-15s %-15s %-30s %-15d",teamName,city,coach.toString(),playersList.size());
		return message;
		
	}
	
	
	
}
