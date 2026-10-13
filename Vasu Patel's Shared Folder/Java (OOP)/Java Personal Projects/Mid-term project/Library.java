package week6_midterm;

import java.util.Scanner;

public class Library {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Title: ");
		String title = scanner.nextLine();
		System.out.println("Publisher: ");
		String publisher = scanner.nextLine();
		System.out.println("Year published : ");
		int published = Integer.parseInt(scanner.nextLine());
		System.out.println("Author: ");
		String author = scanner.nextLine();
		System.out.println("Issue Number: ");
		int issueNumber = Integer.parseInt(scanner.nextLine());
		System.out.println("Duration: ");
		int duration = Integer.parseInt(scanner.nextLine());
		
		LibraryItem obj = new LibraryItem(title,publisher,published);
		
		obj = new Book(title,publisher,published,author);
		obj.displayDetails();
		
		obj = new Magazine(title,publisher,published,issueNumber);
		obj.displayDetails();
		
		obj = new DVD(title,publisher,published,duration);
		obj.displayDetails();
		
	}
	
	
}
