package Array;

public class Third {

	int[] numbers = new int[10]; // array making and setting the values to the 10.
	static int[] nums = {1,2,3}; // second way

	// numbers[10] = 10; Boundary error. 
	
	//   final int LENGTH = 10;			
	//   double[] data = new double[length];
	
	int[] values = nums;
	
	
	public static void main(String[] args) {
		
	for(int i = 0; i < nums.length ; i++) {
		
		System.out.println(nums[i]);
		
	}
		
	}
	
	
}
