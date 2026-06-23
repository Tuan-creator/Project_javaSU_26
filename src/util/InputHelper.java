
package util;

import book.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class InputHelper {
    private static final Scanner n= new Scanner(System.in);
    private static final DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
   
    
    // 2 ham co ba
     public static String inputString(String message) {
        while (true) {
            System.out.print(message);
            String input = n.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty. Please try again!");
        }
    }

    public static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(n.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer!");
            }
        }
    }
    
    //// Book
    public static String BookId()
    {
        while(true)
        {
             System.out.print("Nhap vao BookId: ");
        String id = n.nextLine();

        id = ValidationBook.formatBookId(id);

        if (ValidationBook.isValidBookId(id)) {
            return id;
        }

        System.out.println("BookId khong hop le. Vi du: B001, nhap lai!");
        }
    }
    
    public static String Title()
    {
        while(true)
        {
            System.out.println("Nhap vao Title: ");
            String title=n.nextLine();
            
            if (ValidationBook.isValidTitle(title))
            {
                return title;
            }
            System.out.println("Title khong hop le, nhap lai!");
            
        }
    }
    
    public static String Author()
    {
        while(true)
        {
            System.out.println("Nhap vao Author: ");
            String author=n.nextLine();
            
            author=ValidationBook.formatName(author);
            if (ValidationBook.isValidAuthor(author))
            {
                return author;
            }
            
            System.out.println("Author khong hop le, nhap lai!");
        }
    }
    
   public static String Genre()
   {
       while(true)
       {
           System.out.println("Nhap vao Genre: ");
           String genre=n.nextLine();
           
           genre=ValidationBook.formatName(genre);
           if (ValidationBook.isValidGenre(genre))
           {
               return genre;
           }
           
           System.out.println("Genre khong hop le, nhap lai!");
       }
   }
   
   public static int Year()
   {
       while (true)
       {
           try
           {
                     System.out.println("Nhap vao public year");
                      String input=n.nextLine();
                      int year = Integer.parseInt(input);
                      
       
                      if (ValidationBook.isValidPubYear(year))
                      {
                          return year;
                      }else {
                          System.out.println("Nam xuat ban khong hop le (phai lon hon 0 va nho hon hoac bang 2026). Nhap lai!");
                      }
            
           } catch (NumberFormatException e)
           {
               System.out.println("Nhap sai, nhap lai!");
           }
       
   }
   }
       
   
   
    public static int Quantity()
    {
        while(true)
        {
            try
            {
                System.out.println("Nhap vao quantity: ");
                String input=n.nextLine();
                int quantity= Integer.parseInt(input);
                
                if (ValidationBook.isValidQuantity(quantity))
                {
                    return quantity;
                }else {
                    System.out.println("Quantity can be negative");
            } 
            }
            catch(Exception e)
            {
                System.out.println("Nhap sai, nhap lai!");
            }
        }
    }
    
    
    
    
    
    
    //// Borrow book
    public static String tranId()
    {
        while(true)
        {
            System.out.println("Nhap vao the muon: ");
            String tranid=n.nextLine();
            
            tranid=ValidationBorrow.formatTransactionId(tranid);
            if (ValidationBorrow.isValidTransactionId(tranid))
            {
               return tranid;
            }
            
            System.out.println("Nhap sai, nhap lai di!");
        }
    }
      
    
    public static String memId()
    {
        while(true)
        {
            System.out.println("Nhap vao member Id: ");
            String memid=n.nextLine();
            
            memid=ValidationBorrow.formatMemberId(memid);
            if (ValidationBorrow.isValidMemberId(memid))
            {
                return memid;
            }
            
            System.out.println("Nhap sai, nhap lai di!");
        }
    }
    
    
    public static LocalDate borrowDate()
    {
           while (true) {

        System.out.print("Nhap ngay muon (dd/MM/yyyy): ");
         String input = n.nextLine().trim();

        if (!ValidationBorrow.isValidDateFormat(input)) {
            System.out.println("Sai dinh dang ngay!");
            continue;
        }

        LocalDate borrowDate =LocalDate.parse(input, dateformat);

        if (ValidationBorrow.isValidBorrowDate(borrowDate)) {
            return borrowDate;
        }

        System.out.println(
                "Ngay muon khong duoc lon hon ngay hien tai, nhap lai di!");
    }
    }
    
    public static LocalDate dueDate(LocalDate borrowDate)
    {
        while(true)
        {
            System.out.println("Nhap vao ngay het han (dd/MM/yyyy): ");
             String input =n.nextLine().trim();
            Book b= new Book();
            
            if (!ValidationBorrow.isValidDateFormat(input))
            {
                System.out.println("sai dinh dang ngay");
                continue;
            }
            
            LocalDate dueDate =LocalDate.parse(input,dateformat);
            if (dueDate.getYear()<b.getPubYear())
            {
                System.out.println("Due year can not smaller than publicYear");
                continue;
                
            }
            if (ValidationBorrow.isValidDueDate(borrowDate, dueDate))
            {
                return dueDate;
            }
            System.out.println("Nhap lai di:");
        }
        
                
    }
    
    public static LocalDate returnDate(LocalDate borrowDate)
    {
       while(true)
       {
              System.out.print("Nhap ngay tra sach (dd/MM/yyyy): ");
        String input = n.nextLine().trim();
         Book b= new Book();
         

        if (!ValidationBorrow.isValidDateFormat(input)) {
            System.out.println("Sai dinh dang ngay!");
            continue;
        }

        LocalDate returnDate = LocalDate.parse(input, dateformat);
        if (returnDate.getYear()<b.getPubYear())
        {
            System.out.println("Return year can not smaller han pubYear");
        }

        if (ValidationBorrow.isValidReturnDate(borrowDate, returnDate)) {
            return returnDate;
        }

        System.out.println("Ngay tra phai sau ngay muon, nhap lai di!");
       }
    }
    
    
    
    //// Member
    
   public static String Name()
   {
       while(true)
       {
       System.out.println("Nhap vao ten cua sinh vien di");
       String name=n.nextLine();
       
      name=ValidationMember.format(name);
      if (ValidationMember.isValidName(name))
      {
          return name;
      }
      
       System.out.println("Ten khong hop le, nhap lai:");
   }
   }
   
   
   public static String phone()
   {
       while(true)
       {
           System.out.println("Nhap vao so dien thoai cua sinh vien di: ");
           String phone=n.nextLine();
           
          if (ValidationMember.isValidPhone(phone))
          {
              return phone;
          }
           System.out.println("Phone khong hop le, nhap lai");
       }
   }
   
   
   public static String email()
   {
       while(true)
       {
           System.out.println("Nhap vao email cua sinh vien di: ");
           String email=n.nextLine();
           
          if (ValidationMember.isValidEmail(email))
          {
              return email;
          }
           System.out.println("Email khong hop le,  nhap lai!");
       }
   }
   
}
       
       
       
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


