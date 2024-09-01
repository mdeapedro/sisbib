package users;

import loaner.BasicLoaner;
import loaner.ILoaner;

public class Undergraduate implements IUser {
    private int id;
    private String name;
    private int maxNumberOfReserves = 3;
    private int maxNumberOfLoans = 3;
    private int daysToReturnLoan = 3;
    private ILoaner loaner;
    
    public Undergraduate(int id, String name) {
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
