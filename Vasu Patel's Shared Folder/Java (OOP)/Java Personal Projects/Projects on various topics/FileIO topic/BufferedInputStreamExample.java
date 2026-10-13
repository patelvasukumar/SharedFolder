package Week2_FileIO;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

public class BufferedInputStreamExample {

	public static void main(String[] args) throws IOException {
		
		String fileName = "FileInputStreamExample.txt";
		
		FileInputStream fis = new FileInputStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		int data;
		
		while((data = bis.read()) != -1) {
		 
			System.out.print((char) data);
		
		}
		
		bis.close();
		fis.close();
		
	}
	
}
