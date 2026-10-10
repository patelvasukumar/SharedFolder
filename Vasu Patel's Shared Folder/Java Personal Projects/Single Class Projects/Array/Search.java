package Array;
import java.util.Scanner;

public class Search {

	Scanner key =  new Scanner(System.in);
	
	int[] numbers = new int[5];
	int findValue = 5;
	int pos = 0;
	boolean found = false;
	
	public void performSearch() {
		
		while (pos < numbers.length && !found) {
			
			if (numbers[pos] == findValue) {
				found = true;
			} else {
				pos++;
			}
		}
		
		if (found) { System.out.println("Found at position: " + pos);}
		else { System.out.println("Not Found");}
	}
	
	public static void main(String[] args) {
		Search search = new Search();
		
		search.performSearch();
	}
	
}
