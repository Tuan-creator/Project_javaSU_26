package manage_libarary_system;

import book.Book; 
import java.util.ArrayList;
import java.util.Scanner;
import util.ValidationBook;

public class Manage_Book {

    static Scanner sc = new Scanner(System.in);
    // Danh sách lưu trữ tất cả các cuốn sách trong thư viện
    static ArrayList<Book> bookList = new ArrayList<>();

    
    

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

    // --- HÀM NHẬP CHUỖI ĐA NĂNG (DÙNG CHỮ ĐỂ PHÂN BIỆT LOẠI KIỂM TRA) ---
    private static String inputString(String prompt, String errorMsg, String checkType, boolean toUpper) {
    int attempts = 0;
    while (attempts < 5) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (toUpper) {
            input = input.toUpperCase();
        }
        switch (checkType.toLowerCase()) {
            case "id":
                if (ValidationBook.isValidBookId(input) && findBookById(input) == null) {
                    return input; 
                }
                break; 

            case "title":
                if (ValidationBook.isValidTitle(input)) {
                    return input;
                }
                break;

            case "author":
                if (ValidationBook.isValidAuthor(input)) {
                    return input;
                }
                break;

            case "genre":
                if (ValidationBook.isValidGenre(input)) {
                    return input;
                }
                break;
        }

        attempts++;
        System.out.println(errorMsg + " (Attempts: " + attempts + "/5)");
    }
    throw new IllegalArgumentException("Too many failed attempts!");
}
    // HÀM NHẬP SỐ NGUYÊN ĐA NĂNG
    private static int inputInt(String prompt, String errorMsg, String checkType) {
    int attempts = 0;
    while (attempts < 5) {
        System.out.print(prompt);
        try {
            int value = Integer.parseInt(sc.nextLine());
            switch (checkType.toLowerCase()) {
                case "year":
                    if (ValidationBook.isValidPubYear(value)) {
                        return value; 
                    }
                    break; 

                case "quantity":
                    if (ValidationBook.isValidQuantity(value)) {
                        return value; 
                    }
                    break;
            }
            System.out.println(errorMsg + " (Attempts: " + (attempts + 1) + "/5)");

        } catch (NumberFormatException e) {
            System.out.println("Error: Must be a valid integer! (Attempts: " + (attempts + 1) + "/5)");
        }
        
        attempts++;
    }
    throw new IllegalArgumentException("Too many failed attempts!");
}

    // 1. Thêm sách mới 
    public static void addBook() {
        System.out.println("\n--- Add New Book ---");
        try {
            String bookId = inputString("Enter Book ID (Format Bxxx, e.g., B001): ", "Error: Invalid ID format or already exists!", "id", true);
            String title = inputString("Enter Title: ", "Error: Invalid Title!", "title", false);
            String author = ValidationBook.formatName(inputString("Enter Author: ", "Error: Invalid Author name!", "author", false));
            String genre = ValidationBook.formatName(inputString("Enter Genre: ", "Error: Invalid Genre!", "genre", false));
            int pubYear = inputInt("Enter Publication Year: ", "Error: Publication Year must be between 1 and 2026!", "year");
            int quantity = inputInt("Enter Quantity: ", "Error: Quantity cannot be negative!", "quantity");

            Book newBook = new Book(bookId, title, author, genre, pubYear, quantity);
            bookList.add(newBook);
            System.out.println("Add Book Successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage() + " Action canceled.");
        }
    }

    // 2. Cập nhật thông tin sách 
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
            book.setTitle(inputString("New Title: ", "Error: Invalid Title!", "title", false));
            book.setAuthor(ValidationBook.formatName(inputString("New Author: ", "Error: Invalid Author name!", "author", false)));
            book.setGenre(ValidationBook.formatName(inputString("New Genre: ", "Error: Invalid Genre!", "genre", false)));
            book.setPubYear(inputInt("New Publication Year: ", "Error: Year must be between 1 and 2026!", "year"));
            book.setQuantity(inputInt("New Quantity: ", "Error: Quantity cannot be negative!", "quantity"));

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
                System.out.println(b);
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
