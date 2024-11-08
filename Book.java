/**
 * Abstract class representing a book in the library.
 */
abstract class Book {
    protected String title;
    protected String author;
    protected String ISBN;
    protected boolean isCheckedOut;

    /**
     * Constructs a new Book with the specified title, author, and ISBN.
     *
     * @param title  The title of the book.
     * @param author The author of the book.
     * @param ISBN   The ISBN of the book.
     */
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isCheckedOut = false;
    }

    /**
     * Marks the book as checked out.
     */
    public void checkOut() {
        isCheckedOut = true;
    }

    /**
     * Marks the book as returned.
     */
    public void returnBook() {
        isCheckedOut = false;
    }

    /**
     * Provides a description of the book.
     * This method must be implemented by subclasses.
     *
     * @return A string describing the type of book.
     */
    public abstract String describe();
}
