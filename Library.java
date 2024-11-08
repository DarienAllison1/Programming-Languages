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
     * Adds a new book to the library's collection if it is not already present.
     *
     * @param book The book to add.
     */
    public void addBook(Book book) {
        if (!books.contains(book)) { // Check for duplicate
            books.add(book);
        } else {
            System.out.println("Book already exists: " + book.describe());
        }
    }

    /**
     * Registers a new member in the library if they are not already registered.
     *
     * @param member The member to register.
     */
    public void registerMember(Member member) {
        if (!members.contains(member)) { // Check for duplicate
            members.add(member);
        } else {
            System.out.println("Member already exists: " + member.describe());
        }
    }

    /**
     * Adds a new staff member to the library if they are not already added.
     *
     * @param staffMember The staff member to add.
     */
    public void addStaff(Staff staffMember) {
        if (!staff.contains(staffMember)) { // Check for duplicate
            staff.add(staffMember);
        } else {
            System.out.println("Staff member already exists: " + staffMember.describe());
        }
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
