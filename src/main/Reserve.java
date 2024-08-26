package main;

import users.IUser;

public class Reserve {
    private IUser user;
    private Copy copy;
    
    public Reserve(IUser user, Copy copy) {
        this.user = user;
        this.copy = copy;
    }
    
    public IUser getUser() {
        return this.user;
    }
    
    public Copy getCopy() {
        return this.copy;
    }
}
