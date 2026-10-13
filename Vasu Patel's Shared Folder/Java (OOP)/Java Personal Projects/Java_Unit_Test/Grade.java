package Java_Unit_Test;

import java.lang.IllegalArgumentException;

public class Grade {

	public static char determineLetterGrade(int numberGrade) {
		
		if(numberGrade < 0) {
			throw new IllegalArgumentException("Wrong");
		}
		
		else if (numberGrade < 60) {
			return 'F';
		}
		
		else if (numberGrade < 70) {
			return 'D';
		}
		
		else if (numberGrade < 80) {
			return 'C';
		}
		
		else if (numberGrade < 90) {
			return 'B';
		}
		
		else if (numberGrade < 60) {
			return 'A';
		}
		
		else {
			return 'N';
		}
		
	}
	
}
