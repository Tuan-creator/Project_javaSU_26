
package manage_libarary_system;

import borrowing.Borrowing;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import member.Member;
import util.InputHelper;
import util.ValidationBorrow;

public class Borrowing_Returning {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> borrowIdList = new ArrayList<>();
    static ArrayList<String> memberIdList = new ArrayList<>();
    static ArrayList<String> memberNameList = new ArrayList<>();
    static ArrayList<String> memberEmailList = new ArrayList<>();
    static ArrayList<String> memberPhoneList = new ArrayList<>();
    static ArrayList<String> borrowDateList = new ArrayList<>();
    static ArrayList<String> dueDateList = new ArrayList<>();
    static ArrayList<String> statusList = new ArrayList<>();
    static ArrayList<String> bookIdList = new ArrayList<>();
    static ArrayList<String> returnDateList = new ArrayList<>();
    
    
    
    
    
    
    
    
    static ArrayList<Borrowing> listBorrow= new ArrayList<>();
    static ArrayList<Member> listMem= new ArrayList<>();
    

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== BORROWING / RETURNING =====");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View All Borrowed Books");
            System.out.println("4. View Borrowing History By Member");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose: ");

            choice = Integer.parseInt(sc.nextLine());

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
        System.out.println("Borrow ID already exists!");
        return;
        }
       
          String memId = InputHelper.memId();
         String bookId = InputHelper.BookId();
         
         LocalDate borrowDate=InputHelper.borrowDate();
         LocalDate dueDate=InputHelper.dueDate(borrowDate);
         
          Borrowing b = new Borrowing(transactionId, memId, bookId, borrowDate, dueDate);
          
          listBorrow.add(b);
          System.out.println("Borrow Book Successfully!");
          
          
          
          
          
          

//        System.out.print("Enter Borrow ID: ");
//        String borrowId =
//                ValidationBorrow.formatTransactionId(sc.nextLine());
//
//        if (!ValidationBorrow.isValidTransactionId(borrowId)) {
//            System.out.println("Borrow ID must be P001 format!");
//            return;
//        }
//
//        if (isExistBorrowId(borrowId)) {
//            System.out.println("Borrow ID already exists!");
//            return;
//        }
//
//        System.out.print("Enter Member ID: ");
//        String memberId =
//                ValidationBorrow.formatMemberId(sc.nextLine());
//        System.out.print("Enter Book ID: ");
//        String bookId =
//                ValidationBorrow.formatBookId(sc.nextLine());
//
//        if (!ValidationBorrow.isValidBookId(bookId)) {
//            System.out.println("Invalid Book ID!");
//            return;
//        }
//
//        if (!ValidationBorrow.isValidMemberId(memberId)) {
//            System.out.println("Member ID must be M001 format!");
//            return;
//        }
//
//        System.out.print("Enter Member Name: ");
//        String memberName = sc.nextLine();
//
//        System.out.print("Enter Email: ");
//        String email = sc.nextLine();
//
//        System.out.print("Enter Phone: ");
//        String phone = sc.nextLine();
//
//        System.out.print("Enter Borrow Date (dd/MM/yyyy): ");
//        String borrowDate = sc.nextLine();
//
//        if (!ValidationBorrow.isValidDateFormat(borrowDate)) {
//            System.out.println("Invalid Borrow Date!");
//            return;
//        }
//
//        LocalDate bd = LocalDate.parse(
//                borrowDate,
//                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//        if (!ValidationBorrow.isValidBorrowDate(bd)) {
//            System.out.println("Borrow Date is invalid!");
//            return;
//        }
//
//        System.out.print("Enter Due Date (dd/MM/yyyy): ");
//        String dueDate = sc.nextLine();
//
//        if (!ValidationBorrow.isValidDateFormat(dueDate)) {
//            System.out.println("Invalid Due Date!");
//            return;
//        }
//
//        LocalDate dd = LocalDate.parse(
//                dueDate,
//                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//        if (!ValidationBorrow.isValidDueDate(bd, dd)) {
//            System.out.println("Due Date must be after Borrow Date!");
//            return;
//        }
//
//        borrowIdList.add(borrowId);
//        memberIdList.add(memberId);
//
//        memberNameList.add(memberName);
//        memberEmailList.add(email);
//        memberPhoneList.add(phone);
//
//        borrowDateList.add(borrowDate);
//        dueDateList.add(dueDate);
//
//        statusList.add("No Return");
//
//        System.out.println("Borrow Book Successfully!");
//        bookIdList.add(bookId);
//        returnDateList.add("");
    }

    // Return Book
    public static void returnBook() {
        
        if (listBorrow.isEmpty())
        {
            System.out.println("Borrow list is empty!");
            return;
        }
        
        String transactionId= InputHelper.tranId();
        Borrowing found=null;
        
        for (Borrowing b: listBorrow)
        {
            if (b.getTransactionId().equalsIgnoreCase(transactionId))
            {
                found=b;
                break;
            }
        }
        
        if (found==null)
        {
            System.out.println("Borrow Id not found!");
            return;
        }
        
       if (found.isReturned()) {
        System.out.println("This book has already been returned!");
        return;
     }
       
       LocalDate returnDate=InputHelper.returnDate(found.getBorrowDate());
       found.setReturnDate(returnDate);
       
        System.out.println("Return book successfully!");
        
        
        
        
        
        
        
        
        
        
        
        
        

//        if (isBorrowListEmpty()) {
//            System.out.println("Borrow list is empty!");
//            return;
//        }
//
//        System.out.print("Enter Borrow ID: ");
//        String borrowId =
//                ValidationBorrow.formatTransactionId(sc.nextLine());
//
//        if (!isExistBorrowId(borrowId)) {
//            System.out.println("Borrow ID not found!");
//            return;
//        }
//
//        for (int i = 0; i < borrowIdList.size(); i++) {
//
//            if (borrowIdList.get(i).equals(borrowId)) {
//
//                System.out.print("Enter Return Date (dd/MM/yyyy): ");
//                String returnDate = sc.nextLine();
//
//                if (!ValidationBorrow.isValidDateFormat(returnDate)) {
//                    System.out.println("Invalid Return Date!");
//                    return;
//                }
//
//                LocalDate borrowDate = LocalDate.parse(borrowDateList.get(i),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//                LocalDate rd = LocalDate.parse(returnDate,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//                if (!ValidationBorrow.isValidReturnDate(borrowDate, rd)) {
//
//                    System.out.println("Return Date must be after Borrow Date!");
//                    return;
//                }
//                returnDateList.set(i, returnDate);
//                statusList.set(i, "Returned");
//                System.out.println("Return Book Successfully!");
//                return;
//            }
//        }
    }

    // View All Borrowed Books
    public static void viewBorrowList() {

        if (isBorrowListEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("BorrowID\tMemberID\tBookID\tBorrow\t\tDue\t\tReturn\t\tStatus");
        System.out.println("------------------------------------------------------------------------------------------------");

        for (int i = 0; i < borrowIdList.size(); i++) {

            System.out.println(
                borrowIdList.get(i) + "\t\t"
                + memberIdList.get(i) + "\t\t"
                + bookIdList.get(i) + "\t"
                + borrowDateList.get(i) + "\t"
                + dueDateList.get(i) + "\t"
                + returnDateList.get(i) + "\t"
                + statusList.get(i));
        }
    }

    // View Borrowing History By Member
    public static void viewHistory() {

        if (isBorrowListEmpty()) {
            System.out.println("Borrow history is empty!");
            return;
        }

        System.out.print("Enter Member ID: ");
        String memberId = ValidationBorrow.formatMemberId(sc.nextLine());

        boolean found = false;

        for (int i = 0; i < memberIdList.size(); i++) {
            if (memberIdList.get(i).equals(memberId)) {
                System.out.println("--------------------------------");
                System.out.println("Borrow ID : " + borrowIdList.get(i));
                System.out.println("Member ID : " + memberIdList.get(i));
                System.out.println("Name      : " + memberNameList.get(i));
                System.out.println("Email     : " + memberEmailList.get(i));
                System.out.println("Phone     : " + memberPhoneList.get(i));
                System.out.println("Borrow    : " + borrowDateList.get(i));
                System.out.println("Due Date  : " + dueDateList.get(i));
                System.out.println("Status    : " + statusList.get(i));
                System.out.println("Book ID    : " + bookIdList.get(i));
                System.out.println("Return Date: " + returnDateList.get(i));
                System.out.println("--------------------------------");

                found = true;
            }
        }
        if (!found) {
            System.out.println("Member ID not found!");
        }
    }

    // Validation
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isExistBorrowId(String borrowId) {
        for (int i = 0; i < borrowIdList.size(); i++) {
            if (borrowIdList.get(i).equals(borrowId)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isBorrowListEmpty() {
        return borrowIdList.isEmpty();
    }
}
