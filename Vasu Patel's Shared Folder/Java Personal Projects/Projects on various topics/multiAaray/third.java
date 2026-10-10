package multiAaray;

public class third {

	public static void main(String[] args) {
		
		int[][] numbers = {{1,2,3},{4,5}};
		
		for(int[] row : numbers) {
			
			for(int i : row) {
				System.out.println(i);
			}
			
		}
		
	}
	
}
