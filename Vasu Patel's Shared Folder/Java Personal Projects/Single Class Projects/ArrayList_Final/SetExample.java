package ArrayList_Final;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import java.util.Set;
import java.util.SortedSet;

public class SetExample {

	public static void main(String[] args) {
	
	// remove the comments and see the difference between calling both constructor
	// They both are the part of a class : (LinkedHashSet and HashSet class)
		
	// below TreeSet<>() constructors, work the same way, but just the implementation is different
		
	 // Set<String> set = new TreeSet<>();
	 SortedSet<String> set = new TreeSet<>();
		
	// Set<String> set = new LinkedHashSet<>(); // LinkedHashSet constructor : does not change the order
	// Set<String> set = new HashSet<>(); // changed the order.
	
	// HashSet does not guarantee any particular order of its elements; 
	// the iteration order can appear random and may change over time.
	
	set.add("Vasu");
	set.add("Patel");
	set.add("Vasu");
	set.add("Algonquin");
	set.add("Canada");
	set.add("India");
		
	System.out.println(set);
	
	if(set.contains("Vasu")) {
		System.out.println("It is in set.");
	}
		
	for(String name : set) {
		System.out.println(name);
	}
	
	set.remove("Algonquin");
	System.out.println(set);
	
	}
	
}
