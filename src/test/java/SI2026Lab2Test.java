import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest() {
        Library lib = new Library();

        assertThrows(IllegalArgumentException.class, () ->
                lib.searchBookByTitle("")
        );

        lib.addBook(new Book("Clean Code", "Robert Martin", "IT"));
        assertNotNull(lib.searchBookByTitle("Clean Code"));

        assertNull(lib.searchBookByTitle("NonExistent"));
    }

    @Test
    void borrowBookEveryBranchTest() {
        Library lib = new Library();
        Book book = new Book("The Hobbit", "Tolkien", "Fantasy");
        lib.addBook(book);

        assertThrows(IllegalArgumentException.class, () ->
                lib.borrowBook("", "Tolkien")
        );

        assertThrows(IllegalArgumentException.class, () ->
                lib.borrowBook("The Hobbit", "")
        );

        assertThrows(RuntimeException.class, () ->
                lib.borrowBook("Unknown", "Author")
        );

        lib.borrowBook("The Hobbit", "Tolkien");
        assertTrue(book.isBorrowed());

        assertThrows(RuntimeException.class, () ->
                lib.borrowBook("The Hobbit", "Tolkien")
        );
    }

    @Test
    void searchBookMultipleConditionTest() {
        Library lib = new Library();

        Book b1 = new Book("Clean Code", "Author", "IT");
        Book b2 = new Book("Clean Code", "Author", "IT");
        b2.setBorrowed(true);

        lib.addBook(b1);
        lib.addBook(b2);

        assertNotNull(lib.searchBookByTitle("Clean Code"));

        b1.setBorrowed(true);
        assertNull(lib.searchBookByTitle("Clean Code"));

        assertNull(lib.searchBookByTitle("Unknown"));

        assertNull(lib.searchBookByTitle("Another"));
    }

    @Test
    void borrowBookMultipleConditionTest() {
        Library lib = new Library();
        lib.addBook(new Book("Test", "Author", "Genre"));

        assertThrows(IllegalArgumentException.class, () ->
                lib.borrowBook("", "")
        );

        assertThrows(IllegalArgumentException.class, () ->
                lib.borrowBook("", "Author")
        );

        assertThrows(IllegalArgumentException.class, () ->
                lib.borrowBook("Test", "")
        );

        lib.borrowBook("Test", "Author");
    }
}