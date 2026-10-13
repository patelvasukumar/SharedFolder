package Week12;

public class RunDemoForWeek12 {

	public static void main(String[] args) {
		VariableMethodArgumentsDemo demo = new VariableMethodArgumentsDemo();
		int sum1 = demo.addNumbers(1, 2, 3);
		int sum2 = demo.addNumbers(4, 5, 6, 7);
		System.out.println(sum1);
		System.out.println(sum2);
	}

}