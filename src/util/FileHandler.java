
package util;

import book.Book;
import borrowing.Borrowing;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import member.Member;
import member.PremiumMember;
import member.RegularMember;


public class FileHandler {
   
    public void writeToFileBook(List<Book> list)
            {
                try{
                FileWriter fw= new FileWriter("Book.txt");
                BufferedWriter bw= new BufferedWriter(fw);
                for(Book b:list)
                {
                    bw.write(b.toString());
                    bw.newLine();
                }
                
                 bw.close();
                 fw.close();
                 
                } catch(Exception e)
                {
                     System.out.println("Can not write Book.txt: " + e.getMessage());
                }
                
            }
    
    public void writeToFileMember(List<Member>list)
    {
        try{
            FileWriter fw=new FileWriter("Member.txt");
            BufferedWriter bw= new BufferedWriter(fw);
            for (Member m:list)
            {
                bw.write(m.toString());
                bw.newLine();
            }
              bw.close();
              fw.close();
        }
        catch (Exception e)
        {
             System.out.println("Can not write Member.txt: " + e.getMessage());
        }
    }
    
    public void writeToFileBorrowing(List<Borrowing> list)
    {
        try{
            FileWriter fw=new FileWriter("Borrowing.txt");
            BufferedWriter bw= new BufferedWriter(fw);
            for (Borrowing b:list)
            {
                bw.write( b.getTransactionId() + " | " +
                b.getMemberId() + " | " +
                b.getBookId() + " | " +
                b.getMemberName() + " | " +
                b.getEmail() + " | " +
                b.getPhone() + " | " +
                b.getBorrowDate() + " | " +
                b.getDueDate() + " | " +
                (b.getReturnDate() == null ? "null" : b.getReturnDate()) + " | " +
                b.isReturned() + " | " +
                b.getFine());
                bw.newLine();
            }
              bw.close();
              fw.close();
        }
        catch (Exception e)
        {
             System.out.println("Can not write  Borrowing.txt: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
   public void readFileBook(List<Book>list)
   {
       
       try
       {
           File bookFile= new File("Book.txt");
           if(!bookFile.exists())
           {
              return;
           }
           FileReader fr= new FileReader("Book.txt");
           BufferedReader br= new BufferedReader(fr);
           String line="";
           while(true)
           {
               
               line=br.readLine();
               if (line==null)
               {
                   break;
               }
               if (line.trim().isEmpty()) {
                 continue;
                }
               String[] book=line.split("\\|");
               String bookId=book[0].trim();
               String title=book[1].trim();
               String author=book[2].trim();
               String genre=book[3].trim();
               int pubYear=Integer.parseInt(book[4].trim());
               int quantity=Integer.parseInt(book[5].trim());
               
               list.add(new Book(bookId,title,author,genre,pubYear,quantity));
               
               
           }

           br.close();
       } catch (Exception e)
       {
            System.out.println("Can not read Book.txt: " + e.getMessage());
       }
       
   }
   
   public void readFileMember(List<Member>list)
   {
       try
       {
           File memberFile= new File("Member.txt");
           if(!memberFile.exists())
           {
               return;
           }
           
           FileReader fr= new FileReader("Member.txt");
           BufferedReader br= new BufferedReader(fr);
           
           String line="";
           while(true)
                   {
                       line=br.readLine();
                       if (line ==null)
                       {
                           break;
                       }
                       if (line.trim().isEmpty()) {
                       continue;
                        }
                       
                       String[] member= line.split("\\|");
                       String memberId=member[0].trim();
                       String name= member[1].trim();
                       String phone= member[2].trim();
                       String email=member[3].trim();
                       String type= member[4].trim();
                       
                       if (type.equalsIgnoreCase("Premium")) {
                          list.add(new PremiumMember(memberId, name, phone, email));
                        } else if (type.equalsIgnoreCase("Regular")) {
                          list.add(new RegularMember(memberId, name, phone, email));
                        }
                       
                   }
            br.close();
       } catch(Exception e)
       {
//           System.out.println("Can not read Member.txt: "+e.getMessage());
           e.printStackTrace();
       }
   }
   
 
   public void readFileBorrowing(List<Borrowing>list)
   {
       try
       {
           File borrowingFile= new File("Borrowing.txt");
           if(!borrowingFile.exists())
           {
               return;
           }
           FileReader fr= new FileReader("Borrowing.txt");
           BufferedReader br= new BufferedReader(fr);
           
           String line="";
           while(true)
           {
               line=br.readLine();
               if (line==null)
               {
                   break;
               }
               if (line.trim().isEmpty())
               {
                   continue;
               }
               String[] borrowing=line.split("\\|");
               String transactionId=borrowing[0].trim();
               String memberId=borrowing[1].trim();
               String bookId=borrowing[2].trim();
               String memberName=borrowing[3].trim();
               String email=borrowing[4].trim();
               String phone =borrowing[5].trim();
               LocalDate borrowDate=LocalDate.parse(borrowing[6].trim());
               LocalDate dueDate=LocalDate.parse(borrowing[7].trim());
               
               String returndateText= borrowing[8].trim();
               
               LocalDate returndate= null;
               
               if(!returndateText.equalsIgnoreCase("null"))
               {
                   returndate=LocalDate.parse(borrowing[8].trim());
                   
               }
               
               
               boolean returned= Boolean.parseBoolean(borrowing[9].trim());
               double fine=Double.parseDouble(borrowing[10].trim());
           }
           
           br.close();
       } catch (Exception e)
       {
           System.out.println("Can not read Borrowing.txt: "+e.getMessage());
       }
   }
    
   
}
