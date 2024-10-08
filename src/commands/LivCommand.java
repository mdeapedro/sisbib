package commands;

import java.util.List;
import java.time.format.DateTimeFormatter;

import main.Book;
import main.Copy;
import main.Loan;
import main.LoanManager;
import main.Output;
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
        LoanManager loanManager = sisbib.getLoanManager();
        ReserveManager reserveManager = sisbib.getReserveManager();

        Output.info("Consulta do livro de código ", Integer.toString(book.getId()), ":");
        Output.info();
        Output.info(book.getTitle());
        Output.info();
        Output.info("Código do exemplar; Status");

        List<Copy> bookCopies = sisbib.getBookCopies(book);

        for (Copy bookCopy : bookCopies) {
            Loan loan = loanManager.getLoanByCopy(bookCopy);
            Reserve reserve = reserveManager.getReserveByCopy(bookCopy);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if (loan != null) {
                Output.info(Integer.toString(bookCopy.getId()), "; Emprestado para ", loan.getUser().getName(), " até ",
                        loan.getReturnDate().format(formatter));
            } else if (reserve != null) {
                Output.info(Integer.toString(bookCopy.getId()), "; Reservado em ",
                        reserve.getCreationDate().format(formatter), " para ", reserve.getUser().getName());
            } else {
                Output.info(Integer.toString(bookCopy.getId()), "; Disponível");
            }
        }
    }
}
