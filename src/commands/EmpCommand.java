package commands;

import main.Book;
import main.Sisbib;
import users.IUser;

public class EmpCommand implements ICommand {
    public EmpCommand(IUser user, Book book) {
        Sisbib sisbib = Sisbib.getInstance();
        
    }

    public void execute() {

    }
}
