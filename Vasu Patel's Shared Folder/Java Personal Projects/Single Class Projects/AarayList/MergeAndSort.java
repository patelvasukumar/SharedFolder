package AarayList;

import java.util.ArrayList;
import java.util.Collections;

public class MergeAndSort {

	public static void main(String[] args) {
		
		ArrayList<Integer> list1 = new ArrayList<>();
		
		list1.add(5);
		list1.add(1);
		list1.add(9);

		ArrayList<Integer> list2 = new ArrayList<>();
		
		list2.add(7);
		list2.add(3);
		list2.add(2);
		
		list1.addAll(list2);
		Collections.sort(list1);
		
		System.out.println(list1);
		
		ArrayList<String> words = new ArrayList<>();
        words.add("Vasu");
        words.add("Vasukumar");
        words.add("Happy");
        words.add("Egor");
        words.add("Kavya");

        
        words.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        System.out.println("Sorted Words: " + words);
		
	}
	
}
