package users;

import loaner.AdvancedLoaner;
import loaner.ILoaner;
import main.Book;
import main.INotifiable;
import main.IObserver;

public class Professor implements IUser, IObserver, INotifiable {
    private int id;
    private String name;
    private int maxNumberOfReserves = 3;
    private int daysToReturnLoan = 7;
    private int numberOfNotifications = 0;
    private ILoaner loaner;

    public Professor(int id, String name) {
        this.id = id;
        this.name = name;
        loaner = new AdvancedLoaner(this, daysToReturnLoan);
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMaxNumberOfReserves() {
        return maxNumberOfReserves;
    }
    
    public int getNumberOfNotifications() {
        return numberOfNotifications;
    }
    
    public ILoaner getLoaner() {
        return loaner;
    }
    
    public void update(Book book) {
        numberOfNotifications++;
    }
}
