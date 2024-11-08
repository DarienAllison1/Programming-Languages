import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library with a collection of books, members, and staff.
 */
class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Staff> staff;

    /**
     * Constructs a new Library.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    /**
     * Adds a new book to the library's collection.
     *
     * @param book The book to add.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Registers a new member in the library.
     *
     * @param member The member to register.
     */
    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * Adds a new staff member to the library.
     *
     * @param staffMember The staff member to add.
     */
    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
    }

    /**
     * Lists all books in the library.
     */
    public void listBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book.describe());
        }
    }

    /**
     * Lists all members in the library.
     */
    public void listMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println(member.describe());
        }
    }

    /**
     * Lists all staff members in the library.
     */
    public void listStaff() {
        System.out.println("Library Staff:");
        for (Staff staffMember : staff) {
            System.out.println(staffMember.describe());
        }
    }
}
