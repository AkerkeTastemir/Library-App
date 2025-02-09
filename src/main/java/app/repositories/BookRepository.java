package app.repositories;

import app.data.interfaces.IDB;
import app.models.Book;
import app.repositories.interfaces.IBookRepository;

import java.sql.*;
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
                    "isAvailable BOOLEAN, " +
                    "borrowDate DATE, " +
                    "returnDate DATE);";
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
                    "isbn, title, author, category, isAvailable, borrowDate, returnDate" +
                    ") " +
                    "VALUES (" +
                    "?, ?, ?, ?, ?, ?, ?" +
                    ");";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getCategory());
            statement.setBoolean(5, book.isAvailable());
            statement.setDate(6, Date.valueOf(book.getBorrowDate()));
            statement.setDate(7, Date.valueOf(book.getReturnDate()));

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book) {
        try {

            String sql = "UPDATE books SET title = ?, author = ?, category = ?, isAvailable = ?, borrowDate = ?, returnDate = ? WHERE isbn = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setBoolean(4, book.isAvailable());
            statement.setString(5, book.getIsbn());
            statement.setDate(6, Date.valueOf(book.getBorrowDate()));
            statement.setDate(7, Date.valueOf(book.getReturnDate()));

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
                    resultSet.getDate("borrowDate").toLocalDate(),
                    resultSet.getDate("returnDate").toLocalDate()
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
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
