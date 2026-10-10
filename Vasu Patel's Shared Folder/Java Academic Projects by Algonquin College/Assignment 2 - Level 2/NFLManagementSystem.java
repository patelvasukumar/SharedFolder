/**
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
 * import scanner in order to take input from the user. 
 */
import java.util.Scanner;

/**
 *   NFLManagementSystem is the driver class and it manages different type of functions 
 *   according to the user's input.
 * 
 * @author Vasukumar Patel
 * @version 30-03-2025
 */
public class NFLManagementSystem {
	
	/*
	 * private zero-parameterized constructor.
	 */
    private NFLManagementSystem(){}
    
    /**
     * 
     * the driver method to run other methods from different classes
     * 
     * @param args - the reference
     */
	public static void main(String[] args) {
		
		/*
		 *	Object creation and scanner
		 */
		Scanner scanner = new Scanner(System.in);
		TeamManager teamManager = new TeamManager();
		PlayerManager playerManager = new PlayerManager();
	    GameManager gameManager = new GameManager();
		NFLManagementSystem NFLObj = new NFLManagementSystem();
		
		/*
		 * objects are calling the method 
		 */
		teamManager.loadTeamFromFile("teams.csv");
	    playerManager.loadPlayersFromFile("players.csv", teamManager);
	    gameManager.loadGameFromFile("games.csv");
		
		int choice = 0;
		boolean menuLoop = true;
		
		/*
		 * using the loop to manage the menu option. 
		 */
		while (menuLoop) {
			
			NFLObj.showMenu();
			
			boolean loopChoice = true;
			while(loopChoice) {
			try {
				System.out.print("Enter the choice from the menu: ");
				choice = Integer.parseInt(scanner.nextLine());
				System.out.println();
				loopChoice = false;
			} catch (Exception e) {
				System.out.println();
				System.out.println("Please, Enter an Integer");
				System.out.println();
			}
			}
			
			/*
			 * switch case to call the methods according to user's choice  
			 * 
			 */
			
			switch (choice) {
			
			case 1:
				teamManager.displayTeams();
				break;
				
			case 2:
				playerManager.displayPlayers();
				break;
				
			case 3:
				gameManager.displayGames();
				break;
				
			case 4:
				System.out.print("Enter team name: ");
		        String teamName = scanner.nextLine();
		        System.out.print("Enter city: ");
		        String city = scanner.nextLine();
		        System.out.print("Enter coach name: ");
		        String coachName = scanner.nextLine();
		        
		        int yearsOfExperience = 0;
		        boolean loopCoachEx = true;
		        while(loopCoachEx) {
		        try {
		        System.out.print("Enter coach years of experience: ");
		        yearsOfExperience = Integer.parseInt(scanner.nextLine());
		        System.out.println();
		        loopCoachEx = false;
		        } catch (Exception e){
		        	System.out.println();
					System.out.println("Please, Enter an Integer");
					System.out.println();
		        }
		        }
		        
		        teamManager.addTeam(teamName, city, coachName, yearsOfExperience);
				
				break;
			
			case 5:
				System.out.print("Enter player ID: ");
		        String playerId = scanner.nextLine();
		        System.out.print("Enter player name: ");
		        String name = scanner.nextLine();
		        
		        int age = 0;
		        boolean loopAge = true;
		        while(loopAge) {
		        try {
		        System.out.print("Enter age: ");
		        age = Integer.parseInt(scanner.nextLine());
		        System.out.println();
		        loopAge = false;
		        } catch (Exception e){
		        	System.out.println();
					System.out.println("Please, Enter an Integer");
					System.out.println();
		        }
		        }
		        
		        System.out.print("Enter team name: ");
		        String playerTeamName = scanner.nextLine();
		        System.out.print("Enter position: ");
		        String position = scanner.nextLine();
		        System.out.println();
		        
		        playerManager.addPlayer(playerId, name, age, playerTeamName, position, teamManager);
		        
				break;
				
			case 6:	
				System.out.print("Enter team name to search: ");
			    String findTeamName = scanner.nextLine();
				
			    if (teamManager.findTeam(findTeamName) != null) {
			    	System.out.println(teamManager.findTeam(findTeamName));
			    } else {
			    	System.out.println("\nThe team is not on the database");
			    }
				break;
				
			case 7: 
				
				System.out.println();
				System.out.println("*****************************************************");
                System.out.println(" The program is made by Vasukumar Patel");
                System.out.println("*****************************************************");
                System.out.println();
                System.out.println("Serializable Interface: It helps to convert object to byte stream for ObjectOutputStream.\nFor ObjectInputStream, it helps to convert from byte stream to object.");
                System.out.println();
                System.out.println("Serialization-UID: It helps to verify the object that it has not been changed.\n If it is changed, the UID cannot match, and it will give an InvalidClassException.\n also, both class must be matched.");
                System.out.println();
                System.out.println("The programmer did not use coach file because team file already have those attributes.\nAlso, if we only use team file for the data, we can maintain the sequence.\nCoach file does not have data in the proper sequence");
                scanner.close();
                menuLoop = false;
				System.exit(0);
				break;
				
			default:
				System.out.println(" You selected the wrong option");
				break;
			}
			
		}
		
	}
	
	/*
	 * it return nothing and it is a private method
	 * the prints the menu
	 * 
	 */
	
	/**
	 * it return nothing and it is a private method
	 * it prints the menu
	 */
	private void showMenu() {
		
		System.out.println();
		System.out.println("===== NFL Management System =====");
		System.out.println();
        System.out.println("1. Display Teams");
        System.out.println("2. Display Players");
        System.out.println("3. Display Scheduled Games");
        System.out.println("4. Add Team");
        System.out.println("5. Add Player to Team");
        System.out.println("6. Find Team");
        System.out.println("7. Exit");
        System.out.println();	
        
	}
	 
}
	 
	 

