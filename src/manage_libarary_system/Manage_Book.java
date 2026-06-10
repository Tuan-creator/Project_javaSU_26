package manage_libarary_system;

import book.Book; 
import java.util.ArrayList;
import java.util.Scanner;

import util.InputHelper;
import util.ValidationBook;

public class Manage_Book {

    static Scanner sc = new Scanner(System.in);
    // Danh sách lưu trữ tất cả các cuốn sách trong thư viện
    static ArrayList<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    System.out.println("Exiting Book Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 1 to 6.");
            }
        } while (choice != 6);
    }

    public static void showMenu() {
        System.out.println("\n===== BOOK MANAGEMENT =====");
        System.out.println("1. Add New Book");
        System.out.println("2. Update Book Information / Add Stock Quantity");
        System.out.println("3. Delete / Decrease Book Quantity");
        System.out.println("4. View All Books");
        System.out.println("5. Search Book");
        System.out.println("6. Exit");
        System.out.print("Choose: ");
    }

    public static void showSearchMenu() {
        System.out.println("\n===== SEARCH BOOK =====");
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.println("4. Return to Book Menu");
        System.out.print("Choose search criteria: ");
    }

  // addbook
    public static void addBook() {
        System.out.println("\n--- Add New Book ---");
        try {
            String bookId;
            while (true) {
                bookId = InputHelper.BookId();
                if (findBookById(bookId) == null) {
                    break;
                }
                System.out.println("Error: Book ID already exists! Please try another ID.");
            }
            
            String title = InputHelper.Title();
            String author = InputHelper.Author();
            String genre = InputHelper.Genre();
            int pubYear = InputHelper.Year();
            int quantity = InputHelper.Quantity();

            Book newBook = new Book(bookId, title, author, genre, pubYear, quantity);
            bookList.add(newBook);
            System.out.println("Add Book Successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Action canceled.");
        }
    }

    // update
    public static void updateBook() {
        System.out.println("\n--- Update Book Information ---");
        System.out.print("Enter Book ID to update: ");
        String bookId = sc.nextLine().trim().toUpperCase();

        if (!ValidationBook.isValidBookId(bookId)) {
            System.out.println("Error: Invalid Book ID format!");
            return;
        }
        
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book ID not found!");
            return;
        }

        System.out.println("Current Info: " + book);
        System.out.println("Choose update mode:");
        System.out.println("1. Add more quantity to this book (Cong don so luong)");
        System.out.println("2. Update all profile fields (Sua toan bo thong tin)");
        System.out.print("Your choice: ");
        
        int mode;
        try {
            mode = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            mode = -1;
        }

        try {
            switch (mode) {
                case 1:
                    int additionalQty = InputHelper.Quantity();
                    book.setQuantity(book.getQuantity() + additionalQty);
                    System.out.println("Increased Quantity Successfully! New Info: " + book);
                    break;
                case 2:
                    book.setTitle(InputHelper.Title());
                    book.setAuthor(InputHelper.Author());
                    book.setGenre(InputHelper.Genre());
                    book.setPubYear(InputHelper.Year());
                    book.setQuantity(InputHelper.Quantity());
                    System.out.println("Update Book Profile Successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Update canceled.");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Update canceled.");
        }
    }

    // delete book 
    public static void deleteBook() {
        System.out.println("\n--- Delete / Decrease Book Quantity ---");
        System.out.print("Enter Book ID to delete: ");
        String bookId = sc.nextLine().trim();

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book ID not found!");
            return;
        }

        System.out.println("Current Info: " + book);
        int currentQty = book.getQuantity();
        int deleteQty = 0;

        while (true) {
            try {
                System.out.println("Enter quantity to delete (Max " + currentQty + "): ");
                deleteQty = InputHelper.Quantity();
                
                if (deleteQty <= currentQty) {
                    break; 
                }
                System.out.println("So luong can xoa khong duoc lon hon so luong hien co trong kho (" + currentQty + ")! Nhap lai.");
            } catch (IllegalArgumentException e) {
                System.out.println("Loi nhap lieu: " + e.getMessage() + " Vui long thuc hien lai.");
                return;
            }
        }

        if (deleteQty == currentQty) {
            bookList.remove(book);
            System.out.println("Book has been completely removed from the library!");
        } else {
            book.setQuantity(currentQty - deleteQty);
            System.out.println("Decreased Quantity Successfully! New Info: " + book);
        }
    }

    // 4. Xem danh sách sách
    public static void viewAllBooks() {
        System.out.println("\n--- All Books In Library ---");
        if (bookList.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        printTableHeader(); 
        for (Book b : bookList) {
            printTableRow(b.getBookId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getPubYear(), b.getQuantity()); 
        }
        System.out.println(SEPARATOR_LINE); 
        System.out.println("Total quantity of books in stock: " + getTotalQuantity());
        System.out.println(SEPARATOR_LINE);
    }

    // 5. Menu con Tìm kiếm Sách
    public static void searchBook() {
        if (bookList.isEmpty()) {
            System.out.println("\nLibrary has no books to search!");
            return;
        }

        int subChoice;
        do {
            showSearchMenu(); 
            try {
                subChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                subChoice = -1;
            }

            if (subChoice >= 1 && subChoice <= 3) {
                String prompt = (subChoice == 1) ? "Enter Book ID to search: " : (subChoice == 2) ? "Enter Title to search: " : "Enter Author to search: ";
                searchByKey(prompt, subChoice);
            } else if (subChoice == 4) {
                System.out.println("Returning to Book Management Menu...");
            } else {
                System.out.println("Invalid choice! Please choose from 1 to 4.");
            }
        } while (subChoice != 4);
    }

    // --- HÀM TÌM KIẾM ĐA NĂNG GỘP ---
    private static void searchByKey(String prompt, int type) {
        System.out.print(prompt);
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        printTableHeader();
        for (Book b : bookList) {
            String target = (type == 1) ? b.getBookId() : (type == 2) ? b.getTitle() : b.getAuthor();
            if (target != null && target.toLowerCase().contains(keyword)) {
                printTableRow(b.getBookId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getPubYear(), b.getQuantity());
                found = true;
            }
        }
        printTableFooter(found, keyword);
    }

    // --- CÁC HÀM HỖ TRỢ ---
    public static Book findBookById(String bookId) {
        for (Book b : bookList) {
            if (b.getBookId() != null && b.getBookId().equalsIgnoreCase(bookId.trim())) {
                return b;
            }
        }
        return null;
    }

    public static int getTotalQuantity() {
        int total = 0;
        for (Book b : bookList) {
            total += b.getQuantity();
        }
        return total;
    }
    private static final String SEPARATOR_LINE = "+----------+---------------------------+----------------------+-----------------+--------+-------+";
     
    private static final String TABLE_HEADER_FORMAT = "| %-8s | %-25s | %-20s | %-15s | %6s | %5s |%n";
    private static final String TABLE_ROW_FORMAT    = "| %-8s | %-25s | %-20s | %-15s | %6d | %5d |%n";

    public static void printTableHeader() {
        System.out.println(SEPARATOR_LINE);
        System.out.printf(TABLE_HEADER_FORMAT, "ID", "Title", "Author", "Genre", "Year", "Qty");
        System.out.println(SEPARATOR_LINE);
    }

    // Hàm in dữ liệu khớp hoàn toàn theo hàng lối
    public static void printTableRow(String id, String title, String author, String genre, int year, int qty) {
        System.out.printf(TABLE_ROW_FORMAT, id, title, author, genre, year, qty);
    }

    public static void printTableFooter(boolean found, String keyword) {
        System.out.println(SEPARATOR_LINE);
        if (!found) {
         
            System.out.printf("| %-96s |%n", "No books match the keyword: '" + keyword + "'");
            System.out.println(SEPARATOR_LINE);
        }
    }
}
