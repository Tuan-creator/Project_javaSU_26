package manage_libarary;

import book.Book; 
import java.util.ArrayList;
import java.util.Scanner;
import util.ValidationBook;

public class Manage_Book {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> bookList = new ArrayList<>();

    // Định nghĩa Interface để truyền hàm kiểm tra (Validation) vào làm tham số
    interface StringValidator { boolean validate(String s); }
    interface IntValidator { boolean validate(int n); }

    
    public static void showMenu() {
        System.out.println("\n===== BOOK MANAGEMENT =====");
        System.out.println("1. Add New Book\n2. Update Book Information\n3. Delete Book\n4. View All Books\n5. Search Book\n6. Back to Main Menu");
        System.out.print("Choose: ");
    }

    public static void showSearchMenu() {
        System.out.println("\n===== SEARCH BOOK =====");
        System.out.println("1. Search by Book ID\n2. Search by Title\n3. Search by Author\n4. Return to Book Menu");
        System.out.print("Choose search criteria: ");
    }

    // --- HÀM NHẬP CHUỖI ĐA NĂNG (Tối đa 5 lần) ---
    private static String inputString(String prompt, String errorMsg, StringValidator validator, boolean toUpper) {
        int attempts = 0;
        while (attempts < 5) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (toUpper) input = input.toUpperCase();

            if (validator.validate(input)) return input;

            attempts++;
            System.out.println(errorMsg + " (Attempts: " + attempts + "/5)");
        }
        throw new IllegalArgumentException("Too many failed attempts!");
    }

    // --- HÀM NHẬP SỐ NGUYÊN ĐA NĂNG (Tối đa 5 lần) --
    private static int inputInt(String prompt, String errorMsg, IntValidator validator) {
        int attempts = 0;
        while (attempts < 5) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (validator.validate(value)) return value;
            } catch (NumberFormatException e) {
                // Bắt lỗi nếu người dùng nhập chữ thay vì số
            }
            attempts++;
            System.out.println(errorMsg + " (Attempts: " + attempts + "/5)");
        }
        throw new IllegalArgumentException("Too many failed attempts!");
    }

    // 1. Thêm sách mới (Đã rút gọn bằng các hàm input)
    public static void addBook() {
        System.out.println("\n--- Add New Book ---");
        try {
            String bookId = inputString("Enter Book ID (Format Bxxx): ", "Error: Invalid ID format or already exists!", 
                id -> ValidationBook.isValidBookId(id) && findBookById(id) == null, true);

            String title = inputString("Enter Title: ", "Error: Invalid Title!", 
                t -> ValidationBook.isValidTitle(t), false);

            String author = ValidationBook.formatName(inputString("Enter Author: ", "Error: Invalid Author name!", 
                a -> ValidationBook.isValidAuthor(a), false));

            String genre = ValidationBook.formatName(inputString("Enter Genre: ", "Error: Invalid Genre!", 
                g -> ValidationBook.isValidGenre(g), false));

            int pubYear = inputInt("Enter Publication Year: ", "Error: Must be between 1 and 2026!", 
                y -> ValidationBook.isValidPubYear(y));

            int quantity = inputInt("Enter Quantity: ", "Error: Quantity cannot be negative!", 
                q -> ValidationBook.isValidQuantity(q));

            bookList.add(new Book(bookId, title, author, genre, pubYear, quantity));
            System.out.println("Add Book Successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Action canceled.");
        }
    }

    // 2. Cập nhật thông tin sách (Đã rút gọn bằng các hàm input)
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
        try {
            book.setTitle(inputString("New Title: ", "Error: Invalid Title!", t -> ValidationBook.isValidTitle(t), false));
            book.setAuthor(ValidationBook.formatName(inputString("New Author: ", "Error: Invalid Author!", a -> ValidationBook.isValidAuthor(a), false)));
            book.setGenre(ValidationBook.formatName(inputString("New Genre: ", "Error: Invalid Genre!", g -> ValidationBook.isValidGenre(g), false)));
            book.setPubYear(inputInt("New Publication Year: ", "Error: Must be between 1 and 2026!", y -> ValidationBook.isValidPubYear(y)));
            book.setQuantity(inputInt("New Quantity: ", "Error: Quantity cannot be negative!", q -> ValidationBook.isValidQuantity(q)));

            System.out.println("Update Book Successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Update canceled.");
        }
    }

    // 3. Xóa sách
    public static void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        System.out.print("Enter Book ID to delete: ");
        Book book = findBookById(sc.nextLine().trim());
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
        for (Book b : bookList) System.out.println(b); 
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
            showSearchMenu();
            try {
                subChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                subChoice = -1;
            }
            switch (subChoice) {
                case 1: searchByKey("Enter Book ID to search: ", 1); break;
                case 2: searchByKey("Enter Title to search: ", 2); break;
                case 3: searchByKey("Enter Author to search: ", 3); break;
                case 4: System.out.println("Returning to Book Management Menu..."); break;
                default: System.out.println("Invalid choice! Please choose from 1 to 4.");
            }
        } while (subChoice != 4);
    }

    // Gộp chung 3 hàm tìm kiếm cũ thành 1 hàm cực ngắn
    private static void searchByKey(String prompt, int type) {
        System.out.print(prompt);
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        printTableHeader();
        for (Book b : bookList) {
            String target = (type == 1) ? b.getBookId() : (type == 2) ? b.getTitle() : b.getAuthor();
            if (target != null && target.toLowerCase().contains(keyword)) {
                System.out.println(b);
                found = true;
            }
        }
        printTableFooter(found, keyword);
    }

    // --- CÁC HÀM HỖ TRỢ ---
    public static Book findBookById(String bookId) {
        for (Book b : bookList) {
            if (b.getBookId() != null && b.getBookId().equalsIgnoreCase(bookId.trim())) return b;
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
        if (!found) System.out.println("No books match the keyword: '" + keyword + "'");
    }
}
