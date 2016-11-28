package faisal.home.com.college;

/**
 * Created by Home on 11/24/2016.
 */
public class Book {
    String bookName,stream;
    int available;

    public Book(String bookName, String stream,int available) {
        this.bookName = bookName;
        this.available = available;
        this.stream = stream;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
