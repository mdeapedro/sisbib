package main;

public interface ILoaner {
    void performLoan(Book book) throws LoanerException;
}