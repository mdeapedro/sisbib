package states;

import commands.*;
import main.Book;
import main.ReadCommandException;
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
        System.out.print("Digite um comando (res, liv, sai): ");
        this.getNextCommand().execute();
    }

    public void onExit() {
        System.out.println("Sayonara! (^ . ^)");
        this.scanner.close();
    }

    public boolean isDone() {
        return false;
    }
    
    private ICommand getNextCommand() {
        String args[];
        try {
            args = this.scanner.nextLine().split(" ");
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());

            throw e;
        }
        String command = args[0];
        switch (command) {
            case "res":
                try {
                    assertArgsLength(args, 3);
                    IUser user = getUser(args[1]);
                    Book book = getBook(args[2]);
                    return new ResCommand(user, book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }
            
            case "liv":
                if (args.length == 1) {
                    return new LivAllCommand();
                }
                
                try {
                    assertArgsLength(args, 2);
                    Book book = getBook(args[1]);
                    return new LivCommand(book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }
            
            case "sai":
                try {
                    assertArgsLength(args, 1);
                    return new SaiCommand();
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }

            default:
                String message = "'";
                message += command;
                message += "' não é um comando válido.";
                return new ErrorCommand(message);
        }
    }
    
    private IUser getUser(String userIdString) throws ReadCommandException {
        Sisbib sisbib = Sisbib.getInstance();
        int userId;
        try {
            userId = Integer.parseInt(userIdString);
        } catch (NumberFormatException e) {
            String message = "codigo_usuario deve ser inteiro.";
            throw new ReadCommandException(message);
        }

        IUser user = sisbib.getUserById(userId);

        if (user == null) {
            String message = "Nenhum usuário com id '";
            message += userId;
            message += "' cadastrado.";
            throw new ReadCommandException(message);
        }
        
        return user;
    }
    
    private Book getBook(String bookIdString) throws ReadCommandException {
        Sisbib sisbib = Sisbib.getInstance();
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdString);
        } catch (NumberFormatException e) {
            String message = "codigo_livro deve ser inteiro.";
            throw new ReadCommandException(message);
        }

        Book book = sisbib.getBookById(bookId);

        if (book == null) {
            String message = "Nenhum livro com id '";
            message += bookId;
            message += "' cadastrado.";
            throw new ReadCommandException(message);
        }
        
        return book;
    }
    
    private void assertArgsLength(String[] args, int expectedArgsLength) throws ReadCommandException {
        if (args.length != expectedArgsLength) {
            String message = "O comando '";
            message += args[0];
            message += "' espera ";
            message += expectedArgsLength - 1;
            message += " argumentos.";
            throw new ReadCommandException(message);
        }
    }
}
