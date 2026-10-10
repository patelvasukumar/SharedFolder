package ArrayList_Final;

import java.util.*;

public class AddAllExample {
 
	public static void main(String[] args) {
    
		ArrayList<String> list = new ArrayList<>();
		
		list.add("A");
		list.add("B");
		
		Collections.addAll(list, "A", "B", "C");
        System.out.println(list);
    
	}

}

