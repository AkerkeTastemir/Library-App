package app.menu;

import app.MyApplication;
import app.controllers.BookController;
import app.menu.interfaces.IMenu;
import app.models.Book;
import app.models.Fines;
import app.models.User;

import java.time.LocalDate;
import java.util.Scanner;

public class UserMenu implements IMenu {

    private final MyApplication app = MyApplication.getInstance();
    private final Scanner scanner;

    public UserMenu() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void onEnter() {
        start();
    }

    public void start() {

        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Retrieve book details");
            System.out.println("3. Delete a book");
            System.out.println("4. View penalty");
            System.out.println("5. Exit");
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
                    viewPenalty();
                    break;
                case 5:
                    System.out.println("Exiting application...");
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
        app.getBookController().addBook(book);

        app.getUser().borrowBook(book);
        app.getUserController().updateUser(app.getUser());

        System.out.println("Book added successfully.");
    }

    private void retrieveBookDetails() {
        System.out.print("Enter ISBN of the book to retrieve: ");
        String isbn = scanner.nextLine();
        Book book = app.getBookController().getBookByIsbn(isbn);

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
        app.getBookController().deleteBook(isbn);
        MyApplication.getInstance().getUser().returnBook(isbn);
        System.out.println("Book deleted successfully.");
    }

    private void viewPenalty() {
        System.out.print("Enter user ID to view fines: ");
        User user = MyApplication.getInstance().getUser();

        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        Fines.calculateAndPrintFines(user);
    }

}

