import java.util.ArrayList;
import java.util.List;

class Book {
    String title;
    String author;
    String genre;
    boolean isBorrowed;

    Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isBorrowed = false;
    }
}

class Library {
    List<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    boolean searchByTitle(String title) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                return true;
            }
        }
        return false;
    }

    void listByGenre(String genre) {
        // TODO: Implement
    }

    void borrowBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title) && !book.isBorrowed) {
                book.isBorrowed = true;
                System.out.println("Borrowed successfully");
                return;
            }
        }
    }

    void returnBook(String title) {
        // TODO: Implement
    }

    int countAvailable() {
        // TODO: Implement
        return 0;
    }

    void printBorrowed() {
        // TODO: Implement
    }
}

public class SI2026Lab1Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
        library.addBook(new Book("Dune", "Frank Herbert", "Sci-Fi"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));

        // Test search
        System.out.println(library.searchByTitle("Dune"));
        System.out.println(library.searchByTitle("Unknown Book"));

        // Test borrow
        library.borrowBook("1984");
    }
}
