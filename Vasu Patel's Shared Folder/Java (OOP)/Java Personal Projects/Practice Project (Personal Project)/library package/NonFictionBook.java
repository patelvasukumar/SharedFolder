package library;
public class NonFictionBook extends Book {
    private String subject;

    public NonFictionBook() {}

    public NonFictionBook(String title, String author, int isbn, String subject) {
        super(title, author, isbn);
        this.subject = subject;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    @Override
    public void readDetails() {
     
        super.readDetails();
        System.out.print("Enter the subject of the non-fiction book: ");
        subject = scanner.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nSubject: %s", subject);
    }
}