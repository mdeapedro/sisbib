package states;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.Sisbib;
import users.IUser;
import users.Postgraduate;
import users.Professor;
import users.Undergraduate;

public class LoadUsersState implements IState{
    String users;
    int numberOfUsersLoaded = 0;
    boolean done = false;

    public LoadUsersState(String users) {
        this.users = users;
    }

    public void onEnter() {
        System.out.print("Carregando usuários");
    }
    
    public void onTick() {
        if (!this.done) {
            this.loadUsers();
            this.done = true;
        }
    }
    
    public void onExit() {
        System.out.println(" " + numberOfUsersLoaded + " usuário(s) carregado(s).");
    }
    
    public boolean isDone() {
        return this.done;
    }

    private void loadUsers() {
        Sisbib sisbib = Sisbib.getInstance();
        File usersFile = new File(this.users);
        try {
            Scanner scanner = new Scanner(usersFile);
            while (scanner.hasNextLine()) {
                String[] userArgs = scanner.nextLine().split(";");
                int id = Integer.parseInt(userArgs[0]);
                String userType = userArgs[1];
                String name = userArgs[2];
                IUser user;
                if (userType.equals("Aluno de Graduação")) {
                    user = new Undergraduate(id, name);
                } else if (userType.equals("Aluno de Pós-graduação")) {
                    user = new Postgraduate(id, name);
                } else if (userType.equals("Professor")) {
                    user = new Professor(id, name);
                } else {
                    scanner.close();
                    throw new IllegalArgumentException("Tipo de usuário não reconhecido: " + userType);
                }
                sisbib.addUser(user);
                this.numberOfUsersLoaded++;
                System.out.print(".");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + usersFile);
            System.exit(1);
        }
    }
}
