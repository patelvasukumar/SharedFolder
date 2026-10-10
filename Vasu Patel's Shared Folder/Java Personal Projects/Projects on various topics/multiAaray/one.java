package multiAaray;

public class one {

	static int[][] numbers = {{1,2,3},{4,5,6}};
	
	
	public static void main(String[] args) {
		numbers[1][2] = 7;
		System.out.println(numbers[1][2]);
		
	}
}
