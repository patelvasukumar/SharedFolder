package Serialization;

import java.io.File;

public class DeleteingFileIO {

	 public static void main(String[] args) { 
		   
		 File myObj = new File("filename.txt"); // You can delete the folder if you put its name.
		 
		 if (myObj.delete()) { 
		 
			 System.out.println("Deleted the file: " + myObj.getName());
		    
		 } else {
		  
			 System.out.println("Failed to delete the file.");
		    
		 } 
		 } 
	
}
