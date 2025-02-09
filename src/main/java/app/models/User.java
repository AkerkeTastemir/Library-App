package app.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String id;

    private String name;
    private String email;
    private String password;

    private final HashMap<String, Book> borrowedBooks;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.borrowedBooks = new HashMap<>();
    }

    public User(String id, String name, String email, String password, HashMap<String, Book> borrowedBooks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.borrowedBooks = borrowedBooks;
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

    public HashMap<String, Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.put(book.getIsbn(), book);
            book.setAvailable(false);
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is not available.");
        }
    }

    public void returnBook(String isbn) {
        if (borrowedBooks.containsKey(isbn)) {
            borrowedBooks.get(isbn).setAvailable(true);
            borrowedBooks.remove(isbn);
        }
    }

    public void checkOverdueBooks() {
        LocalDate today = LocalDate.now();
        for (Map.Entry<String, Book> entry : borrowedBooks.entrySet()) {
            if (entry.getValue().getReturnDate().isBefore(today)) {
                System.out.println("Book: " + entry.getValue().getTitle() + ", Return date: " + entry.getValue().getReturnDate());
            }
        }
    }

}
