
import java.util.Scanner;


public class ParttimeStudent extends Student {
	
	
    private double courseFees;
    private double credits;

    
    public ParttimeStudent() {
    	
    	super();
    	
    }

    
    public ParttimeStudent(String firstName, String lastName, String email, 
    					   long phoneNumber, String studentNumber, String programName, 
    					   double gpa, double baseFees, double courseFees, double credits) {
        
    	super(firstName, lastName, email, phoneNumber, studentNumber, programName, gpa, baseFees);
        this.courseFees = courseFees;
        this.credits = credits;
    
    }

    
    @Override
    public void readInfo(Scanner scanner) {
       
    	super.readInfo(scanner);
    	
    	boolean loopingCourseFees = false;
    	
    	while(!loopingCourseFees) {
    	try {
    	
    		System.out.print("Enter student's course fees: ");
    		courseFees = Double.parseDouble(scanner.nextLine());
    		System.out.println();
    		
    		loopingCourseFees = true;
    		
    	} catch (Exception e) {
    		
    		System.out.println("Please, Enter an Integer or a decimal!");
    		System.out.println();
    	
    	}
    	}
    	
    	boolean loopingCredits = false;
    	
    	while(!loopingCredits) {
    	try {
        
    		System.out.print("Enter student's credits: ");
    		credits = Double.parseDouble(scanner.nextLine());
    		
    		
    		loopingCredits = true;
    	
    	} catch (Exception e) {
    		
    		System.out.println("Please, enter an integer or a decimal!");
    		System.out.println();
    		
    	}
    	}
    	
    }

    @Override
    public void printInfo() {
        
    	double totalFees = baseFees + (courseFees * credits);
        
    	System.out.printf("%20s | %20s | %20s | %30s | %20d | %5.2f | %5.2f | %5.2f   |\n", 
    			programName, studentNumber, firstName + " " + lastName, email, phoneNumber, gpa,totalFees, credits);
    
    }
}
