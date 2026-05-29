
package book;
import util.ValidationBook;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private int pubYear;
    private int quantity;
 
    
    
    // constructor
    public Book()
    {
        
    }

    public Book(String bookId, String title, String author, String genre, int pubYear, int quantity){
        
        setBookId(bookId);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setPubYear(pubYear);
        setQuantity(quantity);
       
    }
    
    
    
    // getter and setter
    public String getBookId(){
        return bookId;
    }
    
    public void setBookId(String bookId){
        bookId = ValidationBook.formatBookId(bookId);
        
        if (!ValidationBook.isValidBookId(bookId))
        {
            throw new IllegalArgumentException("Book ID must be in format Bxxx. Example: B001");
        }
        this.bookId = bookId;   
    }
    
    
    
     
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        
        
        if (!ValidationBook.isValidTitle(title))
        {
            throw new IllegalArgumentException("Title is invalidate");
        }
        
        this.title = title;
    }
    
    
    
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        author = ValidationBook.formatName(author);
         
        if (!ValidationBook.isValidAuthor(author))
        {
            throw new IllegalArgumentException("Name of author is incorrect");
        }
        
        this.author = author;
    }
    
    
    
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        
        genre= ValidationBook.formatName(genre);
        if (!ValidationBook.isValidGenre(genre))
        {
            throw new IllegalArgumentException("Genre is incorrect");
        }
        
        this.genre = genre;
    }
    
    
    
    public int getPubYear(){
        return pubYear;
    }
    public void setPubYear(int pubYear){
       if (!ValidationBook.isValidPubYear(pubYear))
       {
           throw new IllegalArgumentException("Year is incorrect");
       }
        this.pubYear = pubYear;
    }
    
    
    
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
            if(!ValidationBook.isValidQuantity(quantity)){
        throw new IllegalArgumentException("Quantity cannot be negative");
    }
    this.quantity = quantity;   
    }
    
    
    // method 
    public boolean isAvailable()  // check book is available or inavailable
    {
        return this.quantity > 0;
    }
    
     public void increaseQuantity() {
         this.quantity++;
    }
    
    public void decreaseQuantity() {
    if (this.quantity <= 0) {
        throw new IllegalArgumentException("Book is out of stock");
    }
    this.quantity--;
     }     

    
    // ham dinh dang formatName
    
    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author + " | " + genre + " | " + pubYear + " | " + quantity;
    }

  
}
