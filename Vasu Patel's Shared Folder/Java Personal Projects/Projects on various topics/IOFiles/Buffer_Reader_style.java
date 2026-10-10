package IOFiles;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// import java.io.*; call all three.

public class Buffer_Reader_style {

	String vasu;
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Enter a number");
		
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(in);
		
		int num = Integer.parseInt(bf.readLine());
		System.out.println(num);
		
		bf.close();
	
}
}
