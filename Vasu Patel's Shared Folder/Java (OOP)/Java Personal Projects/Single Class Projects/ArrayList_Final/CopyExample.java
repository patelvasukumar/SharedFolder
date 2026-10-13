package ArrayList_Final;

import java.util.ArrayList;
import java.util.Collections;

public class CopyExample {
  
	public static void main(String[] args) {

		ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        
        list1.add("A");
        list1.add("B");
        list1.add("C");
        
        list2.add("X");
        list2.add("Y");
        list2.add("Z");
        list2.add("V");        
        
        Collections.copy(list2, list1);
        System.out.println(list2);
    
	}

}
