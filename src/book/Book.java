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
 
    

    public Book(String bookId, String Title, String author, String genre, int pubYear, int quantity){
        if (bookId == null || bookId.trim().isEmpty()){
            throw new IllegalArgumentException("Book ID cannot be null or empty");
        }
        if (Title == null || Title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("author cannot be null or empty");
        }
          if (genre == null || genre.trim().isEmpty()){
            throw new IllegalArgumentException("genre cannot be null or empty");
        }
          if (pubYear > 2026 || pubYear <= 0){
            throw new IllegalArgumentException("Invalid publication year");
        }
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
        if (bookId == null || bookId.trim().isEmpty()){
            throw new IllegalArgumentException("Book ID cannot be null or empty");
    }
        this.bookId = bookId;   
    }
    public String getTitle(){
        return Title;
    }
    public void setTitle(String Title){
        if (Title == null || Title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
    }
        this.Title = Title;
    }
    public String getauthor(){
        return author;
    }
    public void setauthor(String author){
        if (author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("author ID cannot be null or empty");
    }
        this.author = author;
    }
    public String getgenre(){
        return genre;
    }
    public void setgenre(String genre){
        if (genre == null || genre.trim().isEmpty()){
            throw new IllegalArgumentException("genre cannot be null or empty");
    }
        this.genre = genre;
    }
    public int getpubYear(){
        return pubYear;
    }
    public void setpubYear(int pubYear){
        if (pubYear > 2026 || pubYear <= 0){
            throw new IllegalArgumentException("Invalid publication year");
    }
        this.pubYear = pubYear;
    }
    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        if(this.quantity>=0){
            this.quantity = quantity;
        }else{
            System.out.println("Quantity cannot be negative");
        }
    }
  
}
