package library;
public class FictionBook extends Book {
    private String genre;

    public FictionBook() {}

    public FictionBook(String title, String author, int isbn, String genre) {
        super(title, author, isbn);
        this.genre = genre;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public void readDetails() {
       
        super.readDetails();
        System.out.print("Enter the genre of the fiction book: ");
        genre = scanner.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nGenre: %s", genre);
    }
}