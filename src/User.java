import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private BorrowStrategy borrowStrategy;
    private List<Loan> loans = new ArrayList<>();

    public User(String id, String name, BorrowStrategy initialStrategy) {
        this.id = id;
        this.name = name;
        this.borrowStrategy = initialStrategy;
    }

    public void requestLoan(Book book, LoanService loanService) {
        loanService.createLoan(this, book);
    }

    public void changeStrategy(BorrowStrategy newStrategy) {
        this.borrowStrategy = newStrategy;
    }

    public boolean hasOverdueBooks() {
        return loans.stream().anyMatch(Loan::isOverdue);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public BorrowStrategy getCurrentStrategy() {
        return borrowStrategy;
    }

    public String getName() {
        return name;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }
}
