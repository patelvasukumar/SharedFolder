package multiAaray;

public class random_multi {
	
	public static void main(String[] args) {
		
		int largest = 0;
		int[][] numbers = new int[3][4];
		int sums =0;
		
		for(int i = 0; i < numbers.length; i++) {
			
			for(int j = 0; j < numbers[i].length;j++) {
				
				numbers[i][j] = (int) (Math.random()*100);
				
			}
		}
		
		for(int i = 0; i < numbers.length; i++) {
			for(int j=0; j < numbers[i].length; j++) {
				sums = sums + numbers[i][j];
				System.out.print(numbers[i][j]);
				System.out.print(" ");
				
				if(numbers[i][j] > largest) {
					largest = numbers[i][j];
				}
				
			}
			System.out.println();
		}
		System.out.println("");
		System.out.println(sums);
		System.out.println("");
		System.out.println(largest);
	}
	
}
