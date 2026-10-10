/* Program: CET LEVEL 2
 * STUDENT NAME: VASUKUMAR PATEL
 * STUDENT NUMBAR: 041153940
 * COURSE: CST8132_310 OOP
 * PROFESSOR: JAMES MWANGI PHD.
 * */

public class FibonacciGenerator {
	
	public FibonacciGenerator() {}
	
	public void GenerateFibonacciSequence(int number) {
		
		
		int first = 1;
		int second = 2;
		int counter = 2;
		
		System.out.print(first + " " +second + " ");
		
		while(counter < number) {
			
			int nextFibonacci = 0;
			
		    nextFibonacci  = first + second ;
			System.out.print(nextFibonacci + " ");
			first = second;
			second = nextFibonacci;
			counter++;
	
		}
		
		
	}
	
}
