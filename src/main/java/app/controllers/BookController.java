package app.controllers;

import app.controllers.interfaces.IBookController;
import app.models.Book;
import app.repositories.interfaces.IBookRepository;

import java.util.List;

public class BookController implements IBookController {
    private final IBookRepository bookRepository;

    public BookController(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;

        init();
    }

    public void init() {
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBook(isbn);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }

    @Override
    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

}