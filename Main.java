/**
 * Main class to test the library management system.
 */
public class Main {
    public static void main(String[] args) {
        // Create library
        Library library = new Library();

        // Test 1: Adding and listing staff and members
        System.out.println("Test 1: Adding and listing staff and members");
        Staff staff1 = new Staff("Tasha", "S001");
        Member member1 = new Member("Jr", "M001");
        Member member2 = new Member("Bug", "M002");

        library.addStaff(staff1);
        staff1.registerMember(library, member1);
        staff1.registerMember(library, member2);

        library.listStaff();    // Expect to see Tasha
        library.listMembers();  // Expect to see Jr and Bug

        // Test 2: Adding and listing books
        System.out.println("\nTest 2: Adding and listing books");
        Book fictionBook = new Fiction("The Color Purple", "Alice Walker", "123456789");
        Book nonFictionBook = new NonFiction("The Souls of Black Folk", "W.E.B. Du Bois", "987654321");

        staff1.registerBook(library, fictionBook);
        staff1.registerBook(library, nonFictionBook);

        library.listBooks();    // Expect to see "The Color Purple" and "The Souls of Black Folk"

        // Test 3: Check out and return book
        System.out.println("\nTest 3: Check out and return book");
        fictionBook.checkOut();
        System.out.println("Fiction book checked out: " + fictionBook.describe());

        // Attempt to check out the already checked-out book
        fictionBook.checkOut(); // Should handle re-checkout gracefully
        fictionBook.returnBook();

        // Test 4: Attempt to add duplicate books and members
        System.out.println("\nTest 4: Attempt to add duplicate books and members");
        staff1.registerBook(library, fictionBook);  // Should not duplicate
        staff1.registerMember(library, member1);    // Should not duplicate

        library.listBooks();   // Expect two books only
        library.listMembers(); // Expect two members only

        // Test 5: Check out all books, then list
        System.out.println("\nTest 5: Check out all books, then list");
        fictionBook.checkOut();
        nonFictionBook.checkOut();
        library.listBooks();   // Expect to see both books marked as checked out

        // Test 6: Return all books, then list
        System.out.println("\nTest 6: Return all books, then list");
        fictionBook.returnBook();
        nonFictionBook.returnBook();
        library.listBooks();   // Expect to see both books marked as available

        // Test 7: Attempt listing with no members or books (fresh library)
        System.out.println("\nTest 7: Attempt listing with no members or books");
        Library emptyLibrary = new Library();
        emptyLibrary.listBooks();    // Expect no books
        emptyLibrary.listMembers();  // Expect no members
        emptyLibrary.listStaff();    // Expect no staff

        // Test 8: Adding multiple staff members and listing
        System.out.println("\nTest 8: Adding multiple staff members and listing");
        Staff staff2 = new Staff("David", "S002");
        emptyLibrary.addStaff(staff1);
        emptyLibrary.addStaff(staff2);
        emptyLibrary.listStaff();    // Expect to see both Tasha and David

        System.out.println("\nAll tests completed.");
    }
}


 
