package ArrayList_Final;

import java.util.Collections;
import java.util.ArrayList;

public class ReverseExample {
 
	public static void main(String[] args) {
    
		ArrayList<String> list = new ArrayList<>();
		
		list.add("A");
		list.add("B");
		list.add("C");
		
        Collections.reverse(list);
        System.out.println(list);
    
	}

}