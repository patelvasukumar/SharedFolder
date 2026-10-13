package library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> bookCollection;

    public Library() {
        this.bookCollection = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookCollection.add(book);
    }

    public List<Book> getAllBooks() {
        return bookCollection;
    }

    public Book searchByTitle(String title) {
        for (Book book : bookCollection) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}