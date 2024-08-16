public class UndergraduateStudentStrategy implements BorrowStrategy {
    private static final int MAX_BOOKS = 3;
    private static final int LOAN_DAYS = 3;

    public boolean canBorrow(User user, Book book) {
        //

    }
    public int getLoanTime() {
        return LOAN_DAYS;
    }
}
