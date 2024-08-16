public class ProfessorStrategy implements BorrowStrategy {
    private static final int LOAN_DAYS = 7;

    public boolean canBorrow(User user, Book book) {
        return !user.hasOverdueBooks() &&
               book.hasAvailableCopies();
    }
    public int getLoanTime() {
        return LOAN_DAYS;
    }
}
