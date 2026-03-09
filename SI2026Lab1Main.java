import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String genre;
    private boolean borrowed;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.borrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Borrowed: " + borrowed;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    // TODO: Implement in branch feature-search-books
    public boolean searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    // TODO: Implement in branch feature-borrow-book
    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isBorrowed()) {
                    book.setBorrowed(true);
                    System.out.println("Borrowed successfully");
                } else {
                    System.out.println("Book is already borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // TODO: Optional additional method
    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isBorrowed()) {
                    book.setBorrowed(false);
                    System.out.println("Book returned.");
                } else {
                    System.out.println("Book was not borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // TODO: Implement in branch feature-genre-report
    public void printBooksByGenre(String genre) {
    }

    // TODO: Optional additional method
    public int countAvailableBooks() {
        int count = 0;
        for (Book book : books) {
            if (!book.isBorrowed()) {
                count++;
            }
        }
        return count;
    }

    // TODO: Optional additional method
    public void printBorrowedBooks() {
        for (Book book : books) {
            if (book.isBorrowed()) {
                System.out.println(book);
            }
        }
    }
}

public class SI2026Lab1Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));

        System.out.println("Library initialized.");

        System.out.println("Search Clean Code: " + library.searchBookByTitle("Clean Code"));
        System.out.println("Search Harry Potter: " + library.searchBookByTitle("Harry Potter"));
        library.borrowBook("The Hobbit");
    }
}