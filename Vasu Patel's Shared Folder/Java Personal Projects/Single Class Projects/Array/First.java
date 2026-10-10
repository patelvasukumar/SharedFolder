package Array;
import java.util.Scanner;

public class First {

	//Class
	Scanner key = new Scanner(System.in);
	public int number;
	public String displayWord;
	public String[] nameAr;
	public String[] stringAr;
	
	// Cons
	First(){}
	
	//Methods
	
	//1
	public int personCount() {
		number = key.nextInt();
				 key.nextLine();
		return number;
	}
	
	//2
	public String displayWord() {
		String[] counts = {"zero","first","second","third","fourth","five","six","seven","eight","nine","ten"};
		
		
		return "";
	}
	

	
	//main
	public static void main(String[] args) {
		First myObj = new First();
		
		System.out.print("How many person name do you want to print?");
		myObj.personCount();
		
		System.out.println("Write person name for the " + myObj.number + ":");
		myObj.displayWord();
		
		if(myObj.number>=0 && myObj.number <= 10) {
		
		}
		
		int i = 1;
		int j = 0;
		while(i == myObj.number) {
		
		String innerName = myObj.key.nextLine();
		myObj.nameAr[j] = innerName;
		
		j++;
		i++;
		}
		
		}

}
