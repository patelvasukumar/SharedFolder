package ArrayList_Final;
import java.util.ArrayList;

public class Student {

	public static void main(String[] args) {
		
		ArrayList<String> studentList = new ArrayList<String>();
	
		studentList.add("Vasu"); //0
		studentList.add("Happy"); // 1
		studentList.add("Chaitanya"); // 2
		studentList.add("Kavya"); // 3
		studentList.add("Bhavya"); // 4
		
		System.out.println(studentList.size()); //5
		
		System.out.println("\n"+studentList.get(1));
		
		studentList.remove(3);
		
		// small sizing
		
		// studentList.add("Vasu"); //0
		// studentList.add("Happy"); // 1
		// studentList.add("Chaitanya"); // 2
		// studentList.add("Kavya"); // removed
		// studentList.add("Bhavya"); // 4 becomes 3 
	
		System.out.println("\n" + studentList.size()); //4
		
		studentList.add(3,"Kavya");
		
		// expanding sizing
		
		// studentList.add("Vasu"); //0
		// studentList.add("Happy"); // 1
		// studentList.add("Chaitanya"); // 2
		// studentList.add("Kavya"); // kavya added at place 3.
		// studentList.add("Bhavya"); // 3 becomes 4
		
		System.out.println("\n" + studentList.size()); // 4
		
		System.out.println("\n" + studentList.contains("Vasu")); // true
		System.out.println(studentList.contains("Vasukumar")); //false
		
		System.out.println("\n" + "For each method:");
		for(String names : studentList) {
			
			System.out.println(names);
	
		}
		
		
		studentList.set(0, "Vasukumar"); // set is different from get. It change the value. not the index(place).
		System.out.println();
		
		for(int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i));
		}
		
		System.out.println();
		System.out.println(studentList.indexOf("Vasukumar"));  //0
		System.out.println(studentList.indexOf("Happy"));  //1
		System.out.println(studentList.indexOf("Chaitanya")); //2
		System.out.println(studentList.indexOf("vasu")); //-1
		System.out.println(studentList.indexOf("vasukumar")); //-1 // Spelling matters.
		// if the list does not have the name, it will return -1 by default.
		
		System.out.println();
		System.out.println("Before clearing the list:");
        for(String names : studentList) {
			
			System.out.println(names);
	
		}
        
        studentList.clear();  // clear the list
        
        System.out.println();
		
        System.out.println("After using studentList.clear(): ");
		for(String names : studentList) {
			
			System.out.println(names);
	
		} // Loop worked but there are no values. So, print nothing. It worked but does 
		 // not have values to print
		
		System.out.println();
		System.out.println("After clearing the list, ArraySize: " + studentList.size());
		
	}
	
}
