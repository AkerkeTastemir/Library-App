package app.controllers.interfaces;

import app.models.Book;

import java.util.List;

public interface IBookController {
    List<Book> getAllBooks();
    Book getBookByIsbn(String isbn);
    void updateBook(Book book);
    void deleteBook(String isbn);
    void addBook(Book book);
}

