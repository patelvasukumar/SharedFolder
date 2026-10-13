package test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test1 {

	public static void main(String[] args) {
		
		String[] arr = new String[15];
		
		try(BufferedReader reader = new BufferedReader(new FileReader("vocab.txt"))){
			
			String line;
			int i = 0;
			
			while( (line = reader.readLine()) != null ) {
				
				arr[i] = line;
				i++;
			}
			
			
		}catch (IOException ex) {
			System.out.println(" Cannot open the file : " + ex);
		}
		
		for(int j = 0; j < arr.length; j++) {
			System.out.println(arr[j]);
		}
		
	}
	
}
