import java.util.Scanner;
public class point {

		public static void main(String[] args) {
			
			Scanner key = new Scanner(System.in);

			
			System.out.printf("Enter the value of the x : ");
			double x = key.nextDouble();
						key.nextLine();
			
			System.out.printf("Enter the value of the y : ");
			double y = key.nextDouble();
						key.nextLine();
			
			double epsilon = 0.01;
		//	System.out.println(x + y);
						
						if(Math.abs(x-y) < epsilon) {
									System.out.println("Same");
						} else {
									System.out.println("not same");
						}
			
			key.close();
		}
}
