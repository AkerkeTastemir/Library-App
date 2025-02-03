package app.repositories.interfaces;

import app.models.Book;

import java.util.List;

public interface IBookRepository {
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(String isbn);
    Book getBook(String isbn);
    List<Book> getAllBooks();
}
