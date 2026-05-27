/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package borrowing;

/**
 *
 * @author TRINH HUNG TUAN
 */
import java.time.LocalDate;
public class Borrowing {
     private String transactionId;
    private String memberId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int daysOverdue;

    public BorrowingTransaction(String transactionId, String memberId, String bookId, LocalDate borrowDate) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = null;
        this.daysOverdue = 0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Days Overdue: " + daysOverdue);
    }
}
