package ArrayList_Final;

import java.util.ArrayList;

public class Example {
    
	public static void main(String[] args) {
       
		ArrayList<String> list = new ArrayList<>(10);

		list.add("A");
		list.add("B");
		list.add("C");
		list.add("B");
		
        String item = list.get(1);
        System.out.println("Element at index 1: " + item);

        int index = list.indexOf("B");
        System.out.println("First index of 'B': " + index);

        list.trimToSize(); // reduce capacity to 4. 
        System.out.println("List after trimToSize: " + list);
   
	}

}
