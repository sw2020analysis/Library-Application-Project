    package libraryApp;
    
    import libraryApp.Book;
    import libraryApp.Borrower;
    import libraryApp.Loan;
    import libraryApp.History;
    import java.util.*;
    import java.time.*;
    
    /**
    * Library Class
    *
    * @author (3팀 강영미, 전유정, 최서연, 허다희)
    * @version ()
    */
    public class Library {
    private HashMap<String, Borrower> borrowerMap = new HashMap<String, Borrower>(); // key: name, value: Borrower Object
    private HashMap<Integer, Book> bookMap = new HashMap<Integer, Book>(); // key: catalogueNumber, value: Book Object
    private String libraryName;
    private int loanPossibleNumberRule = 5; //대출가능권수 -> 5권
    private ArrayList<History> historyArray = new ArrayList<History>(); //대출,반납 기록저장소
    
    /**
     * Library Constructor
     * @param name
     */
    public Library(String name) {
        this.libraryName = name;
    }
    
    /**
     * SQ1 새로운 이용자를 등록한다 - 1:registerOneBorrower(name:String)
     * @param name
     * @return 등록성공여부 메시지
     */
    public String registerOneBorrower(String name) {
        // state: true = 등록된 상태, false = 등록되지 않은 상태
        boolean state = checkBorrower(name);
        if(state == true) {
            return "이미 등록되어 있는 이용자입니다.";
        }
        else {
            Borrower b = new Borrower(name);
            addBorrower(name, b);
            return "등록이 완료되었습니다.";
        }
    }
    
    /**
     * SQ1 새로운 이용자를 등록한다 - 1.1: checkBorrower(name: String)
     * @param name
     * @return 이용자가 등록되어있는 경우 true, 등록되어 있지 않은 경우 false
     */
    public boolean checkBorrower(String name) {
        // return값: true = 등록된 상태, false = 등록되지 않은 상태
        if (borrowerMap.get(name) == null) { // borrowerMap에 이용자가 등록되어 있지 않은 경우
            return false;
        }
        else { // borrowerMap에 이용자가 등록되어 있는 경우
            return true;
        }
    }
    
    /**
     * SQ1 새로운 이용자를 등록한다 - 1.5: addBorrower(name: String, b: Borrower)
     * @param name
     * @param b
     */
    public void addBorrower(String name, Borrower b) {
        borrowerMap.put(name, b);
    }
    
    /**
     * SQ2 새로운 책을 등록한다 - 1 : registerOneBook(title: String, author: String, catalogueNumber: int)
     * @param title
     * @param author
     * @param catalogueNumber
     * @return 등록성공여부 메시지
     */
    public String registerOneBook(String title, String[] authors, int catalogueNumber) {
        // return값: true = 등록된 상태, false = 등록되지 않은 상태
        boolean state = checkBook(catalogueNumber);
        if(state == true) {
            return "이미 등록되어 있는 책입니다.";
        }
        else {
            Book b = new Book(title, authors, catalogueNumber);
            addBook(catalogueNumber, b);
            return "등록이 완료되었습니다.";
        }
    }
    
    /**
     * SQ2 새로운 책을 등록한다 - 1.1 checkBook(catalogueNumber: int)
     * @param catalogueNumber
     * @return 책이 등록되어 있는 경우 true, 등록되지 않은 경우 false
     */
    public boolean checkBook(int catalogueNumber) {
        // return값: true = 등록된 상태, false = 등록되지 않은 상태
        if (bookMap.get(catalogueNumber) == null) { //bookMap에 책이 등록되어 있지 않은 경우
            return false;
        }
        else { // bookMap에 책이 등록되어 있는 경우
            return true;
        }
    }
    
    /**
     * SQ2 새로운 책을 등록한다 - 1.5: addBook(catalogueNumber: int, b: Book)
     * @param catalogueNumber
     * @param b
     */
    public void addBook(int catalogueNumber, Book b) {
        bookMap.put(catalogueNumber, b);
    }
    
    /**
     * SQ3 대출가능한 책들을 화면출력한다 - 1: displayBooksForLoan()
     * @return 화면출력 완료메시지
     */
    public String displayBooksForLoan() { 
        // state: true = 대출가능한 상태, false = 대출불가능한 상태
        Set<Integer> keys = bookMap.keySet();
        Iterator<Integer> it = keys.iterator();
        int count = 0;
        while(it.hasNext()) {
            int catalogueNumber = it.next();
            Book book = bookMap.get(catalogueNumber);
            boolean state = checkBookLoan(catalogueNumber);
            if(state == true) { // 대출가능한 상태인 경우
                count += 1;
                displayBook(book, count);
            }
        }
        if(count == 0)
            return "대출가능한 책이 없습니다.";
        else
            return "\n대출가능한 책들이 모두 출력되었습니다.";
    }
    
    /**
     * SQ3 대출가능한 책들을 화면출력한다 & SQ4 대출중인 책들을 화면출력한다 & SQ5 책을 대출한다 - checkBookLoan(catalogueNumber: int)
     * @param catalogueNumber
     * @return 대출가능한 경우 true, 대출불가능한 경우 false
     */
    public boolean checkBookLoan(int catalogueNumber) {
        // return값: true = 대출가능한 상태(대출중이지 않은 상태), false = 대출불가능한 상태(대출중인 상태)
        if(!bookMap.containsKey(catalogueNumber)) {
            return false; // 대출불가능(고유번호로 등록된 책이 없음)
        }
        else {
            Book book = bookMap.get(catalogueNumber);
            if (book.getLoanState() == null) { // book이 대출되지 않은  경우
                return true; // 대출가능
            }
            else { // book 대출되어 있는 경우
                return false; // 대출불가능(대출중)
            }
        }
    }
    
    /**
     * SQ3 대출가능한 책들을 화면출력한다 - 1.4: displayBook(book, count)
     */
    public void displayBook(Book book, int count){
        System.out.println(count + ")" + book.toString());
    }
    
    /**
     * SQ4 대출중인 책들을 화면출력한다 - 1: displayBooksOnLoan()
     * @return 화면출력 완료메시지
     */
    public String displayBooksOnLoan() {
        // state: true = 대출중이지 않은 상태, false = 대출중인 상태
        Set<Integer> keys = bookMap.keySet();
        Iterator<Integer> it = keys.iterator();
        int count = 0;
        while(it.hasNext()) {
            int catalogueNumber = it.next();
            Book book = bookMap.get(catalogueNumber);
            boolean state = checkBookLoan(catalogueNumber);
            if(state == false) { // 대출중인 상태인 경우
                count += 1;
                displayBook(book, count);
            }
        }
        if(count == 0)
            return "대출중인 책이 없습니다.";
        else
            return "\n대출중인 책들이 모두 출력되었습니다.";
    }
    
    /**
     * SQ5 책을 대출한다 - 1. lendOneBook(name, catalogueNumber)
     * @param name
     * @param catalogueNumber
     * @return 대출성공여부 메시지
     */
    public String lendOneBook(String name, int catalogueNumber) {
        // bookState, borrowerState: true = 대출가능, false = 대출불가능
        Scanner scanner = new Scanner(System.in);
        boolean bookState = checkBookLoan(catalogueNumber);
        boolean borrowerState = checkBorrowerLoan(name);
        
        LocalDate toDate = LocalDate.now(); //컴퓨터의 현재 날짜
        
        if(bookState == true && borrowerState == true) {
            Book book = bookMap.get(catalogueNumber);
            Borrower borrower = borrowerMap.get(name);
            Loan loan = new Loan(book, borrower);
            attach(book, borrower, loan);
            History history = new History("대출", loan, toDate);//대출기록 객체 생성
            historyArray.add(history);//대출기록저장
            LocalDate returnScheduleDate = toDate.plusDays(7); //대출기간 7일기준 반납일
            int onLoanNumber = borrower.getLoanArraySize(); // 대출중인 권수
            int loanPossibleNumber = loanPossibleNumberRule - onLoanNumber; //대출가능권수기준 - 대출중인 권수
            return toDate + "에 대출이 완료되었습니다. " + "반납일은 "+ returnScheduleDate +"입니다.\n" +
                   "현재 " + onLoanNumber + "권 대출중입니다. 앞으로 " + loanPossibleNumber + "권 대출가능합니다.";
        } else if(bookState == true && borrowerState == false) { // 등록되지 않은 이용자인 경우
            return "등록되지 않은 이용자입니다.";
        } else if(bookState == false && borrowerState == true) { // 대출불가능한 책인 경우(미등록 or 대출중)
            Book book = getBookMap().get(catalogueNumber);
            if(book.getLoanState() != null){ // 대출중인 책일 경우
                System.out.println("이미 대출중인 책입니다.");
                Borrower borrower = getBorrowerMap().get(name); // 대출신청한 이용자 객체
                Loan loan = book.getLoanState(); // 대출신청한 책의 loan 객체
                Borrower b = loan.getBorrower(); // 대출신청한 책을 대출중인 이용자 객체
                if(b.getName() != borrower.getName()){
                    System.out.println("예약하시겠습니까?(Y/N)");
                    String input = scanner.next();
                    switch(input){
                        case "Y":
                        case "y":
                            book.bookReservation(name);
                            return "예약이 완료되었습니다. 현재 예약 대기는 " + book.getReservationArray().size() + "번 입니다.";
                        case "N":
                        case "n":
                            return "예약을 하지 않습니다.";
                        default:
                            return "잘못 입력하셨습니다.";
                    }
                }
            }
            //미등록한 책일 경우
            return "등록되지 않은 책입니다.";
        }
        else // 책도 이용자도 등록되지 않은 경우
            return "이용자와 책이 등록되지 않아서 대출이 불가능합니다.";
    }
    
    /**
     * SQ5 책을 대출한다 - 1.3: checkBorrowerLoan(name: String)
     * @param name
     * @return 대출가능일때 true, 대출불가능일때 false
     */
    public boolean checkBorrowerLoan(String name) {
        // return값: true = 대출가능한 상태(대출중이지 않은 상태), false = 대출불가능한 상태(대출중인 상태)
        Borrower borrower = borrowerMap.get(name);
        if(!borrowerMap.containsKey(name)) {
            return false; // 대출불가능(등록되지 않은 이용자)
        } else if (borrower.getLoanArraySize() < loanPossibleNumberRule) { // borrower가 대출한 책이 5권(임시) 이하인 경우
                return true; // 대출가능
        } else{ // borrower가 대출가능한 권수를 넘겼을 경우
            return false; // 대출불가능(대출중)
        }
    }
    
    /**
     * SQ5 책을 대출한다 - attach(book: Book, borrower: Borrower, loan: Loan)
     * @param book
     * @param borrower
     * @param loan
     */
    public void attach(Book book, Borrower borrower, Loan loan) {
        book.attachLoan(loan);
        borrower.attachLoan(loan);
    }
    
    /**
     * SQ6 책을 반납한다 - 1: returnOneBook(catalogueNumber: int)
     * @param catalogueNumber
     * @return 반납성공여부 메시지
     */
    public String returnOneBook(int catalogueNumber) {
        // state: true = 반납성공, false = 반납실패
        Book book = findBook(catalogueNumber);
        Loan loan = book.getLoanState();
        boolean state; //반납상태
        if(loan == null) //대출되지 않은 책일 경우
            return "대출되지 않은 책입니다.";
        else
            state = detach(book);
        
        LocalDate toDate = LocalDate.now();
        if(state == true) {
            History history = new History("반납", loan, toDate);//반납기록 객체 생성
            historyArray.add(history);//반납기록저장
            return toDate + "에 반납이 완료되었습니다.";
        }
        else {
            return "해당 도서관에 등록된 책이 아니거나, 잘못입력하셨습니다.";
        }
    }
    
    /**
     * SQ6 책을 반납한다 - 1.1: findBook(catalogueNumber: int)
     * @param catalogueNumber
     * @return catalogueNumber에 해당하는 book Object
     */
    public Book findBook(int catalogueNumber) {
        return bookMap.get(catalogueNumber);
    }
    
    /**
     * SQ6 책을 반납한다 - 1.1: detach(book: Book, catalogueNumber: int)
     * @param book
     * @param catalogueNumber
     * @return true
     */
    public boolean detach(Book book) {
        if(book != null){
            book.detachLoan();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * library의 속성 borrowerMap을 리턴하는 메소드
     * @return borrowerMap
     */
    public HashMap<String, Borrower> getBorrowerMap(){
        return borrowerMap;
    }
    
    /**
     * library의 속성 bookMap을 리턴하는 메소드
     * @return bookMap
     */
    public HashMap<Integer, Book> getBookMap() {
        return bookMap;
    }
    
    /**
     * library의 속성 loanPossibleNumberRule을 리턴하는 메소드
     * @return loanPossibleNumberRule
     */
    public int getLoanPossibleNumberRule() {
        return loanPossibleNumberRule;
    }
    
    public ArrayList<History> getHistoryArray() {
        return historyArray;
    }
}