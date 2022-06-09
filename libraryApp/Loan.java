package libraryApp;

import libraryApp.Book;
import libraryApp.Borrower;
import libraryApp.Loan;

/**
 * Loan Class
 *
 * @author (3팀 강영미, 전유정, 최서연, 허다희)
 * @version ()
 */
public class Loan {
    private Book book;
    private Borrower borrower;
    
    /**
     * Loan Constructor 
     * @param book
     * @param borrower
     */
    public Loan(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
    }
    
    /**
     * SQ6 책을 반납한다 - detachBorrower()
     */
    public Borrower detachBorrower() {
        return borrower;
        // 일단 loan객체의 속성은 건들지 않는다. (대출기록을 위해서?)
    }
    
    /**
     * Loan 클래스의 속성 borrower를 리턴하는 메소드
     * @return borrower
     */
    public Borrower getBorrower() {
        return borrower;
    }
    
    /**
     * Loan 클래스의 속성 book을 리턴하는 메소드
     * @return book
     */
    public Book getBook() {
        return book;
    }
}