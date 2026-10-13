
import java.util.Scanner;


public class College {

    private String collegeName;
    private int studentCounter;
    private Student[] students; 
    private int index = 0;
    
    
    public College() {}
    
    
    public College(String collegeName, int count) {
        this.collegeName = collegeName;
        this.studentCounter = count;
        this.students = new Student[count];
    } 

    
    private void printTitle() {
    	
    	System.out.println();
    	System.out.println("Enter details of student " + (index+1) + " : ");
    	System.out.println("=============================================");
    	System.out.println();
        System.out.println("1. Fulltime Student");
        System.out.println("2. Parttime Student");
        System.out.print("Enter student type: ");
    
    }
    
    
    public void readStudentDetails(Scanner scanner) {
    	
    	boolean loopingError= false;
    	
    	while(!loopingError) {
    	try {
            
    		if (index >= studentCounter) {
    			
    			System.out.println();
                System.out.println("Array is full. Cannot add more students.");
                return;
            }
    		
    		
    		boolean loopingStudentType = false;
    		
    		while(!loopingStudentType) {
    		
    		printTitle();
            int studentType = Integer.parseInt(scanner.nextLine());
            

            if (studentType == 1) {
            	
            	Student students = new Student();
            	students = new FulltimeStudent();
            	this.students[index] = students;
            
            	loopingStudentType = true;
            	loopingError = true;
           
            } else if (studentType == 2) {
            	
            	Student students = new Student();
            	students = new ParttimeStudent();
            	this.students[index] = students;
            	
            	loopingStudentType = true;
            	loopingError = true;
            
            } else {
            	
            	System.out.println();
            	System.out.println("***** Wrong student type *****");
            	
            	loopingStudentType = false;
            	loopingError = false;
            }
    		}

            students[index].readInfo(scanner);
            
            System.out.println();
            System.out.println("The process has been completed.");
            System.out.println("Data for student " + (index + 1) + " is added into the system.");
            System.out.println("The program is going back to the menu list.");
            
            index++;
            
        } catch (Exception exceptionObj) {
          
        	System.out.println();
        	System.out.println("Please, enter only an integer!");
        	loopingError = false;
        }
    }
    }
    
    
    public void printStudentDetails() {
       
    	if (index == 0) {
    		
    		System.out.println();
    		System.out.println("***** No Students to print *****");
            return;
        
    	}
    	
    	System.out.println("************************************");
    	System.out.println(collegeName + " - List of students");
    	System.out.println("************************************");
    	System.out.println();
    	System.out.printf("%20s | %20s | %20s | %30s | %20s | %5s | %6s  | %6s |\n", 
    			"Program", "Student#", "Name", "Email", "Phone Number", "GPA", "Fees", "Credits");
        
    	
    	for (int i = 0; i < index; i++) {
        
    		students[i].printInfo();
       
    	}
    }
    
}
