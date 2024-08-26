package commands;

import java.util.List;

import main.Book;
import main.Sisbib;

public class LivAllCommand implements ICommand {
    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        List<Book> books = sisbib.getBooks();
        System.out.println();
        System.out.println("O comando 'liv' espera 1 argumento: codigo_do_livro.");
        System.out.println();
        System.out.println("Livros cadastrados:");
        System.out.println();
        System.out.println("Código; Título");
        for (Book book : books) {
            System.out.print(book.getId());
            System.out.print("; ");
            System.out.println(book.getTitle());
        }
    }
}
