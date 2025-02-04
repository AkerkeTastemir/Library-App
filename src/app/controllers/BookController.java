package app.controllers;

import app.controllers.interfaces.IBookController;
import app.models.Book;
import app.repositories.interfaces.IBookRepository;

public class BookController implements IBookController {
    private final IBookRepository bookRepository;

    public BookController(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void manageBooks() {
        // managing and interacting // example
        Book book = new Book( "123-45-67890-12-3", "Clear Code", "Uncle Bob", "programming", true);
        bookRepository.addBook(book);
        // add upd and del
    }

    @Override
    public String getAllBooks() {
        return "";
    }

    @Override
    public String getBookByIsbn(String isbn) {
        return "";
    }

    @Override
    public String createBook(String isbn, String author, String title, String category, boolean isAvailable) {
        return "";
    }

    @Override
    public String updateBook(String isbn, String author, String title, String category, boolean isAvailable) {
        return "";
    }

    @Override
    public String deleteBook(String isbn) {
        return "";
    }

    @Override
    public String addBook(String title, String author) {
        return "";
    }

    @Override
    public String borrowBook(int bookId) {
        return "";
    }

    @Override
    public String returnBook(int bookId) {
        return "";
    }

    @Override
    public String deleteBook(int bookId) {
        return "";
    }
} // book checker should add for users and update postgre with many books yes
