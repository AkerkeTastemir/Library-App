package app.models;

import java.time.LocalDate;
import java.util.Map;

public class Fines {

    private static final double DAILY_FINE_RATE = 1000; // fine cost

    public static double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)) {
            return 0.0;
        }
        long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate);
        return overdueDays * DAILY_FINE_RATE;
    }

    public static void calculateAndPrintFines(User user) {
        LocalDate today = LocalDate.now();
        for (Map.Entry<String, Book> entry : user.getBorrowedBooks().entrySet()) {
            Book book = entry.getValue();
            LocalDate returnDate = entry.getValue().getReturnDate();
            if (returnDate.isBefore(today)) {
                double fine = calculateFine(returnDate, today);
                System.out.println("Книга: " + book.getTitle() + ", Штраф: " + fine + " тенге");
            }
        }
    }
}
