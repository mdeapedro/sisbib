package states;

import commands.*;
import exceptions.ReadCommandException;
import main.Book;
import main.INotifiable;
import main.IObserver;
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
        System.out.print("Digite um comando (emp, res, dev, obs, liv, usu, ntf, sai): ");
        this.getNextCommand().execute();
    }

    public void onExit() {
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
            case "emp":
                try {
                    assertArgsLength(args, 3, "codigo_do_usuario", "codigo_do_livro");
                    IUser user = getUser(args[1]);
                    Book book = getBook(args[2]);
                    return new EmpCommand(user, book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }

            case "res":
                try {
                    assertArgsLength(args, 3, "codigo_do_usuario", "codigo_do_livro");
                    IUser user = getUser(args[1]);
                    Book book = getBook(args[2]);
                    return new ResCommand(user, book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }

            case "dev":
                try {
                    assertArgsLength(args, 3, "codigo_do_usuario", "codigo_do_livro");
                    IUser user = getUser(args[1]);
                    Book book = getBook(args[2]);
                    return new DevCommand(user, book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }

            case "obs":
                try {
                    assertArgsLength(args, 3, "codigo_do_usuario", "codigo_do_livro");
                    IUser user = getUser(args[1]);
                    Book book = getBook(args[2]);
                    if (!(user instanceof IObserver)) {
                        return new ErrorCommand("O usuário não tem essa capacidade.");
                    }
                    return new ObsCommand((IObserver)user, book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }
            
            case "usu":
                if (args.length == 1) {
                    return new UsuAllCommand();
                }
                
                try {
                    assertArgsLength(args, 2, "codigo_do_usuario");
                    IUser user = getUser(args[1]);
                    return new UsuCommand(user);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }
            
            case "liv":
                if (args.length == 1) {
                    return new LivAllCommand();
                }
                
                try {
                    assertArgsLength(args, 2, "codigo_do_livro");
                    Book book = getBook(args[1]);
                    return new LivCommand(book);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                }

            case "ntf":
                try {
                    assertArgsLength(args, 2, "codigo_do_usuario");
                    IUser user = getUser(args[1]);
                    if (!(user instanceof INotifiable)) {
                        return new ErrorCommand("O usuário não tem essa capacidade.");
                    }
                    return new NtfCommand((INotifiable)user);
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

            case "mock":
                try {
                    assertArgsLength(args, 4, "dia", "mes", "ano");
                    int day = Integer.parseInt(args[1]);
                    int month = Integer.parseInt(args[2]);
                    int year = Integer.parseInt(args[3]);
                    return new MockCommand(day, month, year);
                } catch (ReadCommandException e) {
                    return new ErrorCommand(e.getMessage());
                } catch (NumberFormatException e) {
                    return new ErrorCommand("dia, mes e ano devem ser inteiros.");
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
    
    private void assertArgsLength(String[] args, int expectedArgsLength, String ...argsNames) throws ReadCommandException {
        if (args.length != expectedArgsLength) {
            String message = "O comando '";
            message += args[0];
            message += "' espera ";
            message += expectedArgsLength - 1;
            message += " argumentos";
            if (argsNames.length > 0) {
                message += ": ";
                message += String.join(" ", argsNames);
            } else {
                message += ".";
            }
            throw new ReadCommandException(message);
        }
    }
}
