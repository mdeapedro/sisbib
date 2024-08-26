package users;
public class Professor implements IUser {
    private int id;
    private String name;
    private int maxNumberOfReserves = 3;

    public Professor(int id, String name) {
        this.id = id;
        this.name = name;
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
}
