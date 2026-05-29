
package util;

public class ValidationBook {
    
    // kiem tra book co format : Bxxx
    public static boolean isValidBookId(String bookId)
    {
        return bookId != null && bookId.trim().matches("^B[0-9]{3}$");
    }
    
    
    
    // kiem tra title 
    public static boolean isValidTitle(String title) {
    return title != null && title.trim().matches("^[a-zA-ZÀ-ỹ0-9 .,'-]+$");
    }
    
    
    
    // kiem tra ten tac gia
     public static boolean isValidAuthor(String author) {
        return author != null && author.trim().matches("^[a-zA-Z .]+$");
    }
     
     
     // kiem tra the loai
     public static boolean isValidGenre(String genre)
     {
         return genre !=null && genre.trim().matches("^[a-zA-Z ]+$");
     }
     
     
     // kiem tra year
      public static boolean isValidPubYear(int pubYear) {
        return pubYear > 0 && pubYear <= 2026;
    }
     
     
      
      // kiem tra so luong
     public static boolean isValidQuantity(int quantity)
     {
         return quantity>=0;
     }
     
     
     
     // dinh dang bookid vd: b001->B001
     public static String formatBookId(String bookId)
     {
         return bookId.trim().toUpperCase();
     }
     
     
     // dinh dang name cua author vd: nguyen NHAT ANH -> Nguyen Nhat Anh
     public static String formatName(String name)
     {
         name=name.trim().toLowerCase();
         
         String[] words =name.split("\\s+");
         String result="";
         
         for (String word: words)
         {
             result+= word.substring(0,1).toUpperCase()+word.substring(1)+" ";
         }
         
             
        return result.trim();
     }
}
