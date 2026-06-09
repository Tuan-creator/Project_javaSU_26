package ui;

import manage_libarary_system.Manage_Member;
// Nếu sau này cần gọi thêm Manage_Book hay Borrowing_Returning thì import thêm ở đây
import util.InputHelper; 

public class Menu {

    public static void main(String[] args) {
        int mainChoice = 0;
        
        do {
            System.out.println("====== LIBRARY MANAGEMENT SYSTEM MAIN MENU ======");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members (Chức năng của mày)");
            System.out.println("3. Manage Borrows & Returns");
            System.out.println("4. Exit System");
            System.out.println("-------------------------------------------------");
            
            mainChoice = InputHelper.inputInt("Choose management function (1-4): ");
            
            switch (mainChoice) {
                case 1:
                    System.out.println("\n--- Opening Book Management ---");
                    // Đứa làm phần Book sau này sẽ gọi hàm Menu của nó ở đây
                    // Ví dụ: manage_libarary_system.Manage_Book.showMenu();
                    break;
                    
                case 2:
                    Manage_Member.showMenu(); 
                    break;
                    
                case 3:
                    System.out.println("\n--- Opening Borrow & Return Management ---");
                    // Đứa làm phần Mượn/Trả sách sau này sẽ gọi hàm ở đây
                    break;
                    
                case 4:
                    System.out.println("\nSaving data and shutting down system... Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please input a number from 1 to 4.");
            }
            
        } while (mainChoice != 4);
    }
}
