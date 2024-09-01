package commands;

import exceptions.ReserveManagerException;
import main.Book;
import main.Copy;
import main.Loan;
import main.Output;
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
            Output.error("Nenhum exemplar do livro '", book.getTitle(), "' está disponível para reserva.");;
            return;
        }
        
        for (Loan userLoan : sisbib.getLoanManager().getUserLoans(user)) {
            if (userLoan.getCopy().getBook().equals(book)) {
                Output.error("O usuário já possui um empréstimo em aberto do livro solicitado.");
                return;
            }
        }

        try {
            reserveManager.addReserve(new Reserve(user, bookCopy));
            Output.success("Livro '", book.getTitle(), "' reservado ao usuário ", user.getName(), " com sucesso.");
        } catch (ReserveManagerException e) {
            Output.error(e.getMessage());
        }
    }
}
