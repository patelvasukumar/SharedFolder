package Week12;

/*
 * This class simulates rolling a dice 1 million times, and provides a
 * report on the resulting faces that landed 'up' as well as the time
 * it took the program to complete the operation.
 */
public class Random {

	public static void main(String[] args) {
		final int DICE_SIZE = 6;
		final int MAX_SAMPLES = 1000000; // can go higher, this is 1 million.
		long startTime; // deferred initialization(s)
		long stopTime; 
		long runTime;
		double seconds;
		int result; // represents the dice-face that landed 'up'
		int[] faceCounts = new int[DICE_SIZE]; // counts each side that was 'up'
		
		startTime = System.currentTimeMillis();
		for(int count = 1; count <= MAX_SAMPLES; count++) {
			result = (int)(Math.random() * 6 + 1);
			faceCounts[result - 1] = faceCounts[result - 1] + 1;
			// faceCounts[result - 1]++;
		}
		stopTime = System.currentTimeMillis();
		
		// process times
		runTime = stopTime - startTime;
		seconds = runTime / 1000.0;
		
        // Reports
		for(int index = 0; index < faceCounts.length; index++) {
			System.out.printf("Count for %d is: %d%n",
					(index + 1), faceCounts[index]);
		}
		System.out.println("Run time milliseconds: " + runTime);
		System.out.println("Run time seconds:" + seconds);
		//System.out.println("Use java.util.Arrays.toString()"); // week 12
		//System.out.println(java.util.Arrays.toString(faceCounts)); // week 12
		
	}

}