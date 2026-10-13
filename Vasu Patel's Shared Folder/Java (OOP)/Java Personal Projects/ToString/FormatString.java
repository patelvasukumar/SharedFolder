package ToString;

import java.text.MessageFormat; // in order to use Message Format.

public class FormatString {

	public static void main(String[] args) {
		
		String name = "Vasukumar Patel";
		int age = 18;
		double height = 170.00;

// String format
String formatString 
= String.format("name %s\nthe age is %d%nthe height is %.1f%n",name,age,height);
		System.out.println(formatString);
		
// String printf
		System.out.printf("name is %s%nage is %d%nheight is %.2f%n",name,age,height);
		
// String message format
		String line = System.lineSeparator();
		String pattern = line +"Name is {0}\nand the age is {1}" + line + "and height {2}";
	    String formattedString = MessageFormat.format(pattern,name,age,height);
	    System.out.println(formattedString);
	}
	
}
