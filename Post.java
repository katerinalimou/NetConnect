import java.util.Date;

public class Post {
	
	 // Ημερομηνία και ώρα δημιουργίας του post
    private Date timestamp;

    private String text;
    private User author;

    public Post(String text, User author) {
        this.timestamp = new Date();
        this.text = text;
        this.author = author;
    }

    // Επιστρέφει την ημερομηνία & ώρα του post
    public Date getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

}
