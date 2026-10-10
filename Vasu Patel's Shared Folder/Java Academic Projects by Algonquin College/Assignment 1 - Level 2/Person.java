
import java.util.Scanner;

public class Person {
	
	
    protected String firstName;
    protected String lastName;
    protected String email;
    protected long phoneNumber;

    public Person() {}

    public Person(String firstName, String lastName, String email, long phoneNumber) {
        
    	this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    
    }
    
    public void readInfo(Scanner scanner) {
      
    	System.out.println();
    	System.out.print("Enter the first name of the student: ");
        firstName = scanner.nextLine();
        
        System.out.print("Enter the last name of the student: ");
        lastName = scanner.nextLine();
        
        System.out.print("Enter student's email ID: ");
        email = scanner.nextLine();
        
        boolean loopingPhoneNumber = false;
        while(!loopingPhoneNumber) {
        try {
        	
        System.out.print("Enter student's phone number: ");
        phoneNumber = Long.parseLong(scanner.nextLine());
        			 
        loopingPhoneNumber = true;
        
        } catch(Exception e) {
        	
        	System.out.println();
        	System.out.println("The phone number cannot be a String.\nPlease, enter again!");
        	
        	
        }
        }
    }

    public void printInfo() {
        System.out.printf("%10s | %10s | %10d\n", firstName + " " + lastName, email, phoneNumber);
    }
}
