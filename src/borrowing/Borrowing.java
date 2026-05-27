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
    private boolean returned;

    public BorrowingTransaction(String transactionId, String memberId, String bookId) {
        if (transactionId == null || transactionId.trim().isEmpty()) { 
            throw new IllegalArgumentException("Transaction ID cannot be null or empty!");
        }

        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty!");
        }

        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty!");
        }
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.returned = false;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setTransactionId(String transactionId) {
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction ID cannot be null or empty!");
        }
        this.transactionId = transactionId;
    }

    public void setMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty!");
        }
        this.memberId = memberId;
    }

    public void setBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty!");
        }
        this.bookId = bookId;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Returned: " + returned);
    }
}
