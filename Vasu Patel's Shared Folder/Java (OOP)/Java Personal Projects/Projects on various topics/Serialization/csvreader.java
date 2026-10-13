package Serialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class csvreader {

	public static void main(String[] args) {
		
		String csvFile = "C:\\Users\\l\\Downloads\\Book1.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			
			String line;
			
			// Read the header
			line = br.readLine();
			String[] headers = line.split(",");
			System.out.println("Headers: " + String.join(",", headers));
			
			// Read and print the data
			while((line = br.readLine()) != null) {
				String[] data = line.split(",");
				System.out.println("Data: " + String.join(",",data));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
