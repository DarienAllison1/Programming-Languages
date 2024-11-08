/**
 * Represents a library staff member.
 */
class Staff extends Person {

    /**
     * Constructs a new Staff member with the specified name and ID.
     *
     * @param name The name of the staff member.
     * @param id   The staff ID.
     */
    public Staff(String name, String id) {
        super(name, id);
    }

    /**
     * Describes the staff member.
     *
     * @return A string describing the staff member.
     */
    @Override
    public String describe() {
        return "Library Staff: " + name + " (ID: " + id + ")";
    }

    /**
     * Registers a new member in the library.
     *
     * @param library The library to register the member in.
     * @param member  The member to register.
     */
    public void registerMember(Library library, Member member) {
        library.registerMember(member);
    }

    /**
     * Registers a new book in the library.
     *
     * @param library The library to register the book in.
     * @param book    The book to register.
     */
    public void registerBook(Library library, Book book) {
        library.addBook(book);
    }
}
