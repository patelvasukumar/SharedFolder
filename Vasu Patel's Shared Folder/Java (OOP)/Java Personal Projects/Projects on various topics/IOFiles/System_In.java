package IOFiles;

public class System_In {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Enter a number");
		int num = System.in.read();
		
		System.out.println(num - 48);
		
	}
		
}
