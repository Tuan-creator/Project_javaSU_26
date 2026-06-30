package manage_libarary_system;

import book.Book;
import borrowing.Borrowing;
import java.time.LocalDate;
import java.util.ArrayList;
import member.Member;
import util.InputHelper;
import java.util.Collections;
import java.util.Comparator;

public class Borrowing_Returning implements IBorrowingManager {

      public ArrayList<Borrowing> listBorrow = new ArrayList<>();
//    private  ArrayList<Member> listMember = new ArrayList<>();
        private Manage_Book bookManager;
        private Manage_Member memberManager;
    
        public Borrowing_Returning(Manage_Book bookManager, Manage_Member memberManager) {
        this.bookManager = bookManager;
        this.memberManager = memberManager;
}

   public void showMenu() {
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
   @Override
    public void borrowBook() {
        String transactionId = InputHelper.tranId();

        if (isExistBorrowId(transactionId)) {
            System.out.println("Transaction ID already exists!");
            return;
        }
        
        
        
        String memberId = InputHelper.memId();
        Member member = memberManager.findMemberById(memberId);

       if (member == null) {
        System.out.println("Member ID not found!");
             return;
          }


         
         int currentBorrowed = countCurrentyBorrwed(memberId);
         int borrowLimit = member.getBorrowLimit();
         
         
         if (currentBorrowed >= borrowLimit) {
         System.out.println("This member has reached the borrowing limit!");
         System.out.println("Current borrowed: " + currentBorrowed);
         System.out.println("Borrow limit: " + borrowLimit);
         return;
}
         
         
//        String memberName = InputHelper.Name();
//        String phone = InputHelper.phone();
//        String email = InputHelper.email();




        String bookId = InputHelper.BookId();
        
        Book book = bookManager.findBookById(bookId);
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
        book.setBorrowCount(book.getBorrowCount() + 1);
        System.out.println("Borrow Book Successfully!");
    }

    
    // Return Book
    @Override
    public  void returnBook() {

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
        long daysOverdue = 0;
        if (returnDate.isAfter(found.getDueDate()))
        {
            daysOverdue=returnDate.toEpochDay()-found.getDueDate().toEpochDay();
        }
        
        
       Member member = memberManager.findMemberById(found.getMemberId());
        
        double fine=0;
        if (member!=null)
        {
           fine=member.calculateFine((int)daysOverdue);
        }
        found.setFine(fine);
        
        
        
      Book book = bookManager.findBookById(found.getBookId());
        if (book!=null)
        {
            book.setQuantity(book.getQuantity()+1);
        }
        
        System.out.println("Return Book Successfully!");
    }

    // View All Borrowed Books
    @Override
    public  void viewBorrowList() {

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
    @Override
    public  void viewHistory() {

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
                System.out.println("Fine           : " + b.getFine());
                System.out.println("--------------------------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("Member ID not found!");
        }
    }

    // Check Transaction ID
    public  boolean isExistBorrowId(String transactionId) {

        for (Borrowing b : listBorrow) {
            if (b.getTransactionId().equalsIgnoreCase(transactionId)) {
                return true;
            }
        }
        return false;
    }
    
    public  int countCurrentyBorrwed(String memberId)
    {
         int count = 0;

    for (Borrowing b : listBorrow) {
        if (b.getMemberId().equalsIgnoreCase(memberId) && !b.isReturned()) {
            count++;
        }
    }

    return count;
    }
    
    public boolean isBookBorrowed(String bookId)
    {
        for (Borrowing b:listBorrow)
        {
            if (b.getBookId().equalsIgnoreCase(bookId)&& !b.isReturned())
            {
                return true;
            }
        }
        return false;
    }
    
    public void sortByTransactionId() {
    Collections.sort(listBorrow, new Comparator<Borrowing>() {
        @Override
        public int compare(Borrowing b1, Borrowing b2) {
            return b1.getTransactionId().compareToIgnoreCase(b2.getTransactionId());
        }
    });
    System.out.println("Sorted by Transaction ID successfully!");
    }   
}
