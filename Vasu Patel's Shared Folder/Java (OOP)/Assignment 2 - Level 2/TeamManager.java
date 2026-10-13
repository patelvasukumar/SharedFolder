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
 * Also, ArrayList which is the part of the List. Which contains in-builded methods 
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  TeamManager class manages file opening, exceptions handling and other functions.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class TeamManager {

	private ArrayList<Team> teamsList;
	
	/*
	 * zero-parameterized constructor. It also calls the super class constructor.
	 */
	
	/**
	 * zero-parameterized constructor. It also calls the super class constructor.
	 */
	public TeamManager() {
		this.teamsList = new ArrayList<>();
	}
	
	/**
	 *  Getter for team List
	 * @return - it return the team list
	 */
	public ArrayList<Team> getTeams(){
		return teamsList;
	}
	
	/*
	 * it opens the file, read the content, initiate the object and store in the list
	 * Also, handles exception
	 * 
	 */
	
	/**
	 * 
	 * it opens the file, read the content, initiate the object and store in the list
	 * handles IOException and other type of the exceptions
	 * 
	 * @param fileName - file path address 
	 */
	public void loadTeamFromFile(String fileName) {	
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			
			String line;
			
			while((line = reader.readLine()) != null) {
				
				String[] info = line.split(",");
				
				if(info.length >= 4) {
					
					String teamName = info[0].trim();
					String city = info[1].trim();
					String coachName = info[2].trim();
					int yearsOfExperience = Integer.parseInt(info[3].trim());
					
					Coach coachRef = new Coach(coachName, yearsOfExperience);
					
					Team teamRef = new Team(teamName,city,coachRef);
					
					teamsList.add(teamRef);
					
				}
				
			}
			
		} catch (IOException e) {
			System.out.println("There is something wrong while reading the file");
		
		} catch (Exception e){
			System.out.println("There is something wrong. Other than reading the file");
			}
		
	}

	/*
	 * it returns nothing and display the content by using the loop
	 */
	
	/**
	 * it returns nothing and display the content by using the loop
	 */
	public void displayTeams() {
		
		System.out.println("--------------------------------------------------------------------------");
        System.out.println(String.format("%-15s %-15s %-30s %-50s", "Team", "City", "Coach", "Players"));
        System.out.println("--------------------------------------------------------------------------");
		
		for(Team teamRef : teamsList) {
			System.out.println(teamRef);
		}
		
	}
	
	/*
	 * it adds new team which is given by the user. Also, it do not promote user to add
	 * the team which is already on the team
	 */
	
	/**
	 * it adds new team which is given by the user. Also, it do not promote user to add
	 * the team which is already on the team
	 * 
	 * @param teamName - the name of the new team
	 * @param city - the name of the new city
	 * @param coachName - the name of the new coach or old coach
	 * @param yearsOfExperience - the years of experience 
	 */
	public void addTeam(String teamName, String city, String coachName, int yearsOfExperience) {
		
		for (Team teamRef : teamsList) {
	        if (teamRef.getTeamName().equalsIgnoreCase(teamName)) {
	            System.out.println("The team is already in the database. Please, Enter another team.");
	            return;
	        }
	    }
		
		Coach coachRef = new Coach(coachName, yearsOfExperience);
		Team teamRef = new Team(teamName, city, coachRef);
		
		teamsList.add(teamRef);
		System.out.println();
		System.out.println(teamName + " is added successfully.");
		
	}
	
	/*
	 *	it finds the name of the team from the list 
	 */
	
	/**
	 * it finds the name of the team from the list
	 * 
	 * @param teamName the name of the team
	 * @return - if team found it returns the name. Otherwise, it returns nothing.
	 */
	public Team findTeam(String teamName) {
		
		
		for(Team teamRef : teamsList) {
			
			if(teamRef.getTeamName().equalsIgnoreCase(teamName)) {
				return teamRef;
			} 
		
		}
		
		return null;
	
	}
	
	
}
