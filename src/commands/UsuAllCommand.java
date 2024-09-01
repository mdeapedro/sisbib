package commands;

import java.util.List;

import main.Output;
import main.Sisbib;
import users.IUser;

public class UsuAllCommand implements ICommand {
    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        List<IUser> users = sisbib.getUsers();
        Output.info("O comando 'usu' espera 1 argumento: codigo_do_usuario");
        Output.info();
        Output.info("Usuários cadastrados:");
        Output.info();
        Output.info("Código; Nome");
        for (IUser user : users) {
            Output.info(Integer.toString(user.getId()), "; ", user.getName());
        }

    }
}
