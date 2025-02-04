package app.controllers.interfaces;

public interface IBookController {
    void manageBooks();

    String getAllBooks();
    String getBookByIsbn(String isbn);
    String createBook(String isbn, String author, String title, String category, boolean isAvailable);
    String updateBook(String isbn, String author, String title, String category, boolean isAvailable);
    String deleteBook(String isbn);
    String addBook(String title, String author);
    String borrowBook(int bookId);
    String returnBook(int bookId);
    String deleteBook(int bookId);
}

