package libraryApp;

import libraryApp.Book;
import libraryApp.Borrower;
import libraryApp.Loan;
import libraryApp.History;
import java.util.*;

/**
 *  Library Application을 실행하는 MyLibraryApplication Class
 *
 * @author (3팀 강영미, 전유정, 최서연, 허다희)
 * @version ()
 */
public class MyLibraryApplication {
    public static void displayMenu() {
        System.out.println("========== 선택메뉴 ==========");
        System.out.println("0 : 종료");
        System.out.println("1 : 이용자 등록");
        System.out.println("2 : 서적 등록");
        System.out.println("3 : 대출가능서적 출력");
        System.out.println("4 : 대출중 서적 출력");
        System.out.println("5 : 대출");
        System.out.println("6 : 반납");
        System.out.println("===========================");
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Library의 이름을 입력하세요: ");
        String libraryName = scanner.nextLine();
        Library library = new Library(libraryName);
        while(true) {
            displayMenu();
            System.out.print(">메뉴를 선택하시오(0~6): ");
            String input = scanner.next();
            if (input.equals("0")) {
                System.out.println("프로그램을 종료합니다...");
                displayHistory(library); //대출,반납기록 출력
                break;
            }
            switch(input) {
                case "1":
                    registerOneBorrower_SQ1(library);
                    break;
                case "2":
                    registerOneBook_SQ2(library);
                    break;
                case "3":
                    displayBooksForLoan_SQ3(library);
                    break;
                case "4":
                    displayBooksOnLoan_SQ4(library);
                    break;
                case "5":
                    lendOneBook_SQ5(library);
                    break;
                case "6":
                    returnOneBook_SQ6(library);
                    break;
                default:
                    System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
            }
            System.out.println();                
        }
    }

    public void registerOneBorrower_SQ1(Library library) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print(">등록할 이용자의 이름을 입력하세요: ");
            String name = scanner.next();
            String message = library.registerOneBorrower(name);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("잘못입력하셨습니다.");
        }
    }

    public void registerOneBook_SQ2(Library library) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print(">등록할 책의 이름을 입력하세요: ");
            String title = scanner.nextLine();
            System.out.print(">등록할 책의 저자를 입력하세요(2명 이상일 경우 반점(,)으로 구분): ");
            String authors[] = scanner.nextLine().split(",");
            for(int i=0; i<authors.length; i++){ 
                authors[i] = authors[i].trim(); 
            }
            System.out.print(">등록할 책의 고유번호를 입력하세요: ");
            int catalogueNumber = scanner.nextInt();
            String message = library.registerOneBook(title, authors, catalogueNumber);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("잘못입력하셨습니다.");
        }
    }

    public void displayBooksForLoan_SQ3(Library library) {
        String message = library.displayBooksForLoan();
        System.out.println(message);
    }

    public void displayBooksOnLoan_SQ4(Library library) {
        String message = library.displayBooksOnLoan();
        System.out.println(message);
    }

    public void lendOneBook_SQ5(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(">이용자의 이름을 입력하세요: ");
        String name = scanner.nextLine();
        Borrower borrower = library.getBorrowerMap().get(name);
        String message = "";
        if(borrower == null){ // 등록되지 않은 이용자일 경우
            message = "등록되지 않은 이용자입니다.";
        }
        else if(borrower.getLoanArraySize() == library.getLoanPossibleNumberRule()){ // 이용자가 5권 대출중일 경우
            message = "이미 대출가능한 권수를 모두 대출하셨습니다.";
        }
        else{
            System.out.print(">대출할 책의 고유번호를 입력하세요: ");
            try{
                int catalogueNumber = scanner.nextInt();
                message = library.lendOneBook(name, catalogueNumber);
            } catch(Exception e) {
                message = "잘못입력하셨습니다.";
            }
        }
        System.out.println(message); 
    }

    public void returnOneBook_SQ6(Library library) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print(">반납할 책의 고유번호를 입력하세요: ");
            int catalogueNumber = scanner.nextInt();
            String message = library.returnOneBook(catalogueNumber);
            System.out.println(message);
        } catch(Exception e) {
            System.out.println("잘못입력하셨습니다.");
        }
    }
    
    public void displayHistory(Library library) {
        ArrayList<History> historyArray = library.getHistoryArray();
        if(historyArray.size() == 0){
            return;
        }
        else{
            System.out.println("=========== 로그 ===========");
            for(int i=0; i<historyArray.size(); i++){
                History history = historyArray.get(i);
                System.out.print("[" + history.getState() + "] ");
                System.out.println("" + history.getState() + "자: " + history.getBorrowerName());
                System.out.println("      도서제목: " + history.getTitle());
                System.out.println("      " + history.getState() + "일자: " + history.getDate() );
            }
        }
    }

    public static void main(String[] args) {
        new MyLibraryApplication().run();
    }
}