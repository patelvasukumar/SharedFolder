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
 * importing buffer and file reader to read the file. buffer reader makes the buffer zone, and
 * then file reader read the line by chunks rather than characters.
 * 
 * IOException to handle file exceptions while opening it.
 * 
 * Also, List which is the part of the List. Which contains in-builded methods 
 * The programmer imported ArrayList because of the constructor. In order to call 
 * super class ArrayList constructor.
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  PlayerManager class manages file opening, exceptions handling and other functions.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class PlayerManager {

	
	private List<Player> players;
	
	/*
	 * zero-parameterized constructor. It also calls the super class constructor.
	 */
	
	/**
	 * zero-parameterized constructor. It also calls the super class constructor.
	 */
	public PlayerManager() {
		this.players = new ArrayList<>();
	}
	
	/*
	 *	it opens the file, read the content, initiate the object and store in the list
	 * Also, handles exception 
	 * 
	 */
	
	/**
	 * it opens the file, read the content, initiate the object and store in the list
	 * Also, handles exception and other exceptions also.
	 * 
	 * @param fileName - the path of the file
	 * @param teamManager - the TeamManager class reference
	 * 
	 */
	
	public void loadPlayersFromFile(String fileName, TeamManager teamManager) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			
			String line;
			
			while((line = reader.readLine()) != null) {
				
				String[] info = line.split(",");
				
				if(info.length >= 5) {
					
					String playerId = info[0].trim();
					String name = info[1].trim();
					int age = Integer.parseInt(info[2].trim());
					String teamName = info[3].trim();
					String position = info[4].trim();
					
					Team teamRef = teamManager.findTeam(teamName);
					
					if(teamRef != null) {
						
						Player playerRef = new Player(playerId,name,age,teamName,position);
						players.add(playerRef);
						teamRef.addPlayer(playerRef);
						
					} else {
						
						System.out.println(" The team is not on the database");
						
					}
					
				}
				
			}
			
		} catch (IOException e) {
			System.out.println("There is something wrong while reading the file");
		} catch (Exception e) {
			System.out.println("There is something wrong. Other than reading the file");
		}
		
	}
	
	/*
	 * it returns nothing and display the list by using the loop.
	 */
	
	/**
	 * it returns nothing and display the list by using the loop.
	 */
	public void displayPlayers() {

	 System.out.println("-------------------------------------------------------------------");
     System.out.println(String.format("%-5s %-17s %-5s %-10s %-15s", "ID", "Player", "Age", "Team", "Position"));
     System.out.println("-------------------------------------------------------------------");
		
		
		for(Player playerRef : players) {
			System.out.println(playerRef);
		}
		
	}
	
	/*
	 * if there is a team, it adds the player. Otherwise, it shows that first add the team.
	 */
	
	/**
	 * if there is a team, it adds the player. Otherwise, it shows that first add the team.
	 * 
	 * @param playerId - the name of the new playerid
	 * @param name - the name of the new player
	 * @param age - the age of the new player
	 * @param teamName - the teamName of the new palyer
	 * @param position - the position of the new player
	 * @param teamManager - TeamManager class reference
	 */
	public void addPlayer(String playerId, String name, int age, String teamName, String position, TeamManager teamManager) {
		
		Team teamRef= teamManager.findTeam(teamName);
		
		if(teamRef != null) {
			
			Player playerRef = new Player(playerId,name,age,teamName,position);
			players.add(playerRef);
			teamRef.addPlayer(playerRef);
			System.out.println("The player is added successfully");
			
		} else {
			
			System.out.println("The team is not on the database. Please, enter the team first");
		
		}
		
	}
	
	
	
}
