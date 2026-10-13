package LinearSearch;

public class Iterative {

   static int[] numbers = {1,2,3,4,5,6,7,8,9,10};
	int search;
	
	private static int linear(int search) {
		
		for(int i = 0; i < numbers.length ;i++) {
			
			if(search == numbers[i]) {
				return i;
			}
			
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println(linear(11));
		
	}
	
}
