/**
 * 
 * Program: Computer Engineering Technology - Computing Science [Academic Level: 2 ]
 * Course: CST8132 Object Oriented Programming 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Section #: 312
 * Professor: Dr. James Mwangi
 * L2
 */

			// creating the class.
public class NFLGamesData {

			// Main method: the starting point.
    public static void main(String[] args) {
        
    		// aarays with values
        int[][] scores = {{22, 30, 25},{18, 24, 16},{27, 21, 22},{17, 28, 19}};
        String[] names = {"Marlon", "Myles", "Nick", "Tyler"};
        	
        	// aarays for new values storage.
        int[] playerTotals = new int[names.length];
        double[] averages = new double[names.length];
        int[] gameTotals = new int[scores[0].length];

        	// declaration with predefined value.
        int highestScore = 0;
        String highestScorer = "";
        
        System.out.println("NFL Game Statistics for Baltimore Ravens Players");
        System.out.println("");
        System.out.println("Total points for each player:");
       
        // first loop: it will calculate and sum the values for players and each game points
        // also, the average values everything in one single loop.
        for (int i = 0; i < scores.length; i++) {
        	
            for (int j = 0; j < scores[i].length; j++) {
            
            	playerTotals[i] = playerTotals[i] + scores[i][j];
            	gameTotals[j] = gameTotals[j] + scores[i][j];
            	averages[i] = (double) playerTotals[i] / scores[i].length;
            }
            
            System.out.println(names[i] + ": " + playerTotals[i]);
        }
        
        
        System.out.println("");
        System.out.println("Average points per game for each player:");
        
        // second loop: it will print 
        for (int i = 0; i < scores.length; i++) {  
            System.out.printf("%s: %.2f%n", names[i], averages[i]);
        }
        
        System.out.println("");
        System.out.println("Total points for each game:");
        
        // 3 loop: it will print
        for (int j = 0; j < scores[0].length; j++) { 
        	System.out.println("Game " + (j + 1) + ": " + gameTotals[j]);
        }

        // loop 4: it will find the largest number.
        for (int i = 0; i < playerTotals.length; i++) {
          
        	if (playerTotals[i] > highestScore) {
            
        		highestScore = playerTotals[i];
                highestScorer = names[i];
                
            }
        }
       
        System.out.println("");
        System.out.println("Player with the highest total score: " + highestScorer + " with " + highestScore);
    }
}
