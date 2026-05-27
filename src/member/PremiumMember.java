package member;

public class PremiumMember extends Member {
    private int limit;

    public PremiumMember(String memberId, String name, String phone, String email) {
        super(memberId, name, phone, email);
        this.limit = 5; 
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
    public double calculateFine(int daysOverdue) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Days overdue cannot be negative!");
        }
        return daysOverdue * 2000.0; 
    }
}
