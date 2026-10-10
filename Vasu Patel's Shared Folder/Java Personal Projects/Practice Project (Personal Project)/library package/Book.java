package library;

import java.util.Scanner;

public abstract class Book implements Readable {
    protected String title;
    protected String author;
    protected int isbn; 
    protected Scanner scanner = new Scanner(System.in);

    public Book() {}

    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getIsbn() { return isbn; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(int isbn) { this.isbn = isbn; }

    @Override
    public void readDetails() {
        System.out.print("Enter the title for the book: ");
        title = scanner.nextLine();
        System.out.print("Enter the author name for the book: ");
        author = scanner.nextLine();
        System.out.print("Enter the ISBN number for the book: ");
        isbn = Integer.parseInt(scanner.nextLine());   
    }

    @Override
    public String toString() {
        return String.format("Title: %s\nAuthor: %s\nISBN: %d", title, author, isbn);
    }
}