package users;
public class Undergraduate implements IUser {
    private int id;
    private String name;
    
    public Undergraduate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
}
