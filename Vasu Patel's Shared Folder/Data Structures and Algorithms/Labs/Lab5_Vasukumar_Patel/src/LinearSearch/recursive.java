package LinearSearch;

public class recursive {

	static int[] numbers = {1,2,3,4,5,6,7,8,9,10};
	static int search;
	
	private static int linearSearch(int[] arr, int index, int target){
		
		if(index >= arr.length) {  
			return -1;
		}
		
		if(arr[index] == target) {
			return index;
		}
		
		return linearSearch(arr,index+1,target);
	}
	
	public static void main(String[] args) {
		
		int result = linearSearch(numbers,0,search);
		
		if(result != -1) {
			System.out.println(result);
		} else {
			System.out.println("not found");
		}
		
	}
	
	
}
