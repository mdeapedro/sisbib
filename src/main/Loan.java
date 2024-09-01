package main;

import java.time.LocalDate;

import users.IUser;

public class Loan {
    private Copy copy;
    private IUser user;
    private LocalDate returnDate;
    private LocalDate creationDate;

    public Loan(Copy copy, IUser user, LocalDate returnDate) {
        this.copy = copy;
        this.user = user;
        this.returnDate = returnDate;
        creationDate = LocalDate.now();
    }

    public Copy getCopy() {
        return copy;
    }

    public IUser getUser() {
        return user;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public LocalDate getCreationDate() {
        return creationDate;
    }
}
