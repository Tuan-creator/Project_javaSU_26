package ui;


import manage_libarary_system.Borrowing_Returning;
import manage_libarary_system.Manage_Book;
import manage_libarary_system.Manage_Member;
import util.InputHelper; 

public class Menu {

    public static void main(String[] args) {
        Borrowing_Returning Br=new Borrowing_Returning();
        Manage_Book Mb = new Manage_Book();
        Manage_Member Mm = new Manage_Member();
        
        
        
        
        int mainChoice = 0;
        
        do {
            System.out.println("====== LIBRARY MANAGEMENT SYSTEM MAIN MENU ======");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Manage Borrows & Returns");
            System.out.println("4. Exit System");
            System.out.println("-------------------------------------------------");
            
            mainChoice = InputHelper.inputInt("Choose management function (1-4): ");
            
            switch (mainChoice) {
                case 1:
                    
                    Manage_Book.showMenu();
                    break;
                       
                    
                    
                case 2:
                    Manage_Member.showMenu();
                    break;
                    
                case 3:
                   
                   Borrowing_Returning.showMenu();
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
