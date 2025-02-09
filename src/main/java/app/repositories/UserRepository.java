package app.repositories;

import app.data.interfaces.IDB;
import app.models.Book;
import app.repositories.interfaces.IBookRepository;
import app.repositories.interfaces.IUserRepository;
import app.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository implements IUserRepository {

    private IDB database;
    private IBookRepository bookRepository;

    public UserRepository(IDB database, IBookRepository bookRepository) {
        this.database = database;
        this.bookRepository = bookRepository;

        init();
    }

    public void init() {
        try {

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id VARCHAR(255) PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "borrowedBooks VARCHAR(255));";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        try {

            String sql = "INSERT INTO users (" +
                    "id, name, email, password, borrowedBooks" +
                    ") " +
                    "VALUES (" +
                    "?, ?, ?, ?, ?" +
                    ");";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            StringBuilder sb = new StringBuilder();
            for (Book book : user.getBorrowedBooks().values()) {
                sb.append(book.getIsbn()).append(";");
            }

            statement.setString(5, sb.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(String userId) {
        try {

            String sql = "SELECT * FROM users WHERE id = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String formatted = resultSet.getString("borrowedBooks");
                HashMap<String, Book> books = new HashMap<>();

                if (resultSet.getString("borrowedBooks") != null) {
                    for (String bookIsbn : formatted.split(";")) {
                        Book book = bookRepository.getBook(bookIsbn);
                        if (book == null) {
                            continue;
                        }
                        books.put(bookIsbn, book);
                    }
                }

                return new User(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        books
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {

            String sql = "SELECT * FROM users WHERE email = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String formatted = resultSet.getString("borrowedBooks");
                HashMap<String, Book> books = new HashMap<>();

                if (resultSet.getString("borrowedBooks") != null) {
                    for (String bookIsbn : formatted.split(";")) {
                        Book book = bookRepository.getBook(bookIsbn);
                        if (book == null) {
                            continue;
                        }
                        books.put(bookIsbn, book);
                    }
                }

                return new User(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        books
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {

            String sql = "SELECT * FROM users;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String formatted = resultSet.getString("borrowedBooks");
                HashMap<String, Book> books = new HashMap<>();

                if (resultSet.getString("borrowedBooks") != null) {
                    for (String bookIsbn : formatted.split(";")) {
                        Book book = bookRepository.getBook(bookIsbn);
                        if (book == null) {
                            continue;
                        }
                        books.put(bookIsbn, book);
                    }
                }

                users.add(new User(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        books
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    @Override
    public void updateUser(User user) {
        try {

            String sql = "UPDATE users SET name = ?, email = ?, password = ?, borrowedBooks = ? WHERE id = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            StringBuilder sb = new StringBuilder();
            for (Book book : user.getBorrowedBooks().values()) {
                sb.append(book.getIsbn()).append(";");
            }

            statement.setString(4, sb.toString());

            statement.setString(5, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String userId) {
        try {

            String sql = "DELETE FROM users WHERE id = ?;";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);

            statement.setString(1, userId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}