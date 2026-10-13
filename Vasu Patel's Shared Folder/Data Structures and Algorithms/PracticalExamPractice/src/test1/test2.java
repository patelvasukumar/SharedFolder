package test1;

import java.util.TreeSet;
import java.util.Set;

public class test2 {

	
	public static void main(String[] args) {
		
		Set<Integer> vasu = new TreeSet<Integer>();
		Set<Integer> vasuB = new TreeSet<Integer>();
		
		vasu.add(12);
		vasu.add(13);
		vasu.add(14);
		vasu.add(15);
		vasu.add(15);
		vasu.add(16);
		
		vasuB.add(12);
		vasuB.add(14);
		vasuB.add(16);
		
		vasu.retainAll(vasuB);
		
		System.out.println(vasu);
		
	}
	
	/*
	 * 
	 	@override
		public int compareTo(Movie o) {
		
		int result = o;
		
		if (this.indb < o.getImdb()) {
			result = -1;
		}
		else if (this.imdb > q,getImdb()) {
			result = 1;
		}
		else {
			return;
		}
		return result;
		}

	 */
	
	
}
