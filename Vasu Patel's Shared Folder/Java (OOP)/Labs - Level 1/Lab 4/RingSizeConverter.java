// Student Name: Vasukumar Patel
// Student Number: 041153940
// Assignment Name: in-Lab 04
// Lab Professor Name: : Syed Bilgrami 
// Due Date: 05-11-2024
// Objective: to make a program which give UK and EZ size according to input of Canadian Size

// To Do: Programmer Comments throughout
public class RingSizeConverter {
	int ringSize 	= 0;	// User's Canadian ring size
	String gender	= null;	// User's gender
	
 	public RingSizeConverter() {
		this(0,"");
	}
 
	public RingSizeConverter(int ringSize, String gender) {
		this.ringSize = ringSize;
		this.gender = gender;
	}

	public double getRingSize() {
		return ringSize;
	}

	public void setRingSize(int ringSize) {
		this.ringSize = ringSize;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	// TODO Implement the following parameterless worker method
	
	// creating method
	public  String createReport() {
		String report = null;
		 
		// starting with switch case
		switch(ringSize) {
		
		case 4: 
			report = "Entered Canadian Ring Size as " + ringSize + " and gender as : " + gender + " UK Size = 5, EU Size = 18";
			break;
			
		case 6: // using if in the switch case
			if(gender.equals("male")) {
				//if input male the below will output
				report = "Entered Canadian Ring Size as " + ringSize + " and gender as : " + gender + " UK Size = 6, EU Size = 21";
			    break;
			    
			} else 
			{
				// if other than male, the below will output
				report = "Entered Canadian Ring Size as " + ringSize + " and gender as : " + gender + " UK Size = 5, EU Size = 20";
			    break;
			}
			
		case 8: 
			report = "Entered Canadian Ring Size as " + ringSize + " and gender as : " + gender + " UK Size = 9, EU Size = 23";
			break;
		
			// coding the default statement
		default:
			report = "Entered Canadian Ring Size as " + ringSize + " and gender as : " + gender + " Ring Size not found";
			break;
		}
		// the return statement to send our value to the method
		return report;
	}
	
	/*
	
	
	
	*/
}