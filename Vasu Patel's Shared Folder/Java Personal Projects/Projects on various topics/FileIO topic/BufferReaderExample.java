package Week2_FileIO;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BufferReaderExample {

	public static void main(String[] args) throws IOException {
		
		String fileName = "FileReaderExample.txt";
		
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		while((line = br.readLine()) != null) {
			System.out.print(line);
		}
		
		br.close();
		fr.close();
		
	}
	
	
}
