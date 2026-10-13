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
 * 
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
 *  GameManager class manages file opening, exceptions handling and other functions.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class GameManager {

	private List<Game> games;
	
	/*
	 * zero-parameterized constructor. It also calls the super class constructor.
	 */
	
	/**
	 * zero-parameterized constructor. It also calls the super class constructor.
	 */
	public GameManager() {
		this.games = new ArrayList<>();
	}
	
	/*
	 * it opens the file, read the content, initiate the object and store in the list
	 * Also, handles exception and other exceptions also. 
	 */
	
	/**
	 * it opens the file, read the content, initiate the object and store in the list
	 * Also, handles exception and other exceptions also.
	 * 
	 * @param fileName - the path of the game file
	 * @throws it throws the IOException and Other Exceptions
	 * 
	 */
	public void loadGameFromFile(String fileName) {
		
	try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
		
		String info;
		
		while((info = reader.readLine()) != null){
			
			String[] data = info.split(",");
			
			if(data.length >= 5) {
				
				String team1 = data[0].trim();
				String team2 = data[1].trim();
				String date = data[2].trim();
				String location = data[3].trim();
				String score = data[4].trim();
				
				Game gameInfo = new Game(team1,team2,date,location,score);
				games.add(gameInfo);
				
			}
			
		}
		
	} catch (IOException e) {
		System.out.println("There is something wrong while reading the file");
	} catch (Exception e) {
		System.out.println("There is something wrong. Other than reading the file");
	}
		
	}	
	
	/**
	 * it display the game info by using the loop
	 */
	public void displayGames() {
		
		System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s", "Home", "Visitor", "Date", "Location", "Score"));
        System.out.println("--------------------------------------------------------------------------------------------");
		
		for(Game gameInfo : games) {
			System.out.println(gameInfo);
		}
		
		
	}
	
}
