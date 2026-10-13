package Week2_FileIO;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class BufferedWriterExample {

	public static void main(String[] args) throws IOException {
		
		FileWriter fw = new FileWriter("BufferedWriterExample.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("Hello, this is Buffered Writer. It is used to write char data.");
		bw.newLine();
		bw.write("Writing effectively");
		
		bw.close();
		fw.close();
		
	}
	
	
}
