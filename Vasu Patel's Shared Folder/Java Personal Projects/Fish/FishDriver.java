package Test;

import java.util.Scanner;
public class FishDriver {

	public static void main(String[] x) {
		
		Scanner key = new Scanner(System.in);
		double bigCounter = 0;
		double mediumCounter = 0;
		String repeat;
		
		do {
		System.out.println("1 = for big fish and 2 = medium fish");
		double bigFish = key.nextDouble();
						key.nextLine();
						
		if(bigFish == 1) {
			bigCounter++;
		} else if (bigFish == 2) {
			mediumCounter++;
		}
		System.out.println("Do you want to repeat?");
		repeat = key.nextLine();
		
		
		}while(!repeat.equalsIgnoreCase("yes"));
		
		Fishnet myObj = new Fishnet(bigCounter,mediumCounter);
		System.out.printf("the total is that %.2f", myObj.calculateTotal());
		
		key.close();
		
	}
}

