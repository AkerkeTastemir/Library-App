package app.repositories;

import app.data.interfaces.IDB;
import app.models.Book;
import app.models.User;
import app.repositories.interfaces.IBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB database;

    public BookRepository(IDB database) {
        this.database = database;

        init();
    }

    public void init() {
        try {

            String sql = "CREATE TABLE IF NOT EXISTS books (" +
                    "isbn VARCHAR(255) PRIMARY KEY, " +
                    "title VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "category VARCHAR(255), " +
                    "isAvailable BOOLEAN);";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addBook(Book book) {
        try {

            String sql = "INSERT INTO books (" +
                    "isbn, title, author, category, isAvailable" +
                    ") " +
                    "VALUES (" +
                    "?, ?, ?, ?, ?" +
                    ");";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getCategory());
            statement.setBoolean(5, book.isAvailable());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book) {
        try {

            String sql = "UPDATE books SET title = ?, author = ?, category = ?, isAvailable = ? WHERE isbn = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setBoolean(4, book.isAvailable());
            statement.setString(5, book.getIsbn());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String isbn) {
        try {

            String sql = "DELETE FROM books WHERE isbn = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, isbn);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBook(String isbn) {
        try {

            String sql = "SELECT * FROM books WHERE isbn = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, isbn);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new Book(
                    resultSet.getString("isbn"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("category"),
                    resultSet.getBoolean("isAvailable")
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
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
