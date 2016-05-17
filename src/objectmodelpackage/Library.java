package objectmodelpackage;

import java.util.ArrayList;

/**
 * Created by deepaksood619 on 17/5/16.
 */
public class Library extends Institute{
    private ArrayList<Books> booksArrayList;

    private int forUG;
    private int forPG;
    private int forPHD;
    private int maxDays;

    public Library(String instituteName) {
        super(instituteName);
        booksArrayList = new ArrayList<>();

        if(instituteName.equals("IIITD")) {
            forUG = 5;
            forPG = 3;
            forPHD = 2;
            maxDays = 12;
        } else if(instituteName.equals("DTU")) {
            forUG = 4;
            forPG = 3;
            forPHD = 2;
            maxDays = 10;
        }
    }

    public ArrayList<Books> getBooksArrayList() {
        return booksArrayList;
    }

    public void setBooksArrayList(ArrayList<Books> booksArrayList) {
        this.booksArrayList = booksArrayList;
    }

    public void addBookToLibrary(Books books) {
        booksArrayList.add(books);
    }

    public Books findBooksCollection(String findThisBook) {
        for(Books books : booksArrayList) {
            for(Book book : books.getBooks()) {
                if(book.getBookName().equals(findThisBook)) {
                    return books;
                }
            }
        }
        return null;
    }

    public RulesResultSet iiitdRules(String instituteName, String programEnrolledIn) {
        if(instituteName.equals("IIITD")) {
            switch (programEnrolledIn) {
                case "UG":
                    return new RulesResultSet(forUG, maxDays);
                case "PG":
                    return new RulesResultSet(forPG, maxDays);
                case "PHD":
                    return new RulesResultSet(forPHD, maxDays);
            }
        } else if(instituteName.equals("DTU")) {
            return new RulesResultSet(2, 4);
        } else if(instituteName.equals("OTHER")) {
            return null;
        }
        return null;
    }

    public RulesResultSet dtuRules(String instituteName, String programEnrolledIn) {
        if(instituteName.equals("IIITD")) {
            return new RulesResultSet(3, 5);
        } else if(instituteName.equals("DTU")) {
            switch (programEnrolledIn) {
                case "UG":
                    return new RulesResultSet(forUG, maxDays);
                case "PG":
                    return new RulesResultSet(forPG, maxDays);
                case "PHD":
                    return new RulesResultSet(forPHD, maxDays);
            }
        } else if(instituteName.equals("OTHER")) {
            return new RulesResultSet(1, 3);
        }
        return null;
    }

}
