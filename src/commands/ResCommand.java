package commands;

import main.Book;
import main.Copy;
import main.Output;
import main.Reserve;
import main.ReserveManagerException;
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
            Output.error("Nenhum exemplar do livro '", book.getTitle(), "' está disponível para reserva.");;
            return;
        }

        try {
            reserveManager.addReserve(new Reserve(user, bookCopy));
            Output.success("Livro '", book.getTitle(), "' reservado ao usuário ", user.getName(), " com sucesso.");
        } catch (ReserveManagerException e) {
            Output.error(e.getMessage());
        }
    }
}
