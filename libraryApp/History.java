package libraryApp;

import java.time.*;

/**
 * history Class
 *
 * @author (3팀 강영미, 전유정, 최서연, 허다희)
 * @version ()
 */
public class History
{
    private String state; //대출or반납 상태
    private String borrowerName; //이용자 이름
    private String title; //책 제목
    private LocalDate date;
    
    public History(String state, Loan loan, LocalDate date){
        Book book = loan.getBook();
        Borrower borrower = loan.getBorrower();
        
        this.state = state;
        this.borrowerName = borrower.getName();
        this.title = book.getTitle();
        this.date = date;
    }
    
    public String getState(){
        return state;
    }
    
    public String getBorrowerName(){
        return borrowerName;
    }
    
    public String getTitle(){
        return title;
    }
    
    public LocalDate getDate(){
        return date;
    }
}
