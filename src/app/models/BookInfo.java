package app.models;

import java.time.LocalDate;

public class BookInfo {
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BookInfo(LocalDate borrowDate, LocalDate returnDate) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
