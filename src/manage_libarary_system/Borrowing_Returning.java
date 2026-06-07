
package manage_libarary_system;

import java.util.ArrayList;
import java.util.Scanner;

public class Borrowing_Returning {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> borrowIdList = new ArrayList<>();
    static ArrayList<String> memberIdList = new ArrayList<>();

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

    // Borrow Book (add - ktra giao dịch mượn sách mới)
    // Borrow ID: P001 - Member ID: M001
    public static void borrowBook() {

        System.out.print("Enter Borrow ID: ");
        String borrowId = sc.nextLine();

        if (isEmpty(borrowId)) {
            System.out.println("Borrow ID cannot be empty!");
            return;
        }

        if (isExistBorrowId(borrowId)) {
            System.out.println("Borrow ID already exists!");
            return;
        }

        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();

        if (isEmpty(memberId)) {
            System.out.println("Member ID cannot be empty!");
            return;
        }

        borrowIdList.add(borrowId);
        memberIdList.add(memberId);

        System.out.println("Borrow Book Successfully!");
    }

    // Return Book (trả sách - xóa các giao dịch mượn sách)
    public static void returnBook() {

        if (isBorrowListEmpty()) {
            System.out.println("Borrow list is empty!");
            return;
        }

        System.out.print("Enter Borrow ID: ");
        String borrowId = sc.nextLine();

        if (!isExistBorrowId(borrowId)) {
            System.out.println("Borrow ID not found!");
            return;
        }

        for (int i = 0; i < borrowIdList.size(); i++) {

            if (borrowIdList.get(i).equals(borrowId)) {
                borrowIdList.remove(i);
                memberIdList.remove(i);

                System.out.println("Return Book Successfully!");
                return;
            }
        }
    }

    // View All Borrowed Books (hiện ds sách đang dc mượn)
    public static void viewBorrowList() {

        if (isBorrowListEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        System.out.println("--------------------------------");
        System.out.println("Borrow ID\tMember ID");
        System.out.println("--------------------------------");

        for (int i = 0; i < borrowIdList.size(); i++) {
            System.out.println(borrowIdList.get(i)+ "\t\t"+ memberIdList.get(i));
        }
    }

    // View Borrowing History By Member (xem ls mượn sách từ member)
    public static void viewHistory() {

        if (isBorrowListEmpty()) {
            System.out.println("Borrow history is empty!");
            return;
        }

        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();

        if (isEmpty(memberId)) {
            System.out.println("Member ID cannot be empty!");
            return;
        }

        boolean found = false;

        System.out.println("--------------------------------");
        System.out.println("Borrow ID\tMember ID");
        System.out.println("--------------------------------");

        for (int i = 0; i < memberIdList.size(); i++) {
            if (memberIdList.get(i).equals(memberId)) {
                System.out.println(borrowIdList.get(i)+ "\t\t"+ memberIdList.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Member ID not found!");
        }
    }

    // ===== VALIDATION =====
    public static boolean isEmpty(String str) { //ktra trống
        return str.trim().isEmpty();
    }
    public static boolean isExistBorrowId(String borrowId) { //ktra đã tồn tại
        for (int i = 0; i < borrowIdList.size(); i++) {
            if (borrowIdList.get(i).equals(borrowId)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isBorrowListEmpty() { //ktra ds mượn trống
        return borrowIdList.isEmpty();
    }
}
