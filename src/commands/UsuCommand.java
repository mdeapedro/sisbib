package commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import main.LoanHistory;
import main.LoanHistoryManager;
import main.Output;
import main.Sisbib;
import users.IUser;

public class UsuCommand implements ICommand {
    private IUser user;

    public UsuCommand(IUser user) {
        this.user = user;
    }

    public void execute() {
        Sisbib sisbib = Sisbib.getInstance();
        LoanHistoryManager loanHistoryManager = sisbib.getLoanHistoryManager();
        List<LoanHistory> userLoanHistories = loanHistoryManager.getUserLoanHistories(user);

        Output.info("Consulta do usuário de código ", Integer.toString(user.getId()), ":");
        Output.info();
        Output.info(user.getName());
        Output.info();
        outputUserLoanHistories();
    }

    private void outputUserLoanHistories() {
        Output.info("Histórico de Empréstimos");

        List<LoanHistory> userLoanHistories = Sisbib.getInstance().getLoanHistoryManager().getUserLoanHistories(user);
        if (userLoanHistories.size() == 0) {
            Output.info("Nenhum empréstimo retornado");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (LoanHistory loanHistory : userLoanHistories) {
            Output.info(
                "Empréstimo do livro '",
                loanHistory.getLoan().getCopy().getBook().getTitle(),
                "' solicitado em ",
                loanHistory.getLoan().getCreationDate().format(formatter),
                ". Devolvido em ",
                loanHistory.getLoan().getReturnDate().format(formatter)
            );
        }

    }
}
