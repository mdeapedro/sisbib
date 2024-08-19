package users;
public class Professor implements IUser {
    private int id;
    private String name;

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
}
