package manage_libarary_system;

public class Manage_Libarary_System {
    private String bookId;
    private String title;
    private int quantity;
    private String borrowerName;
    private boolean returned;

    public void addBook(String bookId, String title, int quantity) throws Exception {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new Exception("Book ID cannot be empty!");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new Exception("Book title cannot be empty!");
        }
        if (quantity < 0) {
            throw new Exception("Quantity cannot be negative!");
        }
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        System.out.println("Add book successfully!");
    }

    public void borrowBook(String borrowerName) throws Exception {
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            throw new Exception("Borrower name cannot be empty!");
        }
        if (bookId == null) {
            throw new Exception("No book in library!");
        }
        if (quantity <= 0) {
            throw new Exception("Book out of stock!");
        }
        quantity--;
        this.borrowerName = borrowerName;
        this.returned = false;
        System.out.println("Borrow book successfully!");
    }

    public void returnBook() throws Exception {
        if (borrowerName == null) {
            throw new Exception("No borrowing transaction found!");
        }
        if (returned) {
            throw new Exception("Book already returned!");
        }
        returned = true;
        quantity++;
        System.out.println("Return book successfully!");
    }

    public void displayLibrary() {
        System.out.println("===== LIBRARY INFORMATION =====");
        if (bookId != null) {
            System.out.println("Book ID: " + bookId);
            System.out.println("Title: " + title);
            System.out.println("Quantity: " + quantity);
        } else {
            System.out.println("No book available!");
        }
        System.out.println("------------------------------");
        if (borrowerName != null) {
            System.out.println("Borrower: " + borrowerName);
            System.out.println("Returned: " + returned);
        } else {
            System.out.println("No transaction available!");
        }
    }
}
