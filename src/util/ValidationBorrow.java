
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ValidationBorrow {
    
    //Dinh dang ngay
    private static final DateTimeFormatter DateFormat=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
    public static boolean isValidDateFormat(String dateInput)
    {
        if (dateInput==null)
        {
            return false;
        }
         try {
        LocalDate.parse(dateInput.trim(), DateFormat);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
    }

    
    
        public static boolean isValidTransactionId(String transactionId) {
        return transactionId != null && transactionId.trim().matches("^P[0-9]{3}$");
    }
        
        
      public static boolean isValidMemberId(String memberId) {
        return memberId != null 
                && memberId.trim().matches("^M[0-9]{3}$");
    }
      
          public static boolean isValidBookId(String bookId) {
        return bookId != null 
                && bookId.trim().matches("^B[0-9]{3}$");
    }

          
     public static String formatBookId(String bookId)
     {
         return bookId.trim().toUpperCase();
     }
     
      public static String formatTransactionId(String transactionId)
     {
         return transactionId.trim().toUpperCase();
     }
     
       public static String formatMemberId(String memberId)
     {
         return memberId.trim().toUpperCase();
     }
       
       
       // kiem tra ngay muon sach co hop le khong
       public static boolean isValidBorrowDate(LocalDate borrowDate)
       {
           return borrowDate!=null && !borrowDate.isAfter(LocalDate.now());
       }
       
       
       
       // kiem tra ngay tra sach co hop le khong
       public static boolean isValidReturnDate(LocalDate borrowDate, LocalDate returnDate)
       {
           return borrowDate !=null && returnDate!=null && !returnDate.isBefore(borrowDate);
       }
       
       
        // kiem tra han tra sach co hop le khong
        public static boolean isValidDueDate(LocalDate borrowDate, LocalDate dueDate)
        {
           return borrowDate != null && dueDate != null && dueDate.isAfter(borrowDate);
        }
        
       
}
