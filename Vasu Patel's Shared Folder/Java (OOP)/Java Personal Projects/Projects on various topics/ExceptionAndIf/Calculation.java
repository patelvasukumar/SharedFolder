package ExceptionAndIf;

import java.io.IOException;

class myException extends Exception{
	
	public myException(String sms) {
		super(sms);
	}
	
}

public class Calculation {

	
	public static void calculation(int a, int b) {
		
		try {
			
			if(a == 1) {
				throw new myException("The array is full, dude.");
			}
			int sum = a +b;
			System.out.println(sum);
			
		}catch(myException e) {
			System.out.println(e);
		} finally {
			System.out.println("It worked");
		}
		
	}
	
}
