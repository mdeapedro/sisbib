public class LoanService {
    public boolean createLoan(User user, Book book) {
        if (user.getCurrentStrategy().canBorrow(user, book)) {
            Loan loan = new Loan(user, book, user.getCurrentStrategy().getLoanDays());
            user.addLoan(loan);
            book.addLoan(loan);
            book.borrowCopy();
            System.out.println(user.getName() + "successfully borrowed" + book.getTitle());
            return true;
        } else {
            System.out.println(user.getName() + "could not borrow" + book.getTitle());
            return false;
        }
    }
}