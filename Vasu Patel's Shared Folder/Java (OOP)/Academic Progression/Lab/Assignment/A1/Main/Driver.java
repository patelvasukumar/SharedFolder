import java.util.Scanner;  		// import in order to use Scanner.
					
								// Driver named class is created, which is storing inputs, outputs and calculation.
public class Driver {
								// used pre-defined method for inputs, outputs, and process.
	public static void main(String[] args) {
		Scanner display = new Scanner(System.in);	// display named Scanner created.
	
		System.out.print("Enter the value of the radius: "); // for input, getting value from user.
		double radius = display.nextDouble();
		
		System.out.print("Enter the value of the height: "); // for input, getting value from user.
		double height = display.nextDouble();
		
		PyramidCylinder theResult = new PyramidCylinder(radius, height); // object created.
		
		String message = "The Volume of the total Pyramid Cylinder is: "; // printf to display my message.
		System.out.printf("%s",message);
		
		System.out.printf("%.3f%n",theResult.calculatePyramidVolume(radius,height)); // object is calling method from PyramidCylinder Class.
		
		String whomade = "The program is created by ";	// programmer's name display.
		System.out.printf("%n%s", whomade + "Vasukumar Patel"); // concatenation used to joint.
	}
}