package states;

import commands.*;
import main.Book;
import main.Sisbib;
import users.IUser;

import java.util.Scanner;

public class ReadCommandState implements IState {
    private Scanner scanner;

    public ReadCommandState() {
        this.scanner = new Scanner(System.in);
    }

    public void onEnter() {
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("                              SISBIB                            ");
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }

    public void onTick() {
        System.out.print("Digite um comando (emp, dev, res, obs, sai): ");
        this.getNextCommand().execute();
        System.out.println();
    }

    public void onExit() {
        System.out.println("Sayonara! (^ . ^)");
        this.scanner.close();
    }

    public boolean isDone() {
        return false;
    }

    private ICommand getNextCommand() {
        Sisbib sisbib = Sisbib.getInstance();

        String nextLine = this.scanner.nextLine();

        if (nextLine.equals("sai")) {
            return new SaiCommand();
        }
        String[] tokens = nextLine.split(" ");
        String command = tokens[0];

        if (tokens.length != 3) {
            String message = "Espera-se um comando seguido do id do usuário e id do livro.";
            return new BadCommand(message);
        }

        int userId = Integer.parseInt(tokens[1]);
        int bookId = Integer.parseInt(tokens[2]);
        
        IUser user = sisbib.getUserById(userId);
        if (user == null) {
            String message = "Usuário de id ";
            message += userId;
            message += " não encontrado.";
            return new BadCommand(message);
        }
        
        Book book = sisbib.getBookById(bookId);

        if (book == null) {
            String message = "Livro de id ";
            message += bookId;
            message += " não encontrado.";
            return new BadCommand(message);
        }

        switch (command) {
            case "emp":
                return new EmpCommand();
            case "dev":
                return new DevCommand();
            case "res":
                return new ResCommand(user, book);
            case "obs":
                return new ObsCommand();
            default:
                String message = "Comando não identificado.";
                return new BadCommand(message);
        }
    }
}
