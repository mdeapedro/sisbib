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
    private ReserveManager reserveManager;
    
    public void addUser(IUser user) {
        this.users.add(user);
    }
    
    public void addBook(Book book) {
        this.books.add(book);
    }

    public void addCopy(Copy copy) {
        this.copies.add(copy);
    }
    
    public IUser getUserById(int userId) {
        for (IUser user : this.users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public Book getBookById(int bookId) {
        for (Book book : this.books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
    
    public List<Copy> getBookCopies(Book book) {
        List<Copy> bookCopies = new ArrayList<Copy>();
        for (Copy copy : copies) {
            if (copy.getBook().equals(book)) {
                bookCopies.add(copy);
            }
        }
        return bookCopies;
    }
    
    public ReserveManager getReserveManager() {
        return this.reserveManager;
    }

    private Sisbib() {
        this.state = new EmptyState();
        this.users = new ArrayList<IUser>();
        this.books = new ArrayList<Book>();
        this.copies = new ArrayList<Copy>();

        this.reserveManager = new ReserveManager();

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
