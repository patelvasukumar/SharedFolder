package week2_practice;
import java.util.Scanner;

public class three {

	private int[] collection;
	
	public int[] getCollection() {
		return collection;
	}
	
	public void setCollection(int[] newCollection) {
		this.collection = newCollection;
	}
			
	 
    public void generateFibonacci(int number) {
        collection = new int[number]; 

        if (number > 0) {
            collection[0] = 0; 
        }
        
        if (number > 1) {
            collection[1] = 1; 
        }
        
        for (int i = 2; i < number; i++) {
            collection[i] = collection[i - 1] + collection[i - 2]; 
        }
    }
	
	public void printing(int number) {
		
		for(int x = 0; x < number; x++) {
		System.out.print(collection[x] + " ");
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner key = new Scanner(System.in);
		
		System.out.println("Write the position number at which you want to find the sequence: ");
		int number = key.nextInt();
		
		three myObj = new three();
		
		myObj.generateFibonacci(number);
		myObj.printing(number);
		
		key.close();
	}
	
}
