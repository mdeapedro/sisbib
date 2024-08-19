package main;
import java.util.List;

import states.IState;
import states.LoadTestCaseState;

public class Sisbib {
    private static Sisbib instance;
    private IState state;
    private List<IUser> users;
    private List<Book> books;
    private List<Copy> copies;
    
    public void setUsers(List<IUser> users) {
        this.users = users;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    private Sisbib() {
        this.state = new LoadTestCaseState();
        this.state.onEnter();
    }

    public static Sisbib getInstance() {
        if (instance == null) {
            instance = new Sisbib();
        }
        return instance;
    }

    public boolean isInFinalState() {
        return this.state.isFinal();
    }

    public void setState(IState state) {
        this.state.onExit();
        this.state = state;
        this.state.onEnter();
    }

    public void tick() {
        this.state.onTick();
    }
}
