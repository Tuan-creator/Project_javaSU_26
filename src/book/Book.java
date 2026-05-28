
package book;


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
        if (bookId == null || bookId.trim().isEmpty()){
            throw new IllegalArgumentException("Book ID cannot be null or empty");
    }
        
       if(!bookId.matches("B[0-9]+$"))
       {
           throw new IllegalArgumentException("Book ID must be in format Bxxxx...");
       }
        this.bookId = bookId;   
    }
    
    
    
     
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
    }
        this.title = title;
    }
    
    
    
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        if (author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author cannot be null or empty");
    }
       if (!author.matches("^[a-zA-Z .]+$"))
       {
           throw new IllegalArgumentException("Name of Author is incorrect");
       }
        this.author = author;
    }
    
    
    
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        if (genre == null || genre.trim().isEmpty()){
            throw new IllegalArgumentException("Genre cannot be null or empty");
    }
        if (!genre.matches("^[a-zA-Z ]$"))
        {
             throw new IllegalArgumentException("Genre is incorrect");
        }
        this.genre = genre;
    }
    
    
    
    public int getPubYear(){
        return pubYear;
    }
    public void setPubYear(int pubYear){
        if (pubYear > 2026 || pubYear <= 0){
            throw new IllegalArgumentException("Invalid publication year");
    }
        this.pubYear = pubYear;
    }
    
    
    
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
            if(quantity < 0){
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
