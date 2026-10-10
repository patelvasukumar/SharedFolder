package Final;
import java.util.Scanner;
public class Third {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.print("Do you want to start (type 'YES' to continue): ");
        String repeat = key.nextLine();
		
		while(repeat.equalsIgnoreCase("YES")) {
			
			System.out.print("Okay");
				
			System.out.print("Do you want to start");
		    repeat = key.nextLine();
		}
		key.close();
		System.out.println("Program ended.");
	}	
}


