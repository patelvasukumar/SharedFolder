/* Assignment: Lab - 03
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Lab Professor: Professor Syed Bilgrami
 * Due Date: Novermber 19th
 * Description: Description: to get even and odd values (even: square, odd: cube)
 */ 

		// ArrayModifier is a class name, and class is created.
public class ArrayModifier {
	
	private int[] values; // array created. Name is values.
	
				// Constructer created and name is ArrayModifier.
	public ArrayModifier() {
		values = null;
	}
				
				// getter to get access the private variable.
	public int[] getValues() {
		return values;
	}

				// setter to set the private variable.
	public void setValues(int[] values) {
		this.values = values;
	}

				// Method created, and name is modifyArray
	public void modifyArray() {
		 
		// used loop to get new array. Also, do power of 2 for even and 3 for odd.
		for(int i = 0; i < values.length ;i++) {
			if (values[i] % 2 == 0) {
				values[i] = (int) Math.pow(values[i], 2);
			} else {
				values[i] = (int) Math.pow(values[i],3);
			}
		}
			
		
	}
}
