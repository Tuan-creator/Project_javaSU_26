package member;

import util.ValidationMember;

public abstract class Member {
    private String memberId;
    private String name;
    private String phone;
    private String email;

    
    public Member()
    {
        
    }
    
    public Member(String memberId, String name, String phone, String email) {
       
        
        setMemberId(memberId);
        setName(name);
        setPhone(phone);
        setEmail(email);
        
        
        
       
    }
    
    

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        memberId=ValidationMember.formatMemberId(memberId);
        
        if (!ValidationMember.isValidMemberId(memberId)) {
            throw new IllegalArgumentException("Member ID is incorrect!");
        }
        this.memberId = memberId;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
          name=ValidationMember.format(name);
        if (!ValidationMember.isValidName(name)) {
            throw new IllegalArgumentException("Member name is incorrect!");
        }
        this.name = name;
    }
    
    
    

    
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        
        if (!ValidationMember.isValidPhone(phone))
        {
             throw new IllegalArgumentException("Phone is incorrect!");
        }
        
        this.phone = phone;
    }
    
    
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!ValidationMember.isValidEmail(email))
        {
             throw new IllegalArgumentException("Email is incorrect!"); 
        }
        this.email = email;
    }

    
    
    
    public abstract double calculateFine(int daysOverdue);
    public abstract int getBorrowLimit();
}
