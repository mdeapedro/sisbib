package users;

import main.AdvancedLoaner;
import main.ILoaner;

public class Professor implements IUser {
    private int id;
    private String name;
    private int maxNumberOfReserves = 3;
    private int daysToReturnLoan = 7;
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
    
    public ILoaner getLoaner() {
        return loaner;
    }
}
