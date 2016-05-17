package objectmodelpackage;

import java.util.ArrayList;

/**
 * Created by deepaksood619 on 17/5/16.
 */

//encapsulation used

public class Books {
    private ArrayList<Book> books;
    private int numOfCopies;

    public Books() {
        books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public void addBookToList(Book book) {
        books.add(book);
    }
}
