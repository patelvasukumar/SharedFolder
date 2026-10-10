package week6_midterm;

class Book extends LibraryItem{
	
	String author;
	
	public Book(String title,String publisher,int yearpublished, String author) {
		
		super(title,publisher,yearpublished);
		this.author=author;
		
	}
	
	@Override 
	public void displayDetails() {
		super.displayDetails();
		System.out.println("author: " + author);
	}
	
}

class Magazine extends LibraryItem {
	
	int issueNumber;
	
	public Magazine(String title, String publisher, int yearpublished, int issueNumber) {
		super(title,publisher,yearpublished);
		this.issueNumber=issueNumber;
	}
	
	@Override
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Issue Number: " + issueNumber);
	}
	
}

class DVD extends LibraryItem{
	
	int duration;
	
	public DVD(String title, String publisher, int yearpublished, int duration) {
		
		super(title,publisher,yearpublished);
		this.duration = duration;
		
	}
	
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Duration: " + duration);
	}
	
}

public class LibraryItem {

	String title;
	String publisher;
	int yearpublished;
	
	public LibraryItem(String title, String publisher, int yearpublished) {
		this.title = title;
		this.publisher = publisher;
		this.yearpublished = yearpublished;
	}
	
	public void displayDetails() {
		
		System.out.println("Title: " + title + "\nPublisher: " + publisher + "\nYear: " + yearpublished);
		
	}
	
}


