
import java.util.Scanner;


public class CollegeSystemTest {
	
	public CollegeSystemTest() {}
	
    public static void main(String[] args) {
       
    	
    	Scanner scanner = new Scanner(System.in);
        
        String collegeName;
        int studentCounter = 0; 
        
        System.out.print("Enter the name of the college: ");
        collegeName = scanner.nextLine();
        System.out.println();
        
        boolean loopingStudentCounter = false;
        
        while(!loopingStudentCounter) {
        try {
        	
        System.out.print("Enter number of students: ");
        studentCounter = Integer.parseInt(scanner.nextLine());
        
        loopingStudentCounter = true;
        
        } catch (Exception e) {
        	
        	System.out.println();
        	System.out.println("Please, enter only an integer!");
        	
        }
        }
        
        College college = new College(collegeName, studentCounter);
        
        boolean running = true;

        while (running) { 
           
        	try {
        	
        	System.out.println();
        	System.out.println("=========================================");
        	System.out.println("There are three options in the menu list.");
        	System.out.println("Please, select one of them.");
        	System.out.println();
        	System.out.println("1. Add a student");
            System.out.println("2. Print all students");
            System.out.println("3. Exit");
            System.out.println();
            System.out.println("=========================================");
            System.out.print("Enter your option: ");
           
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
            
                case 1:
                   
                	college.readStudentDetails(scanner);
                	
                    break;
                    
                case 2:
                   
                	System.out.println();
                	college.printStudentDetails();
                	
                    break;
                    
                case 3:
                    
                	System.out.println();
                	System.out.println("Goodbye... Have a nice day!");
                	System.out.println("The program is made by \" Vasukumar Patel \" . ");
                    running = false;
                	
                    System.exit(0);
                  
                    break;
                    
                default:
                	
                	System.out.println();
                	System.out.println("***** Invalid option... Please try again *****");
                	
                	
                    break;
                    
            }
        	} catch (Exception e) {
        		
        		System.out.println();
        		System.out.println("Please only enter an integer!");
        		
        	
        	}finally {
        		
   
        		
        	}
        	
        }
        
       
        scanner.close();
    }
}
