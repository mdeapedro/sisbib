package main;

import java.time.LocalDate;

public class LoanHistory {
    private Loan loan;
    private LocalDate returnedOn;

    public LoanHistory(Loan loan, LocalDate returnedOn) {
        this.loan = loan;
        this.returnedOn = returnedOn;
    }

    public Loan getLoan() {
        return loan;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }
}
