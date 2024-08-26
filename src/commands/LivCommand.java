package commands;

import java.util.List;

import main.Book;
import main.Copy;
import main.Reserve;
import main.ReserveManager;
import main.Sisbib;

public class LivCommand implements ICommand {
    private Book book;
    
    public LivCommand(Book book) {
        this.book = book;
    }

    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        ReserveManager reserveManager = sisbib.getReserveManager();

        System.out.println();
        System.out.println("Consulta do livro de código " + book.getId() + ":");
        System.out.println(book.getTitle());
        System.out.println();
        System.out.println("Código do exemplar; Status");

        List<Copy> bookCopies = sisbib.getBookCopies(book);

        for (Copy bookCopy : bookCopies) {
            Reserve reserve = reserveManager.getReserveByCopy(bookCopy);
            
            System.out.print(bookCopy.getId());
            System.out.print("; ");

            if (reserve != null) {
                System.out.print("Reservado para ");
                System.out.println(reserve.getUser().getName());
            } else {
                System.out.println("Disponível");
            }
            

            // reservedBookCopies.
            // reserveManager.isReserved(bookCopy)
        }
    }
}
