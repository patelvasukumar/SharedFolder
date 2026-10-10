package Generic;

public class GenericMethodTest {

	public static void main(String[] args) {
		
		Integer[] intArray = {1,2,3,4,5};
		Double[] doubleArray = {1.1,2.2,3.3,4.4,5.5,6.6,7.7};
		Character[] charArray = {'V','A','S','U'};
		
		printArray(intArray);
		printArray(doubleArray);
		printArray(charArray);
		
	}
	
	public static <T> void printArray(T[] inputArray) {
		
		for ( T element : inputArray) {
			
			System.out.println(element);
			
		}
		System.out.println();
	}
	
}
