import java.time.LocalDate;

public class Loan {
    private User user;
    private Book book;
    private LocalDate loanDate;
    private LocalDate dueDate;

    public Loan(User user, Book book, int loanDays) {
        this.user = user;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(loanDays);
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate);
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
