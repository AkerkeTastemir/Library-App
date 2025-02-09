package app.menu;

import app.MyApplication;
import app.controllers.BookController;
import app.controllers.UserController;
import app.controllers.interfaces.IBookController;
import app.controllers.interfaces.IUserController;
import app.menu.interfaces.IMenu;
import app.models.Book;
import app.models.User;


import java.time.LocalDate;
import java.util.Scanner;

public class AdminMenu implements IMenu {

    private final Scanner scanner;
    private final IBookController bookController;
    private final IUserController userController;

    public AdminMenu() {
        this.scanner = new Scanner(System.in);
        MyApplication app = MyApplication.getInstance();
        this.bookController = app.getBookController();
        this.userController = app.getUserController();
    }

    @Override
    public void onEnter() {
        start();
    }

    public void start() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Retrieve book details");
            System.out.println("3. Delete a book");
            System.out.println("4. Add a new user");
            System.out.println("5. Delete a user");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    retrieveBookDetails();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    addNewUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    System.out.println("Exiting Admin Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addNewBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter return days: ");
        int days = Integer.parseInt(scanner.nextLine());

        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(days);

        Book book = new Book(isbn, title, author, category, borrowDate, returnDate);
        bookController.addBook(book);

        System.out.println("Book added successfully.");
    }

    private void retrieveBookDetails() {
        System.out.print("Enter ISBN of the book to retrieve: ");
        String isbn = scanner.nextLine();
        Book book = bookController.getBookByIsbn(isbn);
        if (book != null) {
            System.out.println("Book Details:");
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }

    private void deleteBook() {
        System.out.print("Enter ISBN of the book to delete: ");
        String isbn = scanner.nextLine();
        bookController.deleteBook(isbn);
        System.out.println("Book deleted successfully.");
    }

    private void addNewUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();

        User user = new User(name, email);
        userController.addUser(user);
        System.out.println("User added successfully.");
    }

    private void deleteUser() {
        System.out.print("Enter user email to delete: ");
        String email = scanner.nextLine();
        userController.deleteUser(email);
        System.out.println("User deleted successfully.");
    }
}
