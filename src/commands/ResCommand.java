package commands;

import main.Book;
import main.Copy;
import main.Reserve;
import main.ReserveManager;
import main.Sisbib;
import users.IUser;

public class ResCommand implements ICommand {
    private IUser user;
    private Book book;

    public ResCommand(IUser user, Book book) {
        this.user = user;
        this.book = book;
    }

    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        
        ReserveManager reserveManager = sisbib.getReserveManager();
        
        Copy bookCopy = reserveManager.getAvaiableBookCopy(book);
        if (bookCopy == null) {
            String message = "Todos os livros de título ";
            message += book.getTitle();
            message += " já foram emprestados.";
            System.err.println(message);
            return;
        }

        try {
            reserveManager.addReserve(new Reserve(user, bookCopy));
            String message = "Livro '";
            message += book.getTitle();
            message += "' reservado ao usuário ";
            message += user.getName();
            message += " com sucesso.";
            System.out.println(message);
        } catch (Error error) {
            System.err.println(error.getMessage());
        }
    }
}
