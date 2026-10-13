package Week12;

//* This class contains linear search for an array of int type, and a method
//* that generates a String representation of the array.
//*
public class LinearSearchDemo {
	// array was made class-level as there are two methods that use it.
	private double[] numbers = {3.3, 4.4, 2.45};
	
	public int findItem(double item) {
		int index = 0;
		int foundIndex = -1;
		while (index < numbers.length && foundIndex < 0) {
			if(numbers[index] == item) {
				foundIndex = index;
			}
			else {
				index++;
			} // end if
		} // end while
		return foundIndex;
	} // end method
	
	public String arrayContents() {
		String content = "";
		for(double number : numbers) {
			content = content + number + " ";
		}
		return content;
	}
} // end class
