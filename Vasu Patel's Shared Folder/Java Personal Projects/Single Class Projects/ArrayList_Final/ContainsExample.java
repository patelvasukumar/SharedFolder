package ArrayList_Final;

import java.util.ArrayList;

public class ContainsExample {
 
	public static void main(String[] args) {
    
		ArrayList<String> list = new ArrayList<>();
		
		list.add("A");
		list.add("B");
		list.add("C");
		
        boolean result = list.contains("B");
        System.out.println(result);
    
	}

}

