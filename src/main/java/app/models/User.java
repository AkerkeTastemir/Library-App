package app.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String id;

    private String name;
    private String email;
    private String password;

    private final Map<Book, BookInfo> borrowedBooks;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.borrowedBooks = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Book, BookInfo> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book, LocalDate borrowDate, LocalDate returnDate) {
        if (book.isAvailable()) {
            borrowedBooks.put(book, new BookInfo(borrowDate, returnDate));
            book.setAvailable(false);
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.containsKey(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
        }
    }

    public void checkOverdueBooks() {
        LocalDate today = LocalDate.now();
        for (Map.Entry<Book, BookInfo> entry : borrowedBooks.entrySet()) {
            if (entry.getValue().getReturnDate().isBefore(today)) {
                System.out.println("Book: " + entry.getKey().getTitle() + ", Return date: " + entry.getValue().getReturnDate());
            }
        }
    }

}
