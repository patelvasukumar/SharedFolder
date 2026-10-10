
public class OverloadingFirst {

	public static int calculate(int a, int b) {
		return a+b;
	}
	
	public static int calculate(int a, int b, int c) {
		return a+b+c;
	}
	
	public static double calculate (double a, double b) {
		return a+b;
	}
	
	public static void display (int a, String say) {
		 System.out.println(a + say);
	}
	
	public static void display (String say) {
		System.out.println(say);
	}
	
	public static void display(double a, String say) {
		System.out.println(a + say);
		
	}
	
	public static int findarea (int a) {
		return a*a;
	}
	
	public static int findarea(int a, int b) {
		return a*b;
	}
	
	public static int[] sum(int[] car) {
		return car;
	}
	
	public static void promotion(double a, float b) {
		System.out.println(a+b);
	}
	
	public static void main(String[] args) {
		int first = OverloadingFirst.calculate(1,2);
		int second = OverloadingFirst.calculate(1, 2,3);
		double third = OverloadingFirst.calculate(2.2,3.3);
		
		System.out.println(first); 
		System.out.println(second);
		System.out.println(third);
		
		OverloadingFirst.display(5, " this is an interger");
		OverloadingFirst.display("This is a String");
		OverloadingFirst.display(2.2 , " This is a double value");
		
		System.out.println(OverloadingFirst.findarea(4));
		System.out.println(OverloadingFirst.findarea(4,2));
		
	//	System.out.println(OverloadingFirst.sum([1],[2],[3]));
		
		OverloadingFirst.promotion(3.3,4); 
	}
}
