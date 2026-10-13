package ArrayList_Final;

import java.util.Collections;
import java.util.ArrayList;

public class FrequencyExample {

	public static void main(String[] args) {
    
		ArrayList<String> list = new ArrayList<>();
        
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		list.add("A");
		
		int freq = Collections.frequency(list, "D");
        System.out.println(freq);
    
	}

}
