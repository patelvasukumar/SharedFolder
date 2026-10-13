package ArrayList_Final;

import java.util.ArrayList;
import java.util.Collections;

public class FillExample {
  
	public static void main(String[] args) {
    
		ArrayList<String> list = new ArrayList<>();
       
		list.add("A");
		list.add("B");
		list.add("C");
		
		
		Collections.fill(list, "X");
        System.out.println(list);
   
	}

}

