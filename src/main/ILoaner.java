package main;

import exceptions.LoanerException;

public interface ILoaner {
    void performLoan(Book book) throws LoanerException;
}