import java.util.Scanner;

public class andOr {
		
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("Enter the value for x");
		 double x = input.nextDouble();
				input.nextLine();
		
		
		//		if (input.hasNextDouble()) {
			//		double x = input.nextDouble();
			//		System.out.println("Your enter double number is:" + x);
			//	} else {
			//		System.out.println("This is invalid");
			//		input.nextLine();
			//	}
		
		
				System.out.println("Enter the value for y");
		 double y = input.nextDouble();
				input.nextLine();
		
	/*
				if(input.hasNextDouble()) {
					double y = input.nextDouble();
					System.out.println("Your enter double number is:" + y);
					input.nextLine();
				} else {
					System.out.println("This is invalid");
				}
		*/		
		// System.out.println("Enter the value for z");
		// double z = input.nextDouble();
		//		input.nextLine();
		
		
		if(x == 3.4 && (y == 10.3 || y == 10.6 || y == 10.0) ){
			System.out.println("this is level 1");
		} 
			else if (x == 3.5 && (y == 10.3 || y == 10.0 || y == 10.6)) {
				System.out.println("this is level 2");
		} 
			else if (x == 3.6 && (y == 10.3 || y == 10.0 || y == 10.6)) {
				System.out.println("this is level 3");
		}   
			else {
				System.out.println("this is not valid");
			}
		
	
		input.close();
	} 
}
