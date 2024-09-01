package main;

import java.util.ArrayList;
import java.util.List;

import exceptions.LoanManagerException;
import exceptions.LoanerException;

import java.time.LocalDate;

import users.IUser;

public class BasicLoaner implements ILoaner {
    private IUser user;
    private int maxNumberOfLoans;
    private int daysToReturn;
    
    public BasicLoaner(IUser user, int maxNumberOfLoans, int daysToReturn) {
        this.user = user;
        this.maxNumberOfLoans = maxNumberOfLoans;
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
            if (LocalDate.now().isAfter(loan.getReturnDate())) {
                throw new LoanerException("O usuário tem empréstimos vencidos. Regularize sua situação para solicitar novos empréstimos.");
            }
        }
        
        if (userLoans.size() >= maxNumberOfLoans) {
            String message = "O usuário atingiu o limite máximo de empréstimos (";
            message += maxNumberOfLoans;
            message += ")";
            throw new LoanerException(message);
        }
        
        List<Reserve> userReserves = reserveManager.getUserReserves(user);
        for (Reserve reserve : userReserves) {
            if (reserve.getCopy().getBook().equals(book)) {
                reserveManager.removeReserve(reserve);
                try {
                    loanManager.addLoan(new Loan(reserve.getCopy(), user, LocalDate.now().plusDays(daysToReturn)));
                } catch (LoanManagerException e) {
                    throw new LoanerException(e.getMessage());
                }
                return;
            }
        }
        
        for (Copy bookCopy : avaiableBookCopies) {
            if (!reserveManager.isReserved(bookCopy)) {
                try {
                    loanManager.addLoan(new Loan(bookCopy, user, LocalDate.now().plusDays(daysToReturn)));
                } catch (LoanManagerException e) {
                    throw new LoanerException(e.getMessage());
                }
                return;
            }
        }
        
        String message = "Todos os exemplares do livro '";
        message += book.getTitle();
        message += "' já estão reservados.";
        throw new LoanerException(message);
    }
}
