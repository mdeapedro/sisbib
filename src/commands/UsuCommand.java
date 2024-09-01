package commands;

import users.IUser;

public class UsuCommand implements ICommand {
    private IUser user;

    public UsuCommand(IUser user) {
        this.user = user;
    }

    public void execute() { }
}
