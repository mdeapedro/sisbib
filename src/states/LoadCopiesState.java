package states;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.Book;
import main.Copy;
import main.Sisbib;

public class LoadCopiesState implements IState{
    String copies;
    boolean done = false;
    int numberOfCopiesLoaded = 0;

    public LoadCopiesState(String copies) {
        this.copies = copies;
    }

    public void onEnter() {
        System.out.print("Carregando exemplares");
    }
    
    public void onTick() {
        if (!this.done) {
            this.loadCopies();
            this.done = true;
        }
    }
    
    public void onExit() {
        System.out.println(" " + this.numberOfCopiesLoaded + " exemplar(es) carregado(s).");
    }
    
    public boolean isDone() {
        return this.done;
    }

    private void loadCopies() {
        Sisbib sisbib = Sisbib.getInstance();
        File copiesFile = new File(this.copies);
        try {
            Scanner scanner = new Scanner(copiesFile);
            while (scanner.hasNextLine()) {
                String[] copyArgs = scanner.nextLine().split(";");
                int bookId = Integer.parseInt(copyArgs[0]);
                Book book = sisbib.getBookById(bookId);
                int id = Integer.parseInt(copyArgs[1]);
                Copy copy = new Copy(id, book);
                sisbib.addCopy(copy);
                this.numberOfCopiesLoaded++;
                System.out.print(".");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + copiesFile);
            System.exit(1);
        }
    }
}
