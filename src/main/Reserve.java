package main;

import java.time.LocalDate;

import users.IUser;

public class Reserve {
    private IUser user;
    private Copy copy;
    private LocalDate creationDate;
    
    public Reserve(IUser user, Copy copy) {
        this.user = user;
        this.copy = copy;
        creationDate = LocalDate.now();
    }
    
    public IUser getUser() {
        return user;
    }
    
    public Copy getCopy() {
        return copy;
    }
    
    public LocalDate getCreationDate() {
        return creationDate;
    }
}
