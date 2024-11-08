/**
 * Represents a non-fiction book in the library.
 */
class NonFiction extends Book {

    /**
     * Constructs a new NonFiction book with the specified title, author, and ISBN.
     *
     * @param title  The title of the non-fiction book.
     * @param author The author of the non-fiction book.
     * @param ISBN   The ISBN of the non-fiction book.
     */
    public NonFiction(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    /**
     * Describes the book as a non-fiction type.
     *
     * @return A string indicating the book is a non-fiction book.
     */
    @Override
    public String describe() {
        return "Non-Fiction Book: " + title + " by " + author;
    }
}
