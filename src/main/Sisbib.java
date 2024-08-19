package main;
import java.util.ArrayList;
import java.util.List;

import states.EmptyState;
import states.IState;
import users.IUser;

public class Sisbib {
    private static Sisbib instance;
    private IState state;
    private List<IUser> users;
    private List<Book> books;
    private List<Copy> copies;
    
    public void addUser(IUser user) {
        this.users.add(user);
    }
    
    public void addBook(Book book) {
        this.books.add(book);
    }

    public void addCopy(Copy copy) {
        this.copies.add(copy);
    }

    private Sisbib() {
        this.state = new EmptyState();
        this.users = new ArrayList<IUser>();
        this.books = new ArrayList<Book>();
        this.copies = new ArrayList<Copy>();

        this.state.onEnter();
    }

    public static Sisbib getInstance() {
        if (instance == null) {
            instance = new Sisbib();
        }
        return instance;
    }

    public boolean isInFinalState() {
        return this.state.isDone();
    }

    public void setState(IState state) {
        this.state.onExit();
        this.state = state;
        this.state.onEnter();
    }
    
    public void run() {
        while (!this.state.isDone()) {
            this.state.onTick();
        }
    }
}
