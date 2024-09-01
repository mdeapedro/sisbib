package loaner;

import exceptions.LoanerException;
import main.Book;

public interface ILoaner {
    void performLoan(Book book) throws LoanerException;
}