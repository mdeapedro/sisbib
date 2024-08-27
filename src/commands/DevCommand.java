package commands;

import java.util.List;

import main.Book;
import main.Loan;
import main.LoanManager;
import main.Output;
import main.Sisbib;
import users.IUser;

public class DevCommand implements ICommand {
    private IUser user;
    private Book book;

    public DevCommand(IUser user, Book book) {
        this.user = user;
        this.book = book;
    }

    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        LoanManager loanManager = sisbib.getLoanManager();

        Loan loan = loanManager.getLoanByUserAndBook(user, book);
        if (loan == null) {
            Output.error("O usuário '", user.getName(), "' não tem empréstimos em aberto para o livro '", book.getTitle(), "'");
            return;
        }

        loanManager.removeLoan(loan);
        Output.success("O usuário '", user.getName(), "' devolveu o livro '", book.getTitle(), "' com sucesso");
    }
}
