package libraryApp;

import libraryApp.Loan;
import java.util.*;

/**
 * Book Class
 *
 * @author (3팀 강영미, 전유정, 최서연, 허다희)
 * @version ()
 */
public class Book {
    private String title;
    private ArrayList<String> authors = new ArrayList<String>();
    private int catalogueNumber;
    private Loan loan = null;
    private ArrayList<String> reservationArray = new ArrayList<String>(); // 예약자 리스트
    
    /**
     * Book Constructor 
     * @param title
     * @param author
     * @param catalogueNumber
     */
    public Book(String title, String[] authors, int catalogueNumber) {
        this.title = title;
        Collections.addAll(this.authors, authors);
        this.catalogueNumber = catalogueNumber;
    }
    
    /**
     * SQ3 대출가능한 책들을 화면출력한다 - checkBookLoan(catalogueNumber: int) 에서 호출되는 getLoanState() 메소드 
     * @return 해당 book 객체에 연결되어 있는 loan 객체
     */
    public Loan getLoanState() {
        return loan;
    }
    
    /**
     * SQ5 책을 대출한다 - 1.6: attachLoan(loan: Loan)
     * @param loan
     */
    public void attachLoan(Loan loan) {
        this.loan = loan;
    }
    
    /**
     * SQ6 책을 반납한다 - detach()
     */
    public void detachLoan() {
        Borrower borrower = loan.detachBorrower();
        borrower.detachLoan(loan);
        loan = null;
    }
    
    /**
     * Book 클래스의 속성 reservation을 리턴하는 메소드
     * @return reservation
     */
    public ArrayList<String> getReservationArray() {
        return reservationArray;
    }
    
    /**
     * 예약자의 이름을 예약리스트에 추가하는 메소드
     */
    public void bookReservation(String name) {
        reservationArray.add(name);
    }
    
    /**
     * 도서의 제목을 리턴하는 메소드
     * @return title
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * SQ3 대출가능한 책들을 화면출력한다
     */
    @Override
    public String toString() {
        String authors = "";
        for(int i=0; i<this.authors.size(); i++){
            authors += this.authors.get(i);
            if(i != this.authors.size()-1){
                authors += ",";
            }
        }
        String message = " 제목: " + this.title + 
                         "\n   저자: " + authors + 
                         "\n   고유번호: " + this.catalogueNumber;
        if(getLoanState() != null){ //해당도서가 대출중일 경우
            Borrower borrower = getLoanState().getBorrower();
            message += "\n   대출자: " + borrower.getName();
            if(reservationArray.size() >= 1){ //예약자가 있을 경우
                message += "\n   예약자: ";
                for(int i=0; i<reservationArray.size(); i++){
                    message += reservationArray.get(i);
                    if(i != reservationArray.size()-1)
                        message += ", ";
                }
            }
        }
        return message;
    }
}
