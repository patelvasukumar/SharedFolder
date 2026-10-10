package ArrayList_Final;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearchExample {
    
	public static void main(String[] args) {
    
		ArrayList<Integer> list = new ArrayList<>();
        
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		
		Collections.sort(list);
        
		int index = Collections.binarySearch(list, 30);
        
		System.out.println(index);
   
	}

}
