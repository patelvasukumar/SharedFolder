package Array;
import java.util.Scanner;


public class searchUp {

	Scanner key = new Scanner(System.in);
	
	int[] numbers;
	boolean found = true;
	int find;
	
	public void aaraySize() {
		
		int size;
		
		System.out.println("Write the size of the Aaray: ");
		size = key.nextInt();
			   key.nextLine();
		
		numbers = new int[size];
		
		int i = 0;
		while(i < size) {
		System.out.print("Write the elements: ");
		
		numbers[i] = key.nextInt();
					 key.nextLine();
		
			i++;
		}
		
	}
	
	
	public void whichElement() {
		
		System.out.println("Which element do you want to find?");
		find = key.nextInt();
				key.nextLine();
		
	}
	
	public void searchEngine() {
		
		int counter = 0;
		while(counter < numbers.length && found) {
			
				if(numbers[counter] == find ) {
					found = false;
				} else {
					counter++;
				}	
		}
		
		if(!found) { System.out.println("Found at position: " + counter); } 
		else { System.out.println("the element does not exist in the aaray."); }
		
	}
	
	public void addElement() {
		 System.out.println("Enter the element to add: ");
	       int newElement = key.nextInt();
	        				key.nextLine();

	        int[] newArray = new int[numbers.length + 1];
	        System.arraycopy(numbers, 0, newArray, 0, numbers.length);
	        newArray[numbers.length] = newElement;

	        numbers = newArray;
	        System.out.println("Element added successfully.");
	}
	
	 public void removeElement() {
	        System.out.println("Enter the element to remove: ");
	        int removeElement = key.nextInt();
	        key.nextLine();

	        boolean isRemoved = false;
	        int[] newArray = new int[numbers.length - 1];
	        int j = 0;

	        for (int number : numbers) {
	            if (number != removeElement || isRemoved) {
	                if (j < newArray.length) {
	                    newArray[j] = number;
	                    j++;
	                }
	            } else {
	                isRemoved = true;
	            }
	        }

	        if (isRemoved) {
	            numbers = newArray;
	            System.out.println("Element removed successfully.");
	        } else {
	            System.out.println("Element not found in the array.");
	        }
	    }

	    public void sweepElements() {
	        System.out.println("Current elements in the array:");
	        for (int number : numbers) {
	            System.out.print(number + " ");
	        }
	        System.out.println();
	    }
	
	public static void main(String[] args) {
		
		searchUp myObj = new searchUp();
		boolean exit = false;
		
		myObj.aaraySize();
		
		while(!exit) {
		
		System.out.println("The menu options:");
		System.out.println("First: Find the element.");
		System.out.println("Second: Add the element.");
		System.out.println("Third: Remove the element.");
		System.out.println("Fourth: Display the element.");
		System.out.println("Fifth: Exit.");
		
		System.out.println("Select your choice:");
		int choice = myObj.key.nextInt();
					myObj.key.nextLine();
		
		switch(choice) {
		
		case 1: 
				myObj.whichElement();
				myObj.searchEngine();
				break;
				
		case 2: 
				myObj.addElement();
				break;
				
		case 3:
				myObj.removeElement();
				break;
				
		case 4:
				myObj.sweepElements();
				break;
				
		case 5:
				exit = true;
				System.out.println("Exiting program.");
				break;
				
		default:
				System.out.println("Invalid Choice. Please Try Again");
		}
		
		}
	}
	
	
}
