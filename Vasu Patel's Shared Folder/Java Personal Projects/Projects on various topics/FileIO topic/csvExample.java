package Week2_FileIO;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class csvExample {

	public static void main(String[] args) {
		
		String csvFile = "csvExample.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			
			String line;
			line = br.readLine();
			String[] headers = line.split(",");
			System.out.println("Headers: " + String.join(",", headers));
			
			while((line=br.readLine()) != null) {
				String[]  data =line.split(",");
				System.out.println("Data: " + String.join(",", headers));
			}
			
			
		} catch (IOException e) {
			
			System.out.println(e.toString());
		
		}
		
	}
	
}
