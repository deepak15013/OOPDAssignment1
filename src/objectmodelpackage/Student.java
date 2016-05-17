package objectmodelpackage;

import java.util.ArrayList;

/**
 * Created by deepaksood619 on 17/5/16.
 */
public class Student extends Institute {
    private String studentName;
    private String studentId;
    private String programEnrolledIn;

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

    private void issueCard(String instituteName) {
        if(instituteName.equals("IIITD")) {
            iiitdLibraryCard = true;
        }
        if(instituteName.equals("DTU")) {
            dtuLibraryCard = true;
        }
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

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProgramEnrolledIn() {
        return programEnrolledIn;
    }

    public void setProgramEnrolledIn(String programEnrolledIn) {
        this.programEnrolledIn = programEnrolledIn;
    }

    public ArrayList<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(ArrayList<Book> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
}
