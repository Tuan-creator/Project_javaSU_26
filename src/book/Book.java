/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book;

/**
 *
 * @author TRINH HUNG TUAN
 */
public class Book {
    private String bookId;
    private String Title;
    private String author;
    private String genre;
    private int pubYear;
    private int quantity;
 
    
    public Book()
    {
        
    }

    public Book(String bookId, String Title, String author, String genre, int pubYear, int quantity){
        this.bookId = bookId;
        this.Title = Title;
        this.author = author;
        this.genre = genre;
        this.pubYear = pubYear;
        this.quantity = quantity;
       
    }
    public String getbookId(){
        return bookId;
    }
    public void setbookId(String bookId){
        this.bookId = bookId;   
    }
    public String getTitle(){
        return Title;
    }
    public void setTitle(String Title){
        this.Title = Title;
    }
    public String getauthor(){
        return author;
    }
    public void setauthor(String author){
        this.author = author;
    }
    public String getgenre(){
        return genre;
    }
    public void setgenre(String genre){
        this.genre = genre;
    }
    public int getpubYear(){
        return pubYear;
    }
    public void setpubYear(int pubYear){
        this.pubYear = pubYear;
    }
    public int getquantity(){
        return quantity;
    }
    public void setquantity(){
        if(this.quantity>=0){
            this.quantity = quantity;
        }else{
            System.out.println("Quantity cannot be negative");
        }
    }
  
}
