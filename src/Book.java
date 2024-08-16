import java.util.ArrayList;
import java.util.List;

public class Book {
    private String id;
    private String title;
    private int availableCopies;
    private List<Loan> loans = new ArrayList<>();

    public Book(String id, String title, int initialCopies) {
        this.id = id;
        this.title = title;
        this.availableCopies = initialCopies;
    }

    public boolean borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    public void returnCopy() {
        availableCopies++;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans); 
    }

    public boolean isBorrowed() {
        return availableCopies < 1;
    }
}