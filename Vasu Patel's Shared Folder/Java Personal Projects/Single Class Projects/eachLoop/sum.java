package eachLoop;

public class sum {

	static int[] numbers = {1,1,1};
	static int i = 0;
	
	public static void main(String[] args) {
		
		for(int values : numbers) {
			
					i = i + values;
		}	
			System.out.println(i);
		
	}
	
}
