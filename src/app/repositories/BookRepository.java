package app.repositories;

import app.data.interfaces.IDB;
import app.models.Book;
import app.repositories.interfaces.IBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB db;

    public BookRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addBook(Book book) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO books(isbn, title, author, category, isAvailable) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getCategory());
            stmt.setBoolean(5, book.isAvailable());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBook(Book book) {
        // update
        return false;
    }

    @Override
    public boolean deleteBook(String isbn) {
        // delete
        return false;
    }

    @Override
    public Book getBook(String isbn) {
        // get single book
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("isAvailable")
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
