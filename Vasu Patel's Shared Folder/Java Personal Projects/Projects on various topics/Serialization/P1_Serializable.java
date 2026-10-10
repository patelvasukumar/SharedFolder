package Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class P1_Serializable {
		
		public static void main(String[] args) {
		
		P1_Student myObj1 = new P1_Student(01, "Vasukumar Patel", 100.00);
		
		try{
			FileOutputStream fObj = new FileOutputStream("Project1.txt");
			ObjectOutputStream Oobj = new ObjectOutputStream(fObj);
			
			Oobj.writeObject(myObj1);
			
			fObj.close();
			Oobj.close();
			
			System.out.println("Object has been serialized to persist it");
			
		} catch (IOException e) { e.printStackTrace();	
		} 
		
		P1_Student myObj2 = null;
		
		try {
			FileInputStream Fobj = new FileInputStream("Project1.txt");
			ObjectInputStream Oobj = new ObjectInputStream(Fobj);
			
			myObj2 = (P1_Student) Oobj.readObject();
			
			Fobj.close();
			Oobj.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (myObj2 != null) {
            System.out.println("Student Number: " + myObj2.getId());
            System.out.println("Student Name: " + myObj2.getName());
            System.out.println("Grade: " + myObj2.getGrade());
        }
		
		
}
}
