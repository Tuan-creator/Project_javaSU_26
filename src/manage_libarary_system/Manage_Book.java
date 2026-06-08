package mangage_libarary_system;

import book.Book; 
import java.util.ArrayList;
import java.util.Scanner;
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
                    System.out.println("Back to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number from 1 to 6.");
            }
        } while (choice != 6);
    }

    public static void showMenu() {
        System.out.println("\n===== BOOK MANAGEMENT =====");
        System.out.println("1. Add New Book");
        System.out.println("2. Update Book Information");
        System.out.println("3. Delete Book");
        System.out.println("4. View All Books");
        System.out.println("5. Search Book");
        System.out.println("6. Back to Main Menu");
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

    // 1. Thêm sách mới (Sử dụng ValidationBook + Cho phép nhập lại tối đa 5 lần)
    public static void addBook() {
        System.out.println("\n--- Add New Book ---");
        
        String bookId = "";
        String title = "";
        String author = "";
        String genre = "";
        int pubYear = 0;
        int quantity = 0;
        int attempts;

        try {
            // --- NHẬP BOOK ID ---
            attempts = 0;
            while (true) {
                System.out.print("Enter Book ID (Format Bxxx, e.g., B001): ");
                bookId = sc.nextLine();
                
                if (!ValidationBook.isValidBookId(bookId)) {
                    attempts++;
                    System.out.println("Error: Invalid Book ID format! Must be 'B' followed by 3 digits. (Attempts: " + attempts + "/5)");
                } else {
                    bookId = ValidationBook.formatBookId(bookId);
                    if (findBookById(bookId) != null) {
                        attempts++;
                        System.out.println("Error: Book ID already exists! (Attempts: " + attempts + "/5)");
                    } else {
                        break; 
                    }
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Book ID!");
            }

            // --- NHẬP TITLE ---
            attempts = 0;
            while (true) {
                System.out.print("Enter Title: ");
                title = sc.nextLine();
                if (!ValidationBook.isValidTitle(title)) {
                    attempts++;
                    System.out.println("Error: Invalid Title! Cannot be empty or contain special characters. (Attempts: " + attempts + "/5)");
                } else {
                    title = title.trim();
                    break;
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Title!");
            }

            // --- NHẬP AUTHOR ---
            attempts = 0;
            while (true) {
                System.out.print("Enter Author: ");
                author = sc.nextLine();
                if (!ValidationBook.isValidAuthor(author)) {
                    attempts++;
                    System.out.println("Error: Invalid Author name! Only letters and spaces allowed. (Attempts: " + attempts + "/5)");
                } else {
                    author = ValidationBook.formatName(author);
                    break;
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Author!");
            }

            // --- NHẬP GENRE ---
            attempts = 0;
            while (true) {
                System.out.print("Enter Genre: ");
                genre = sc.nextLine();
                if (!ValidationBook.isValidGenre(genre)) {
                    attempts++;
                    System.out.println("Error: Invalid Genre! Only letters and spaces allowed. (Attempts: " + attempts + "/5)");
                } else {
                    genre = ValidationBook.formatName(genre);
                    break;
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Genre!");
            }

            // --- NHẬP PUBLICATION YEAR ---
            attempts = 0;
            while (true) {
                System.out.print("Enter Publication Year: ");
                try {
                    pubYear = Integer.parseInt(sc.nextLine());
                    if (!ValidationBook.isValidPubYear(pubYear)) {
                        attempts++;
                        System.out.println("Error: Publication Year must be between 1 and 2026! (Attempts: " + attempts + "/5)");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    attempts++;
                    System.out.println("Error: Publication Year must be a valid integer! (Attempts: " + attempts + "/5)");
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Publication Year!");
            }

            // --- NHẬP QUANTITY ---
            attempts = 0;
            while (true) {
                System.out.print("Enter Quantity: ");
                try {
                    quantity = Integer.parseInt(sc.nextLine());
                    if (!ValidationBook.isValidQuantity(quantity)) {
                        attempts++;
                        System.out.println("Error: Quantity cannot be negative! (Attempts: " + attempts + "/5)");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    attempts++;
                    System.out.println("Error: Quantity must be a valid integer! (Attempts: " + attempts + "/5)");
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Quantity!");
            }

            Book newBook = new Book(bookId, title, author, genre, pubYear, quantity);
            bookList.add(newBook);
            System.out.println("Add Book Successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Action canceled.");
        }
    }

    // 2. Cập nhật thông tin sách (Sử dụng ValidationBook + Cho phép nhập lại tối đa 5 lần)
    public static void updateBook() {
        System.out.println("\n--- Update Book Information ---");
        System.out.print("Enter Book ID to update: ");
        String bookId = sc.nextLine();

        if (!ValidationBook.isValidBookId(bookId)) {
            System.out.println("Error: Invalid Book ID format!");
            return;
        }
        
        bookId = ValidationBook.formatBookId(bookId);
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book ID not found!");
            return;
        }

        System.out.println("Current Info: " + book);
        
        String title = "";
        String author = "";
        String genre = "";
        int pubYear = 0;
        int quantity = 0;
        int attempts;

        try {
            // --- CẬP NHẬT TITLE ---
            attempts = 0;
            while (true) {
                System.out.print("New Title: ");
                title = sc.nextLine();
                if (!ValidationBook.isValidTitle(title)) {
                    attempts++;
                    System.out.println("Error: Invalid Title! (Attempts: " + attempts + "/5)");
                } else {
                    title = title.trim();
                    break;
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Title!");
            }

            // --- CẬP NHẬT AUTHOR ---
            attempts = 0;
            while (true) {
                System.out.print("New Author: ");
                author = sc.nextLine();
                if (!ValidationBook.isValidAuthor(author)) {
                    attempts++;
                    System.out.println("Error: Invalid Author name! (Attempts: " + attempts + "/5)");
                } else {
                    author = ValidationBook.formatName(author);
                    break;
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Author!");
            }

            // --- CẬP NHẬT GENRE ---
            attempts = 0;
            while (true) {
                System.out.print("New Genre: ");
                genre = sc.nextLine();
                if (!ValidationBook.isValidGenre(genre)) {
                    attempts++;
                    System.out.println("Error: Invalid Genre! (Attempts: " + attempts + "/5)");
                } else {
                    genre = ValidationBook.formatName(genre);
                    break;
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Genre!");
            }

            // --- CẬP NHẬT PUBLICATION YEAR ---
            attempts = 0;
            while (true) {
                System.out.print("New Publication Year: ");
                try {
                    pubYear = Integer.parseInt(sc.nextLine()); 
                    if (!ValidationBook.isValidPubYear(pubYear)) {
                        attempts++;
                        System.out.println("Error: Year must be between 1 and 2026! (Attempts: " + attempts + "/5)");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    attempts++;
                    System.out.println("Error: Year must be a valid integer! (Attempts: " + attempts + "/5)");
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Publication Year!");
            }

            // --- CẬP NHẬT QUANTITY ---
            attempts = 0;
            while (true) {
                System.out.print("New Quantity: ");
                try {
                    quantity = Integer.parseInt(sc.nextLine()); 
                    if (!ValidationBook.isValidQuantity(quantity)) {
                        attempts++;
                        System.out.println("Error: Quantity cannot be negative! (Attempts: " + attempts + "/5)");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    attempts++;
                    System.out.println("Error: Quantity must be a valid integer! (Attempts: " + attempts + "/5)");
                }
                if (attempts >= 5) throw new IllegalArgumentException("Too many failed attempts on Quantity!");
            }

            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setPubYear(pubYear);                     
            book.setQuantity(quantity);                     

            System.out.println("Update Book Successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Update canceled.");
        }
    }

    // 3. Xóa sách
    public static void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        System.out.print("Enter Book ID to delete: ");
        String bookId = sc.nextLine().trim();

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book ID not found!");
            return;
        }

        bookList.remove(book);
        System.out.println("Delete Book Successfully!");
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
            System.out.println(b); 
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // 5. Menu con Tìm kiếm Sách
    public static void searchBook() {
        if (bookList.isEmpty()) {
            System.out.println("\nLibrary has no books to search!");
            return;
        }

        int subChoice;
        do {
            showSearchMenu(); // Gọi hàm hiển thị menu tìm kiếm sách
            try {
                subChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                subChoice = -1;
            }

            switch (subChoice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByAuthor();
                    break;
                case 4:
                    System.out.println("Returning to Book Management Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 1 to 4.");
            }
        } while (subChoice != 4);
    }

    // --- CÁC HÀM TÌM KIẾM CHI TIẾT ---
    private static void searchById() {
        try {
            System.out.print("Enter Book ID to search: ");
            String keyword = sc.nextLine().trim().toLowerCase();
            boolean found = false;
            printTableHeader();
            for (Book b : bookList) {
                if (b.getBookId() != null && b.getBookId().toLowerCase().contains(keyword)) {
                    System.out.println(b);
                    found = true;
                }
            }
            printTableFooter(found, keyword);
        } catch (Exception e) {
            System.out.println("An error occurred during search: " + e.getMessage());
        }
    }

    private static void searchByTitle() {
        try {
            System.out.print("Enter Title to search: ");
            String keyword = sc.nextLine().trim().toLowerCase();
            boolean found = false;
            printTableHeader();
            for (Book b : bookList) {
                if (b.getTitle() != null && b.getTitle().toLowerCase().contains(keyword)) {
                    System.out.println(b);
                    found = true;
                }
            }
            printTableFooter(found, keyword);
        } catch (Exception e) {
            System.out.println("An error occurred during search: " + e.getMessage());
        }
    }

    private static void searchByAuthor() {
        try {
            System.out.print("Enter Author to search: ");
            String keyword = sc.nextLine().trim().toLowerCase();
            boolean found = false;
            printTableHeader();
            for (Book b : bookList) {
                if (b.getAuthor() != null && b.getAuthor().toLowerCase().contains(keyword)) {
                    System.out.println(b);
                    found = true;
                }
            }
            printTableFooter(found, keyword);
        } catch (Exception e) {
            System.out.println("An error occurred during search: " + e.getMessage());
        }
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

    public static void printTableHeader() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("ID \t| Title \t\t| Author \t\t| Genre \t| Year \t| Qty");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printTableFooter(boolean found, String keyword) {
        System.out.println("--------------------------------------------------------------------------------");
        if (!found) {
            System.out.println("No books match the keyword: '" + keyword + "'");
        }
    }
}
