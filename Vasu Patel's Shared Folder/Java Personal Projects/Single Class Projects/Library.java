import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Parent class
class LibraryItem {
    protected String title;
    protected String publisher;
    protected int yearPublished;

    public LibraryItem(String title, String publisher, int yearPublished) {
        this.title = title;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }

    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Publisher: " + publisher);
        System.out.println("Year Published: " + yearPublished);
    }
}

// Book subclass
class Book extends LibraryItem {
    private String author;

    public Book(String title, String publisher, int yearPublished, String author) {
        super(title, publisher, yearPublished);
        this.author = author;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Author: " + author);
    }
}

// Magazine subclass
class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String title, String publisher, int yearPublished, int issueNumber) {
        super(title, publisher, yearPublished);
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Issue Number: " + issueNumber);
    }
}

// DVD subclass
class DVD extends LibraryItem {
    private int duration; // in minutes

    public DVD(String title, String publisher, int yearPublished, int duration) {
        super(title, publisher, yearPublished);
        this.duration = duration;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Duration: " + duration + " minutes");
    }
}

// Library class to manage items
public class Library {
    private List<LibraryItem> items = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void displayAllItems() {
        System.out.println("\n📚 Library Items:");
        System.out.println("------------------------");
        for (LibraryItem item : items) {
            item.displayDetails();
            System.out.println();
        }
    }

    public void userInputAndAddItems() {
        // Adding a book
        System.out.println("\nEnter details for a Book:");
        addItem(new Book(input("Title"), input("Publisher"), inputInt("Year Published"), input("Author")));

        // Adding a magazine
        System.out.println("\nEnter details for a Magazine:");
        addItem(new Magazine(input("Title"), input("Publisher"), inputInt("Year Published"), inputInt("Issue Number")));

        // Adding a DVD
        System.out.println("\nEnter details for a DVD:");
        addItem(new DVD(input("Title"), input("Publisher"), inputInt("Year Published"), inputInt("Duration (minutes)")));
    }

    // Helper methods for user input
    private String input(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    private int inputInt(String prompt) {
        System.out.print(prompt + ": ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.userInputAndAddItems();
        library.displayAllItems();
    }
}
