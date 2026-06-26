
package manage_libarary_system;

import member.Member;


public interface IMemberManager {
    void addMember();
    void updateMember();
    void removeMember();
    void viewAllMember();
    void searchMember();
    void sortMemberById();
    void sortMemberByBorrowedCount();
    void sortMemberByFineValue();
    

}
