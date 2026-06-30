package member;

public class RegularMember extends Member {
    private int limit;
    
    public RegularMember()
    {
        
    }
    
    public RegularMember(String memberId, String name, String phone, String email) {
        super(memberId, name, phone, email);
        this.limit = 3; 
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Borrowing limit must be greater than 0!");
        }
        this.limit = limit;
    }
    
     
    @Override
    public int getBorrowLimit() {
    return limit;
     }
    

    @Override
    public double calculateFine(int daysOverdue) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Days overdue cannot be negative!");
        }
        return daysOverdue * 5000.0; 
    }
    
    @Override
public String toString() {
    return super.toString() + " | Regular";
}
}
