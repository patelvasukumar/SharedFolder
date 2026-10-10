package ArrayList_Final;

import java.util.Collections;
import java.util.ArrayList;

public class MinExample {
   
	public static void main(String[] args) {
    
		ArrayList<Integer> list = new ArrayList<>();
       
		list.add(1);
		list.add(2);
		list.add(-3);
		
		int min = Collections.min(list);
		int max = Collections.max(list);
        
		System.out.println(min);
        System.out.println(max);
    
	}

}

