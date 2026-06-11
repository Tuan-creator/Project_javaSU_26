package manage_libarary_system;

import java.util.ArrayList;
import java.util.Scanner;
import member.Member;
import member.RegularMember;
import member.PremiumMember; 
import util.InputHelper;

public class Manage_Member {

    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Member> memberList = new ArrayList<>();

    public static void showMenu() {
        int choice = 0;
        do {
            System.out.println("\n===== MEMBER MANAGEMENT =====");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Remove Member");
            System.out.println("4. View All Members");
            System.out.println("5. Search Member");
            System.out.println("6. Back to Main Menu");

            choice = InputHelper.inputInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    updateMember();
                    break;
                case 3:
                    removeMember();
                    break;
                case 4:
                    viewAllMember();
                    break;
                case 5:
                    searchMember();
                    break;
                case 6:
                    System.out.println("Back to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 1 to 6.");
            }
        } while (choice != 6);
    }

    //1.add member
    public static void addMember() {
        System.out.println("\n--- Add Member ---");

        String id = InputHelper.memId();

        if (findMemberById(id) != null) {
            System.out.println("Error: Member ID already exists!");
            return;
        }
        
        String name = InputHelper.Name();
        String phone = InputHelper.phone();
        String email = InputHelper.email();

        System.out.println("Select Member Type: 1. Regular Member | 2. Premium Member");
        int type = InputHelper.inputInt("Your choice: ");

        try {
            Member newMem;
            if (type == 2) {
                newMem = new PremiumMember(id, name, phone, email); 
            } else {
                newMem = new RegularMember(id, name, phone, email);
            }
            
            memberList.add(newMem);
            System.out.println("Add Member Successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Action canceled.");
        }
    }

    //2.update member
    public static void updateMember() {
        System.out.println("\n--- Update Member ---");
        System.out.print("Enter Member ID to update: ");
        String id=InputHelper.memId();

        Member m = findMemberById(id);
        if (m == null) {
            System.out.println("Member ID not found!");
            return;
        }

        System.out.println("(Press ENTER to skip updating a field)");

        try {
            System.out.print("New Name: ");
            String newName = sc.nextLine().trim();
            if (!newName.isEmpty()) {
                m.setName(newName);
            }

            System.out.print("New Phone Number: ");
            String newPhone = sc.nextLine().trim();
            if (!newPhone.isEmpty()) {
                m.setPhone(newPhone);
            }

            System.out.print("New Email: ");
            String newEmail = sc.nextLine().trim();
            if (!newEmail.isEmpty()) {
                m.setEmail(newEmail);
            }

            System.out.println("Update Member Successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " Update canceled.");
        }
    }

    //3.remove member
    public static void removeMember() {
        System.out.println("\n--- Remove Member ---");
        System.out.print("Enter Member ID to remove: ");
        String id=InputHelper.memId();
        

        Member m = findMemberById(id);
        if (m == null) {
            System.out.println("Member ID not found!");
            return;
        }
        
        int borrowedCount=Borrowing_Returning.countCurrentyBorrwed(id);
        
        if (borrowedCount>0)
        {
           System.out.println("Cannot remove this member!");
           System.out.println("This member still has borrowed books: " + borrowedCount);
           return;
        }
        
        memberList.remove(m);
        System.out.println("Remove Member Successfully!");
    }

    //4.view all member
    public static void viewAllMember() {
        System.out.println("\n--- View All Member ---");
        if (memberList.isEmpty()) {
            System.out.println("No members registered in the library.");
            return;
        }

        System.out.printf("%-8s | %-25s | %-15s | %-25s | %-12s | %-6s | %-8s | %-10s\n", 
                "ID", "Name", "Phone", "Email", "Type", "Limit","Borrowed", "Fine/1 Day");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        
        for (Member m : memberList) {
            int limitValue = 0;
            String memberType = "Unknown";
            
            if (m instanceof RegularMember) {
                limitValue = ((RegularMember) m).getLimit();
                memberType = "Regular";
            } else if (m instanceof PremiumMember) { 
                limitValue = ((PremiumMember) m).getLimit();
                memberType = "Premium";
            }
            
            double fineValue = m.calculateFine(1);
            int borrowedCount = Borrowing_Returning.countCurrentyBorrwed(m.getMemberId());
            
            
            System.out.printf("%-8s | %-25s | %-15s | %-25s | %-12s | %-6d | %-8d | %-10.1f\n", 
                    m.getMemberId(), m.getName(), m.getPhone(), m.getEmail(), memberType, limitValue,borrowedCount, fineValue);
        }
    }

    //5.search member
    public static void searchMember() {
        System.out.println("\n===== SEARCH MEMBER =====");
        System.out.println("1. Search by Member ID");
        System.out.println("2. Search by Name");

        int type = InputHelper.inputInt("Choose search criteria: ");

        System.out.print("Enter keyword: ");
        String key = sc.nextLine().trim().toLowerCase();

        System.out.printf("%-8s | %-25s | %-15s | %-25s | %-12s | %-6s | %-10s\n", 
                "ID", "Name", "Phone", "Email", "Type", "Limit", "Fine/1 Day");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        for (Member m : memberList) {
            boolean isMatched = false;
            if (type == 1 && m.getMemberId().toLowerCase().contains(key)) {
                isMatched = true;
            }
            if (type == 2 && m.getName().toLowerCase().contains(key)) {
                isMatched = true;
            }

            if (isMatched) {
                int limitValue = 0;
                String memberType = "Unknown";
                if (m instanceof RegularMember) {
                    limitValue = ((RegularMember) m).getLimit();
                    memberType = "Regular";
                } else if (m instanceof PremiumMember) { 
                    limitValue = ((PremiumMember) m).getLimit();
                    memberType = "Premium";
                }
                
                double fineValue = m.calculateFine(1);
                int borrowedCount = Borrowing_Returning.countCurrentyBorrwed(m.getMemberId());
                
                System.out.printf("%-8s | %-25s | %-15s | %-25s | %-12s | %-6d |%-8a | %-10.1f\n", 
                        m.getMemberId(), m.getName(), m.getPhone(), m.getEmail(), memberType, limitValue,borrowedCount, fineValue);
            }
        }
    }

    //Ham phu tro
    public static Member findMemberById(String id) {
        for (Member m : memberList) {
            if (m.getMemberId().equalsIgnoreCase(id.trim())) {
                return m;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        showMenu();
    }
}
