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
    private LoanManager loanManager;
    private LoanHistoryManager loanHistoryManager;

    private Sisbib() {
        state = new EmptyState();
        users = new ArrayList<IUser>();
        books = new ArrayList<Book>();
        copies = new ArrayList<Copy>();

        reserveManager = new ReserveManager();
        loanManager = new LoanManager();
        loanHistoryManager = new LoanHistoryManager();

        state.onEnter();
    }

    public static Sisbib getInstance() {
        if (instance == null) {
            instance = new Sisbib();
        }
        return instance;
    }

    public void addUser(IUser user) {
        users.add(user);
    }
    
    public void addBook(Book book) {
        books.add(book);
    }

    public void addCopy(Copy copy) {
        copies.add(copy);
    }
    
    public IUser getUserById(int userId) {
        for (IUser user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
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
    
    public List<Book> getBooks() {
        return books;
    }
    
    public List<IUser> getUsers() {
        return users;
    }
    
    public ReserveManager getReserveManager() {
        return reserveManager;
    }

    public LoanManager getLoanManager() {
        return loanManager;
    }
    
    public LoanHistoryManager getLoanHistoryManager() {
        return loanHistoryManager;
    }

    public boolean isInFinalState() {
        return state.isDone();
    }

    public void setState(IState state) {
        this.state.onExit();
        this.state = state;
        this.state.onEnter();
    }
    
    public void run() {
        while (!state.isDone()) {
            state.onTick();
        }
    }
}
