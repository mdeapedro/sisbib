package users;

import main.BasicLoaner;
import main.ILoaner;

public class Postgraduate implements IUser {
    private int id;
    private String name;
    private int maxNumberOfReserves = 3;
    private int maxNumberOfLoans = 4;
    private int daysToReturnLoan = 5;
    private ILoaner loaner;

    public Postgraduate(int id, String name) {
        this.id = id;
        this.name = name;
        loaner = new BasicLoaner(this, maxNumberOfLoans, daysToReturnLoan);
    }

    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getMaxNumberOfReserves() {
        return this.maxNumberOfReserves;
    }
    
    public ILoaner getLoaner() {
        return loaner;
    }
}
