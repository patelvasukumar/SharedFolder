package ArrayList_Final;

import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.Map.Entry;

public class MapInterfaceExample {

	public static void main(String[] args) {
		
		// Map<String, Integer> days = new LinkedHashMap<>();
		Map<String, Integer> days = new LinkedHashMap<>();
		
		
		days.put("Mon",1);
		days.put("Tue",2);
		days.put("Wed",3);
		days.put("Thu",4);
		days.put("Fri",5);
		days.put("Sat",6);
		days.put("Sun",7);
		days.put("NoDay",8);
		
		for ( Entry<String, Integer> entry : days.entrySet()) {
			
			System.out.println(entry.getKey() + " : " + entry.getValue());
			
		}
		
		System.out.println(days.get("NoDay"));
		
	}
	
}
