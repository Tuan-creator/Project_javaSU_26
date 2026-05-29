
package util;


public class ValidationMember {
    
    public static boolean isValidPhone(String phone)
    {
        return phone!=null && (phone.trim().matches("^[0-9]{10}$") || phone.trim().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$")
                || phone.trim().matches("^[0-9]{3}.[0-9]{3}.[0-9]{4}$"));
    }
    
    public static boolean isValidEmail(String email)
    {
        return email!=null && email.trim().matches("[a-zA-Z][a-zA-Z0-9_-]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$");
        
    }
    
    public static boolean isValidName(String name)
    {
        return name!=null && name.trim().matches("^[a-zA-Z ]+$");
    }
    
    
       
      public static boolean isValidMemberId(String memberId) {
        return memberId != null 
                && memberId.trim().matches("^M[0-9]{3}$");
    }
      
       public static String formatMemberId(String memberId)
     {
         return memberId.trim().toUpperCase();
     }
    
    public static String format(String name)
    {
         
        name=name.trim().toLowerCase();
        
        String[] words=name.split("\\s+");
        String result="";
        
       for (String word : words)
       {
           result+=word.substring(0,1).toUpperCase()+word.substring(1)+" ";
       }
        
       return result.trim();
    }
    
    
    
    
}
