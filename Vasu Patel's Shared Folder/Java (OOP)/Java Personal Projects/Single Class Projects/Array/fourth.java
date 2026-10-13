package Array;

public class fourth {

		int totalScore;
	
		public static void addScores(int[] values) {
			for(int i = 0; i < values.length; i++) {
				 System.out.println(values[i]);
			}
		}
	
		public static void main(String[] args) {
			int[] scores = new int[3];
			scores[0] = 10;
			scores[1] = 20;
			scores[2] = 30;
			
			int[] values = {1, 2, 3};
			
			addScores(scores);
			addScores(values);
			// public int[] getScores(); it is possible.
			}
	
}
