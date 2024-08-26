package commands;

import java.util.List;

import main.Book;
import main.Output;
import main.Sisbib;

public class LivAllCommand implements ICommand {
    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        List<Book> books = sisbib.getBooks();
        Output.info("O comando 'liv' espera 1 argumento: codigo_do_livro.");
        Output.info();
        Output.info("Livros cadastrados:");
        Output.info();
        Output.info("Código; Título");
        for (Book book : books) {
            Output.info(Integer.toString(book.getId()), "; ", book.getTitle());
        }
    }
}
