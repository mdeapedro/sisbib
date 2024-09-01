package loaner;

import java.util.ArrayList;
import java.util.List;

import exceptions.LoanManagerException;
import exceptions.LoanerException;
import main.Book;
import main.Copy;
import main.Loan;
import main.LoanManager;
import main.MockDate;
import main.Reserve;
import main.ReserveManager;
import main.Sisbib;

import users.IUser;

public class AdvancedLoaner implements ILoaner {
    private IUser user;
    private int daysToReturn;
    
    public AdvancedLoaner(IUser user, int daysToReturn) {
        this.user = user;
        this.daysToReturn = daysToReturn;
    }

    public void performLoan(Book book) throws LoanerException {
        Sisbib sisbib = Sisbib.getInstance();
        LoanManager loanManager = sisbib.getLoanManager();
        ReserveManager reserveManager = sisbib.getReserveManager();

        List<Loan> userLoans = loanManager.getUserLoans(user);
        
        for (Loan loan : userLoans) {
            if (loan.getCopy().getBook().equals(book)) {
                throw new LoanerException("O usuário já tem um empréstimo para esse livro");
            }
        }

        List<Copy> avaiableBookCopies = new ArrayList<>();
        for (Copy bookCopy : sisbib.getBookCopies(book)) {
            if (!loanManager.isLoaned(bookCopy)) {
                avaiableBookCopies.add(bookCopy);
            }
        }
        
        if (avaiableBookCopies.size() == 0) {
            String message = "Nenhum exemplar do livro '";
            message += book.getTitle();
            message += "' disponível para empréstimo.";
            throw new LoanerException(message);
        }
        
        for (Loan loan : userLoans) {
            if (MockDate.now().isAfter(loan.getReturnDate())) {
                throw new LoanerException("O usuário tem empréstimos vencidos. Regularize sua situação para solicitar novos empréstimos.");
            }
        }
        
        List<Reserve> userReserves = reserveManager.getUserReserves(user);
        for (Reserve reserve : userReserves) {
            if (reserve.getCopy().getBook().equals(book)) {
                reserveManager.removeReserve(reserve);
                try {
                    loanManager.createLoan(new Loan(reserve.getCopy(), user, MockDate.now().plusDays(daysToReturn)));
                } catch (LoanManagerException e) {
                    throw new LoanerException(e.getMessage());
                }
                return;
            }
        }
        
        for (Copy bookCopy : avaiableBookCopies) {
            if (!reserveManager.isReserved(bookCopy)) {
                try {
                    loanManager.createLoan(new Loan(bookCopy, user, MockDate.now().plusDays(daysToReturn)));
                } catch (LoanManagerException e) {
                    throw new LoanerException(e.getMessage());
                }
                return;
            }
        }
        
        Copy bookToLoan = avaiableBookCopies.get(0);
        reserveManager.removeReserve(reserveManager.getReserveByCopy(bookToLoan));
        try {
            loanManager.createLoan(new Loan(bookToLoan, user, MockDate.now().plusDays(daysToReturn)));
        } catch (LoanManagerException e) {
            throw new LoanerException(e.getMessage());
        }
    }
}
