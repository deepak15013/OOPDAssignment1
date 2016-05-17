package objectmodelpackage;

import java.util.ArrayList;

/**
 * Created by deepaksood619 on 17/5/16.
 */
public class Books {
    ArrayList<Book> books;
    int numOfCopies;

    public Books() {
        books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
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
