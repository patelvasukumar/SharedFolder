package Array;

public class six {

	public static void main(String[] args) {
		
		int[] numbers = {1,2,3,4,6,5,7};
		int largest = numbers[0];
		
		for(int i = 1; i < numbers.length; i++) {
			
			if(numbers[i] > largest) {
				
				largest = numbers[i];
			}
			
		}
		
		System.out.println("The largest: " + largest);
		
		for(int i = 0; i < numbers.length; i++) {
			
			if(i > 0) {
			System.out.print("|");
			}
			System.out.print(numbers[i]);
		}
		
	}
	
}
