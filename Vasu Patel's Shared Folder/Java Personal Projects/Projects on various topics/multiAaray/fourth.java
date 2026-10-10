package multiAaray;

public class fourth {

	public static void main(String[] args) {
		
		int[][] numbers = {{1,2,3},{4,5,6}};
		
		for(int[] i : numbers) {
			for(int j : i) {
				System.out.println(j);
			}
		}
	}
	
}
