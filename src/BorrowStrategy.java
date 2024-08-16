public interface BorrowStrategy {
    boolean canBorrow(User user, Book book);
    int getLoanTime();
}