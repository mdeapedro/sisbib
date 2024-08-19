package main;

import states.LoadBooksState;
import states.LoadCopiesState;
import states.LoadUsersState;
import states.ReadCommandState;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Espera-se 3 argumentos: usuarios, livros e exemplares.");
        }
        String users = args[0];
        String books = args[1];
        String copies = args[2];

        Sisbib sisbib = Sisbib.getInstance();

        sisbib.setState(new LoadUsersState(users));
        sisbib.run();
        
        sisbib.setState(new LoadBooksState(books));
        sisbib.run();
        
        sisbib.setState(new LoadCopiesState(copies));
        sisbib.run();
        
        sisbib.setState(new ReadCommandState());
        sisbib.run();
    }
}
