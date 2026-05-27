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
    private Member member;
    private BorrowingTransaction transaction;

    public void addBook(Book book) {
        this.book = book;
        System.out.println("Book added successfully.");
    }

    public void registerMember(Member member) {
        this.member = member;
        System.out.println("Member added successfully.");
    }

    public void borrowBook(BorrowingTransaction transaction) {
        if (book != null) {
            if (book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                this.transaction = transaction;
                System.out.println("Borrow book successfully.");
            } else {
                System.out.println("Book out of stock.");
            }
        } else {
            System.out.println("No book found.");
        }
    }

    public void returnBook() {
        if (book != null) {
            book.setQuantity(book.getQuantity() + 1);
            System.out.println("Return successfully.");
        } else {
            System.out.println("No book found.");
        }
    }

    public void generateReports() {
        System.out.println("===== REPORT =====");
        if (transaction != null) {
            transaction.displayTransaction();
        } else {
            System.out.println("No transaction.");
        }
    }
}
