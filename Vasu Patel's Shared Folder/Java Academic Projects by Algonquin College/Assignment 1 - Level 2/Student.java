import java.util.Scanner;

class MyException extends Exception{
	

	public MyException(String message) {
		super(message);
	}

}


public class Student extends Person {
	
    protected String studentNumber;
    protected String programName;
    protected double gpa;
    protected double baseFees;
    protected final int MAX_MARKS = 100;
    protected final double MAX_GPA = 4.0;

    public Student() {
    	
    	super();
    	
    }

    public Student(String firstName, String lastName, String email, 
    		       long phoneNumber, String studentNumber, String programName, 
    		       double gpa, double baseFees) {
         
    	super(firstName, lastName, email, phoneNumber);
        this.studentNumber = studentNumber;
        this.programName = programName;
        this.gpa = gpa;
        this.baseFees = baseFees;
  
    }
    
   
    @Override
    public void readInfo(Scanner scanner) {
        
    	// calling the method from super class by super word.
    	super.readInfo(scanner);
       
    	System.out.print("Enter student's college number: ");
        studentNumber = scanner.nextLine();
        
        System.out.print("Enter student's program name: ");
        programName = scanner.nextLine();
        
        System.out.println();
        
        readMarks(scanner);
        
        boolean loopingBaseFees = false;
         
        while(!loopingBaseFees) {
        
        try {
                
        System.out.print("Enter student's base fees: ");
        baseFees = Double.parseDouble(scanner.nextLine());
        System.out.println();
        
        loopingBaseFees = true;
        
        } catch (Exception e) {
        	
        	System.out.println("Please, enter an Integer or a decimal number! ");
        	
        }
        
        }
        
        
    }

    private void readMarks(Scanner scanner) {
    	
    	int courseCounter = 0;
    	boolean loopingCourseCounter = false;
    	
    	while(!loopingCourseCounter) {
    	try {
    		
    	System.out.print("Enter the number of courses which is taken by the student: ");
        courseCounter = Integer.parseInt(scanner.nextLine());
        
        System.out.println();
        
    	loopingCourseCounter = true;
        
    	} catch (Exception e) {
    		
    		System.out.println("Please, Enter only an integer!");
    		
    	}
    	}
    	
        int[] marks = new int[courseCounter];
        int totalMark = 0;
        
        for (int i = 0; i < courseCounter; i++) {
        
        boolean loopingMarks = false;
        	
        while(!loopingMarks) {	
        	
        try {	
        	
        	
        	System.out.print("Enter the mark for course " + (i + 1) + ". Marks per course is 100: ");
            marks[i] = Integer.parseInt(scanner.nextLine());
            
            if(marks[i] > MAX_MARKS) {
            
            	throw new MyException("The maximum limitation for the mark per course is 100.");
            	
            } else if (marks[i] < 0) {
            
            	throw new MyException("The minimum limitation for the mark per course is 0.");
            
            }
            
            totalMark = totalMark + marks[i];
            
            System.out.println();
            
            loopingMarks = true;
            
        } catch (MyException e) {
        	
        	System.out.println(e.getMessage());
        	System.out.println("Please, Enter the mark again.");
        	System.out.println();
        	
        }
        catch(Exception e) {
        	
        	System.out.println(" Please enter only an integer! ");
        	System.out.println();
        	
        }
        
        }
            
        }
       
        calculateGpa(totalMark, courseCounter);
    }

    private void calculateGpa(int totalMarks, int courseCounter) {
        
    	this.gpa = ((totalMarks / (double) (courseCounter * MAX_MARKS)) * MAX_GPA);
   
    }

    @Override
    public void printInfo() {
       
    	System.out.printf("%20s | %20s | %20s | %30s | %20d | %5.2f\n", 
    			programName, studentNumber, firstName + " " + lastName, email, phoneNumber, gpa);
    
    }
}
