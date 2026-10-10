
import java.util.Scanner;


public class FulltimeStudent extends Student {

	
    private double tuitionFees;
    
    
    public FulltimeStudent() {
    	
    	super();
    	
    }

    
    public FulltimeStudent(String firstName, String lastName, String email, 
    					   long phoneNumber, String studentNumber, String programName, 
    					   double gpa, double baseFees, double tuitionFees) {
       
    	super(firstName, lastName, email, phoneNumber, studentNumber, programName, gpa, baseFees);
        this.tuitionFees = tuitionFees;
    
    }

    
    @Override
    public void readInfo(Scanner scanner) {
       
    	super.readInfo(scanner);
    	
    	
    	
    	boolean loopingTuitionFees = false;
    	
        while(!loopingTuitionFees) {
    	try {
    	
    	System.out.print("Enter student's tuition fees: ");
        tuitionFees = Double.parseDouble(scanner.nextLine());
        
        loopingTuitionFees = true;
        
    	} catch (Exception e) {
    	
    		System.out.println("Please, Enter an integer or a decimal number!");
    	
    	}
        }
        
    }

    @Override
    public void printInfo() {
       
    	
    	System.out.printf("%20s | %20s | %20s | %30s | %20d | %5.2f | %5.2f |\n", 
    			programName, studentNumber, firstName + " " + lastName, email, phoneNumber, gpa, (tuitionFees+baseFees) );
    	
    
    }
}
