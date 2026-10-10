package inheritance;

class Books {
    protected String title;
    protected String author;
    protected String ISBN;

    Books(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
    }
}

class EBook extends Books {
    private double fileSize;

    public EBook(String title, String author, String ISBN, double fileSize) {
        super(title, author, ISBN);
        this.fileSize = fileSize;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("File Size: " + fileSize + " MB");
    }
}

class PrintedBook extends Books {
    private double weight;

    public PrintedBook(String title, String author, String ISBN, double weight) {
        super(title, author, ISBN);
        this.weight = weight;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Weight: " + weight + " grams");
    }
}

public class Book {
    public static void main(String[] args) {
        Books ebook1 = new EBook("Digital Fortress", "Dan Brown", "123-4567891234", 2.5);
        Books printedBook1 = new PrintedBook("Inferno", "Dan Brown", "987-6543219876", 1200.0);

        Books[] books = {ebook1, printedBook1};

        for (Books book : books) {
            book.displayDetails();
            System.out.println();
        }
        
        System.out.println(printedBook1 instanceof Books );
        
    }
}
