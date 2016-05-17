package objectmodelpackage;

import java.util.ArrayList;

/**
 * Created by deepaksood619 on 17/5/16.
 */

// Inheritance and encapsulation used

public class Student extends Institute {
    private String studentName;
    private String studentId;
    private String programEnrolledIn;

    private int fine;

    private ArrayList<Book> issuedBooks;

    private boolean iiitdLibraryCard;
    private boolean dtuLibraryCard;

    public Student(String instituteName, String studentName, String studentId, String programEnrolledIn) {
        super(instituteName);
        this.studentName = studentName;
        this.studentId = studentId;
        this.programEnrolledIn = programEnrolledIn;

        this.iiitdLibraryCard = false;
        this.dtuLibraryCard = false;

        issueCard(instituteName);

        issuedBooks = new ArrayList<>();
    }

    public int issueCard(String instituteName) {
        if(instituteName.equals("IIITD")) {
            if(iiitdLibraryCard) {
                return 2;
            }
            iiitdLibraryCard = true;
            return 1;
        }
        if(instituteName.equals("DTU")) {
            if(dtuLibraryCard) {
                return 2;
            }
            dtuLibraryCard = true;
            return 1;
        }
        return 0;
    }

    public boolean hasLibraryAccess(String instituteName) {
        if(instituteName.equals("IIITD")) {
            return iiitdLibraryCard;
        }
        if(instituteName.equals("DTU")) {
            return dtuLibraryCard;
        }
        return false;
    }

    public void addBookToIssueList(Book book) {
        this.issuedBooks.add(book);
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getProgramEnrolledIn() {
        return programEnrolledIn;
    }

    public ArrayList<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
