package states;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.Book;
import main.Sisbib;

public class LoadBooksState implements IState {
    String books;
    boolean done = false;
    int numberOfBooksLoaded = 0;

    public LoadBooksState(String books) {
        this.books = books;
    }

    public void onEnter() {
        System.out.print("Carregando livros");
    }
    
    public void onTick() {
        if (!this.done) {
            this.loadBooks();
            this.done = true;
        }
    }
    
    public void onExit() {
        System.out.println(" " + this.numberOfBooksLoaded + " livro(s) carregado(s).");
    }
    
    public boolean isDone() {
        return this.done;
    }

    private void loadBooks() {
        Sisbib sisbib = Sisbib.getInstance();
        File booksFile = new File(this.books);
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
                sisbib.addBook(book);
                this.numberOfBooksLoaded++;
                System.out.print(".");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + booksFile);
            System.exit(1);
        }
    }
}
