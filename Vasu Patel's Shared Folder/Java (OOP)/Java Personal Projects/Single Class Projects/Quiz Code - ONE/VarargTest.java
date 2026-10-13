package Week5_Quiz;

public class VarargTest {

	public static void main(String[] args) {
		
		new VarargTest().printResult(2,"Hello World");
		
	}
	
	public void printResult(int x, String...y) {
		System.out.println(y[0]);
	}
	
}
