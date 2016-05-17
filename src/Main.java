import objectmodelpackage.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static ArrayList<Student> students;
    static Library iiitdLibrary;
    static Library dtuLibrary;

    public static void main(String[] args) {

        students = new ArrayList<>();

        iiitdLibrary = new Library("IIITD");
        dtuLibrary = new Library("DTU");

        addBooksToLibrary(iiitdLibrary, "The Alchemist", "Paulo Coelho");
        addBooksToLibrary(iiitdLibrary, "And Then There Were None", "Agatha Cristie");
        addBooksToLibrary(iiitdLibrary, "Harry Potter", "J. K. Rowling");
        addBooksToLibrary(iiitdLibrary, "Miss Marple", "Agatha Cristie");
        addBooksToLibrary(iiitdLibrary, "War and Peace", "Leo Tolstoy");
        addBooksToLibrary(dtuLibrary, "The Shining", "Stephen King");
        addBooksToLibrary(dtuLibrary, "The Stand", "Stephen King");
        addBooksToLibrary(dtuLibrary, "Espionage", "Robert Ludlem");
        addBooksToLibrary(dtuLibrary, "The Monk Who Sold his Ferrari", "Robin Sharma");
        addBooksToLibrary(dtuLibrary, "Who Will Cry when you die", "Robin Sharma");
        addBooksToLibrary(dtuLibrary, "GOT", "George RR Martin");

        addStudents(students);
        printStudentList(students);

        while(true) {

            Scanner reader = new Scanner(System.in);

            System.out.println("Welcome to Library Management System");
            System.out.println("1. Display all books");
            System.out.println("2. Issue Library Card");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. My Account");
            System.out.println("6. Exit");

            try {

                boolean nextInt = reader.hasNextInt();

                int option = 0;
                if(nextInt) {
                    option = reader.nextInt();
                }

                switch (option) {
                    case 1:
                        displayAllBooks(iiitdLibrary, dtuLibrary);
                        break;

                    case 2:
                        break;

                    case 3:
                        issueBook(iiitdLibrary, dtuLibrary);
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        System.exit(1);
                        break;

                    default:
                        System.out.println("Please enter a valid option");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid option");
                reader.close();
            }
        }
    }

    private static void issueBook(Library iiitdLibrary, Library dtuLibrary) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Book Name: ");
        String getBookName = reader.nextLine();
        String bookLocation;

        Book bookFound = checkBookAvailable(iiitdLibrary, getBookName);
        if(bookFound == null) {
            bookFound = checkBookAvailable(dtuLibrary, getBookName);
            if(bookFound != null) {
                System.out.println("Book Found in DTU library");
                bookLocation = "DTU";
            }
            else {
                System.out.println("Book Not Found");
                return;
            }
        }
        else {
            System.out.println("Book Found in IIITD library");
            bookLocation = "IIITD";
        }

        System.out.println("Enter your studentId: ");
        String borrowerStudentId = reader.nextLine();
        System.out.println(borrowerStudentId);
        Student student = findStudent(borrowerStudentId);

        if(student != null) {

            System.out.println("Student studies in "+student.getInstituteName());

            if(student.hasLibraryAccess(bookLocation)) {
                System.out.println("Access granted");
                System.out.println("loc: "+bookLocation);
                System.out.println("insName: "+student.getInstituteName());
                System.out.println("prog: "+student.getProgramEnrolledIn());
                RulesResultSet resultSet = maxBookStudentCanIssue(bookLocation, student.getInstituteName(), student.getProgramEnrolledIn());
                if(resultSet != null) {
                    if(student.getIssuedBooks().size() < resultSet.getNumOfBooks()) {
                        bookFound.setBorrowerName(student.getStudentName());

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar c = Calendar.getInstance();
                        c.setTime(new Date());
                        bookFound.setBorrowDate(sdf.format(c.getTime()));
                        c.add(Calendar.DATE, resultSet.getNumOfDays());
                        bookFound.setReturnDate(sdf.format(c.getTime()));

                        bookFound.setPendingReturn(true);

                        student.addBookToIssueList(bookFound);
                    }
                    else {
                        System.out.println("Max number of books issued");
                    }
                }
                else {
                    System.out.println("Cannot issue Book");
                }

            }
            else {
                System.out.println("You do not have access to library");
            }
        }
        else {
            System.out.println("Student not found");
        }


    }

    private static RulesResultSet maxBookStudentCanIssue(String bookLocation, String instituteName, String programEnrolledIn) {
        if(bookLocation.equals("IIITD")) {
            return iiitdLibrary.iiitdRules(instituteName, programEnrolledIn);
        }
        else if(bookLocation.equals("DTU")) {
            return dtuLibrary.dtuRules(instituteName, programEnrolledIn);
        }
        return null;
    }

    private static Student findStudent(String id) {
        for(Student student: students) {
            if(student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Book checkBookAvailable(Library library, String getBookName) {
        ArrayList<Books> booksArrayList = library.getBooksArrayList();
        for(Books books : booksArrayList) {
            for(Book book : books.getBooks()) {
                if(book.getBookName().equalsIgnoreCase(getBookName) && !book.isPendingReturn()) {
                    return book;
                }
            }
        }
        return null;
    }

    private static void displayAllBooks(Library iiitdLibrary, Library dtuLibrary) {
        ArrayList<Books> iiitdLibraryBooksArrayList = iiitdLibrary.getBooksArrayList();
        for(Books books : iiitdLibraryBooksArrayList) {
            for(Book book : books.getBooks()) {
                System.out.println("Name:"+book.getBookName() + "   Author:" + book.getBookAuthor() + "   Available: "+!book.isPendingReturn());

            }
        }
        ArrayList<Books> dtuLibraryBooksArrayList = dtuLibrary.getBooksArrayList();
        for(Books books : dtuLibraryBooksArrayList) {
            for(Book book : books.getBooks()) {
                System.out.println("Name:"+book.getBookName() + "   Author:" + book.getBookAuthor() + "   Available: "+!book.isPendingReturn());

            }
        }

    }

    private static void addBooksToLibrary(Library library, String bookName, String bookAuthor) {
        Book book = new Book(bookName, bookAuthor);
        book.setPendingReturn(false);
        Books books = new Books();
        books.setNumOfCopies(getRandomNumber(3)+1);

        for(int i=0; i<books.getNumOfCopies(); i++)
            books.addBookToList(book);

        library.addBookToLibrary(books);
    }


    private static void addStudents(ArrayList<Student> students) {
        String[] studentNames = {"Aartika Sethi", "AASHISH GROVER", "ANURADHA AHLAWAT", "Ashish Garg", "Avni Malhan", "Deepak Sood", "Dilraj Kaur", "Garima Chhikara", "KANIKA GAUTAM", "KARISHMA TIRTHANI", "Mohit Mishra", "Neha Rani", "Rajat Tripathy", "richa gupta", "Rajdeep Mukherjee"};
        String[] instituteNames = {"IIITD", "DTU", "OTHER"};
        String[] programNames = {"UG", "PG", "PHD"};

        for(int i=0;i<10;i++) {
            Student student = new Student(instituteNames[getRandomNumber(3)], studentNames[getRandomNumber(15)], String.valueOf(getRandomNumber(5000)), programNames[getRandomNumber(3)]);
            students.add(student);
        }
    }

    private static void printStudentList(ArrayList<Student> students) {
        for(Student student : students) {
            System.out.println(student.getInstituteName() + "\t" + student.getStudentName() + "\t" + student.getStudentId() + "\t" + student.getProgramEnrolledIn());
        }
    }

    private static int getRandomNumber(int maxNumber) {
        return (int) (Math.random() * maxNumber);
    }
}
