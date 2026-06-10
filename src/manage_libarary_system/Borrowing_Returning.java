package manage_libarary_system;

import book.Book;
import borrowing.Borrowing;
import java.time.LocalDate;
import java.util.ArrayList;
import member.Member;
import util.InputHelper;

public class Borrowing_Returning {

    static ArrayList<Borrowing> listBorrow = new ArrayList<>();
    static ArrayList<Member> listMember = new ArrayList<>();
    

   public static void showMenu() {
        int choice;
        do {
            System.out.println("\n===== BORROWING / RETURNING =====");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View All Borrowed Books");
            System.out.println("4. View Borrowing History By Member");
            System.out.println("5. Back to Main Menu");
            
           

             choice = InputHelper.inputInt("Choose: ");

            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    viewBorrowList();
                    break;
                case 4:
                    viewHistory();
                    break;
                case 5:
                    System.out.println("Back to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // Borrow Book
    public static void borrowBook() {
        String transactionId = InputHelper.tranId();

        if (isExistBorrowId(transactionId)) {
            System.out.println("Transaction ID already exists!");
            return;
        }
        
        
        
        String memberId = InputHelper.memId();
        Member member = Manage_Member.findMemberById(memberId);

       if (member == null) {
        System.out.println("Member ID not found!");
             return;
          }


         if (member == null) {
               System.out.println("Member ID not found!");
               return;
             } 
//        String memberName = InputHelper.Name();
//        String phone = InputHelper.phone();
//        String email = InputHelper.email();




        String bookId = InputHelper.BookId();
        
        Book book=  Manage_Book.findBookById(bookId);
        if (book==null)
        {
            System.out.println("Book ID not found!");
            return;
           
        }
        
           
        if (book.getQuantity() <= 0) {
       System.out.println("Book is out of stock!");
        return;
}
      
        LocalDate borrowDate = InputHelper.borrowDate();
        LocalDate dueDate = InputHelper.dueDate(borrowDate);

        Borrowing borrow= new Borrowing(transactionId, memberId, bookId, borrowDate, dueDate,member.getPhone(), member.getEmail(), member.getName());


        listBorrow.add(borrow);
        book.setQuantity(book.getQuantity()-1);
        System.out.println("Borrow Book Successfully!");
    }

    
    // Return Book
    public static void returnBook() {

        if (listBorrow.isEmpty()) {
            System.out.println("Borrow list is empty!");
            return;
        }

        
        String transactionId = InputHelper.tranId();
        Borrowing found = null;
        for (Borrowing b : listBorrow) {
            if (b.getTransactionId().equalsIgnoreCase(transactionId)) {
                found = b;
                break;
            }
        }

        if (found == null) {
            System.out.println("Transaction ID not found!");
            return;
        }

        if (found.isReturned()) {
            System.out.println("This book has already been returned!");
            return;
        }

        LocalDate returnDate = InputHelper.returnDate(found.getBorrowDate());
        found.setReturnDate(returnDate);
        Book book = Manage_Book.findBookById(found.getBookId());
        
        if (book!=null)
        {
            book.setQuantity(book.getQuantity()+1);
        }
        
        System.out.println("Return Book Successfully!");
    }

    // View All Borrowed Books
    public static void viewBorrowList() {

        if (listBorrow.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        System.out.println("=============================================================================================================================================================");
        System.out.printf("%-12s %-12s %-12s %-18s %-25s %-15s %-15s %-15s %-15s %-10s\n", "TransID", "MemberID", "BookID", "Name", "Email", "Phone", "Borrow", "Due", "Return", "Status");
        System.out.println("=============================================================================================================================================================");

        for (Borrowing b : listBorrow) {
            String returnDate = b.getReturnDate() == null? "N/A" : b.getReturnDate().toString();
            String status = b.isReturned() ? "Returned" : "No Return";

            System.out.printf("%-12s %-12s %-12s %-18s %-25s %-15s %-15s %-15s %-15s %-10s\n",
            b.getTransactionId(),
            b.getMemberId(),
            b.getBookId(),
            b.getMemberName(),
            b.getEmail(),
            b.getPhone(),
            b.getBorrowDate(),
            b.getDueDate(),
            returnDate,
            status);
        }
    }

    // View History By Member
    public static void viewHistory() {

        if (listBorrow.isEmpty()) {
            System.out.println("Borrow history is empty!");
            return;
        }

        String memberId = InputHelper.memId();

        boolean found = false;

        for (Borrowing b : listBorrow) {
            if (b.getMemberId().equalsIgnoreCase(memberId)) {

                System.out.println("--------------------------------------------");
                System.out.println("Transaction ID : " + b.getTransactionId());
                System.out.println("Book ID        : " + b.getBookId());
                System.out.println("Name           : " + b.getMemberName());
                System.out.println("Email          : " + b.getEmail());
                System.out.println("Phone          : " + b.getPhone());
                System.out.println("Borrow Date    : " + b.getBorrowDate());
                System.out.println("Due Date       : " + b.getDueDate());
                System.out.println("Return Date    : " + b.getReturnDate());
                System.out.println("Status         : " + (b.isReturned() ? "Returned" : "No Return"));
                System.out.println("--------------------------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("Member ID not found!");
        }
    }

    // Check Transaction ID
    public static boolean isExistBorrowId(String transactionId) {

        for (Borrowing b : listBorrow) {
            if (b.getTransactionId().equalsIgnoreCase(transactionId)) {
                return true;
            }
        }
        return false;
    }
}
