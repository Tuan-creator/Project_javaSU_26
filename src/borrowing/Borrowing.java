package borrowing;

import java.time.LocalDate;
import static util.InputHelper.email;
import util.ValidationBorrow;
import util.ValidationMember;

public class Borrowing {
    private String transactionId;
    private String memberId;
    private String bookId;
    private String memberName;
    private String email;
    private String phone;
    private boolean returned;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    
    
    
    public Borrowing()
    {
        
    }

    public Borrowing(String transactionId, String memberId, String bookId, LocalDate borrowDate, LocalDate dueDate,String phone,String email,String memberName) {
        
      
        setTransactionId(transactionId);
        setMemberId(memberId);
        setBookId(bookId);
        setBorrowDate(borrowDate);
        setDueDate(dueDate);
        setmemberName(memberName);
        setEmail(email);
        setPhone(phone);
        
        
       
        this.returnDate = null;
        this.returned = false;
    }

    




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) 
             
    {
       
        if (!ValidationMember.isValidEmail(email))
            {
                throw new IllegalArgumentException("Name is not correct!");
                
            }
        this.email=email;
            
      
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!ValidationMember.isValidPhone(phone))
            {
                throw new IllegalArgumentException("Phone is not correct!");
            }
        this.phone = phone;
    }

    public String getmemberName()
            {
                return memberName;
            }
    
    public void setmemberName(String memberName)
            {
                memberName=ValidationMember.format(memberName);
                if (!ValidationMember.isValidName(memberName))
                    {
                        throw new IllegalArgumentException("Name is not correct!");
                        
                    }
                this.memberName=memberName;
            }
 
    
      // kiem tra transactionId
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        transactionId=ValidationBorrow.formatTransactionId(transactionId);
        if (!ValidationBorrow.isValidTransactionId(transactionId))
        {
            throw new IllegalArgumentException("ID is incorrect");
        }
        this.transactionId = transactionId;
    }
    
    
    
    // kiem tra memberId
    public String getMemberId() {
        return memberId;
    }
       public void setMemberId(String memberId) {
           memberId=ValidationBorrow.formatMemberId(memberId);
        if (!ValidationBorrow.isValidMemberId(memberId)) {
            throw new IllegalArgumentException("Member ID is incorrect!");
        }
        this.memberId = memberId;
    }
    
    
    
    // kiem tra bookId
    public String getBookId() {
        return bookId;
    }
    
    public void setBookId(String bookId) {
        bookId=ValidationBorrow.formatBookId(bookId);
        if (!ValidationBorrow.isValidBookId(bookId)) {
            throw new IllegalArgumentException("Book ID is incorrect!");
        }
        this.bookId = bookId;
    }
    
    
    
  
    
    // kiem tra ngay muon sach
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
         if (!ValidationBorrow.isValidBorrowDate(borrowDate)) {
        throw new IllegalArgumentException("Borrow date is incorrect!");
    }
    this.borrowDate = borrowDate;
    }
    
    
    
    
   // kiem tra han ngay tra sach
    public LocalDate getDueDate() {
        return dueDate;
    }

   public void setDueDate(LocalDate dueDate) {
    if (!ValidationBorrow.isValidDueDate(this.borrowDate, dueDate)) {
        throw new IllegalArgumentException("Due date is incorrect!");
    }
    this.dueDate = dueDate;
     }
   
   
   
    
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        
        if (!ValidationBorrow.isValidReturnDate(this.borrowDate, returnDate))
        { 
            
            throw new IllegalArgumentException("Return date is incorrect!");
            
        }
        this.returnDate = returnDate;
        this.returned=true;
    }
    

   

    
    // kiem tra da tra sach hay chua
 
 public boolean isReturned() {
        return returned;
    }
    

    private void setReturned(boolean returned) {
        this.returned = returned;
    }
    
    
    
    

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Returned: " + returned);
    }
    
    
    
    
    
    
    @Override
public String toString() {
    return String.format(
            "========== BORROWING RECEIPT ==========\n" +
            "Transaction ID : %s\n" +
            "Member ID      : %s\n" +
            "Book ID        : %s\n" +
            "Borrow Date    : %s\n" +
            "Due Date       : %s\n" +
            "Return Date    : %s\n" +
            "Status         : %s\n" +
            "=======================================",
            transactionId,
            memberId,
            bookId,
            borrowDate,
            dueDate,
            returnDate == null ? "Not returned yet" : returnDate,
            returned ? "Returned" : "Borrowing"
              );
}

    
    
    
}
