package libraryApp;

import libraryApp.Loan;
import java.util.*;

/**
 * Borrower Class
 *
 * @author (3팀 강영미, 전유정, 최서연, 허다희)
 * @version ()
 */
public class Borrower {
    private String name;
    private ArrayList<Loan> loanArray = new ArrayList<Loan>();
    
    /**
     * Borrower Constructor
     * @param name
     */
    public Borrower(String name) {
        this.name = name;
    }
    
    /**
     * SQ3 대출가능한 책들을 화면출력한다 - checkBorrowerLoan(name: String) 에서 호출되는 getLoanState() 메소드
     * @return loanArray의 크기
     */
    public int getLoanArraySize() {
        return loanArray.size();
    }
    
    /**
     * SQ5 책을 대출한다. - 1.8: attachLoan(loan: Loan)
     * @param loan
     */
    public void attachLoan(Loan loan) {
        loanArray.add(loan);
    }
    
    /**
     * SQ6 책을 반납한다 - detachLoan(loan: Loan)
     */
    public void detachLoan(Loan loan) {
        loanArray.remove(loan);
    }
    
    public String getName(){
        return name;
    }
}
