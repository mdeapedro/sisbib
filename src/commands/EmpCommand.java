package commands;

import main.Book;
import main.LoanerException;
import main.Output;
import users.IUser;

public class EmpCommand implements ICommand {
    private IUser user;
    private Book book;

    public EmpCommand(IUser user, Book book) {
        this.user = user;
        this.book = book;
    }

    public void execute() {
        try {
            user.getLoaner().performLoan(book);
            Output.success("Livro '", book.getTitle(), "' emprestado ao usuário '", user.getName(), "'");
        } catch (LoanerException e) {
            Output.error(e.getMessage());
        }
    }
}
