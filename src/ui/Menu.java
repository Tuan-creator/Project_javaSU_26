package ui;


import manage_libarary_system.Borrowing_Returning;
import manage_libarary_system.Manage_Book;
import manage_libarary_system.Manage_Member;
import util.FileHandler;
import util.InputHelper; 

public class Menu {

    public static void main(String[] args) {
        FileHandler file=new FileHandler();
        Manage_Book bookManager = new Manage_Book();
        Manage_Member memberManager = new Manage_Member();

       Borrowing_Returning borrowingManager = new Borrowing_Returning(bookManager, memberManager);
        
       bookManager.setBorrowingManager(borrowingManager);
       memberManager.setBorrowingManager(borrowingManager);
                            
        
        file.readFileBook(bookManager.bookList);
        file.readFileMember(memberManager.memberList);
        file.readFileBorrowing(borrowingManager.listBorrow);
        
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
                    
                    bookManager.showMenu();
                    break;
                       
                    
                    
                case 2:
                     memberManager.showMenu();
                    break;
                    
                case 3:
                   
                      borrowingManager.showMenu();
                   break;
                    
                case 4:
                    file.writeToFileBook(bookManager.bookList);
                    file.writeToFileMember(memberManager.memberList);
                    file.writeToFileBorrowing(borrowingManager.listBorrow);
                    System.out.println("\nSaving data and shutting down system... Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please input a number from 1 to 4.");
            }
            
        } while (mainChoice != 4);
    }
}
