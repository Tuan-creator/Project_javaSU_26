/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage_libarary_system;

/**
 *
 * @author TRINH HUNG TUAN
 */
public class Manage_Libarary_System {
    private Book book;
    private BorrowingTransaction transaction;

    public void addBook(Book book) throws Exception {
        if (book == null) {
            throw new Exception("Book cannot be null!");
        }
        if (book.getBookId() == null || book.getBookId().trim().isEmpty()) {
            throw new Exception("Book ID cannot be empty!");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new Exception("Book title cannot be empty!");
        }
        if (book.getQuantity() < 0) {
            throw new Exception("Quantity cannot be negative!");
        }
        this.book = book;
        System.out.println("Add book successfully!");
    }

    public void borrowBook(BorrowingTransaction transaction)throws Exception {
        if (transaction == null) {
            throw new Exception("Transaction cannot be null!");
        }
        if (book == null) {
            throw new Exception("No book in library!");
        }
        if (book.getQuantity() <= 0) {
            throw new Exception("Book out of stock!");
        }
        book.setQuantity(book.getQuantity() - 1);
        this.transaction = transaction;
        System.out.println("Borrow book successfully!");
    }

    public void returnBook() throws Exception {
        if (transaction == null) {
            throw new Exception("No borrowing transaction found!");
        }
        if (transaction.isReturned()) {
            throw new Exception("Book already returned!");
        }
        transaction.setReturned(true);
        book.setQuantity(book.getQuantity() + 1);
        System.out.println("Return book successfully!");
    }

    public void displayLibrary() {
        System.out.println("===== LIBRARY INFORMATION =====");
        if (book != null) {
            book.displayBook();
        } else {
            System.out.println("No book available!");
        }
        System.out.println("------------------------------");
        if (transaction != null) {
            transaction.displayTransaction();
        } else {
            System.out.println("No transaction available!");
        }
    }
}
