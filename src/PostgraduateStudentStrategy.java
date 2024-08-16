public class PostgraduateStudentStrategy implements BorrowStrategy {
    private static final int MAX_BOOKS = 4;
    private static final int LOAN_DAYS = 5;

    public boolean canBorrow(User user, Book book) {
       
    }

    public int getLoanTime() {
        return LOAN_DAYS;
    }
}
