package main;

import java.util.ArrayList;
import java.util.List;

import users.IUser;

public class LoanManager {
    List<Loan> loans = new ArrayList<Loan>();
    
    public void addLoan(Loan loan) throws LoanManagerException {
        Loan copyLoan = getLoanByCopy(loan.getCopy());
        
        if (copyLoan != null) {
            String message = "O exemplar '";
            message += copyLoan.getCopy().getBook().getTitle();
            message += "' já foi emprestado ao usuário '";
            message += copyLoan.getUser().getName();
            message += "'";
            throw new LoanManagerException(message);
        }

        loans.add(loan);
    }
    
    public List<Loan> getBookLoans(Book book) {
        List<Loan> bookLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getCopy().getBook().equals(book)) {
                bookLoans.add(loan);
            }
        }
        return bookLoans;
    }
    
    public boolean isLoaned(Copy copy) {
        for (Loan loan : loans) {
            if (loan.getCopy().equals(copy)) {
                return true;
            }
        }
        return false;
    }
    
    public List<Loan> getUserLoans(IUser user) {
        List<Loan> userLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getUser().equals(user)) {
                userLoans.add(loan);
            }
        }
        return userLoans;
    }
    
    public Loan getLoanByCopy(Copy copy) {
        for (Loan loan : loans) {
            if (loan.getCopy().equals(copy)) {
                return loan;
            }
        }
        return null;
    }
}
