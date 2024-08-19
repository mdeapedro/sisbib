package main;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Espera-se 3 argumentos: usuarios, livros e exemplares.");
        }
        
        List<IUser> users = loadUsers(new File(args[0]));
        List<Book> books = loadBooks(new File(args[1]));
        List<Copy> copies = loadCopies(new File(args[2]));
        
        Sisbib sisbib = Sisbib.getInstance();
        sisbib.setUsers(users);
        sisbib.setBooks(books);
        sisbib.setCopies(copies);

        while (!sisbib.isInFinalState()) {
            sisbib.tick();
        }
    }
    
    private static List<IUser> loadUsers(File usersFile) {
        List<IUser> users = new ArrayList<IUser>();
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
                users.add(user);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + usersFile);
            System.exit(1);
        }
        return users;
    }
    
    private static List<Book> loadBooks(File booksFile) {
        List<Book> books = new ArrayList<Book>();
        try {
            Scanner scanner = new Scanner(booksFile);
            while (scanner.hasNextLine()) {
                String[] bookArgs = scanner.nextLine().split(";");
                int id = Integer.parseInt(bookArgs[0]);
                String title = bookArgs[1];
                String publisher = bookArgs[2];
                String authors = bookArgs[3];
                String edition = bookArgs[4];
                String release_year = bookArgs[5];
                Book book = new Book(id, title, publisher, authors, edition, release_year);
                books.add(book);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + booksFile);
            System.exit(1);
        }
        return books;
    }
    
    private static List<Copy> loadCopies(File copiesFile) {
        List<Copy> copies = new ArrayList<Copy>();
        try {
            Scanner scanner = new Scanner(copiesFile);
            while (scanner.hasNextLine()) {
                String[] copyArgs = scanner.nextLine().split(";");
                int id = Integer.parseInt(copyArgs[0]);
                int bookId = Integer.parseInt(copyArgs[1]);
                Copy copy = new Copy(id, bookId);
                copies.add(copy);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + copiesFile);
            System.exit(1);
        }
        return copies;
    }
}
