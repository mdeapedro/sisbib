package commands;

import java.time.LocalDate;
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
        Output.info("Consulta do usuário de código ", Integer.toString(user.getId()), ":");
        Output.info();
        Output.info(user.getName());
        Output.info();
        outputUserLoanHistories();
    }

    private void outputUserLoanHistories() {
        Output.info("• Histórico de Empréstimos");

        List<LoanHistory> userLoanHistories = Sisbib.getInstance().getLoanHistoryManager().getUserLoanHistories(user);
        if (userLoanHistories.size() == 0) {
            Output.info("    (nada)");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int index = 1;
        for (LoanHistory loanHistory : userLoanHistories) {
            LocalDate loanCreationDate = loanHistory.getLoan().getCreationDate();
            LocalDate loanReturnedOn = loanHistory.getReturnedOn();
            long overdue = loanReturnedOn.toEpochDay() - loanCreationDate.toEpochDay();
            String overdueInfo;
            if (overdue > 0) {
                overdueInfo = " (Atraso!)";
            } else {
                overdueInfo = "";
            }
            String indexString = Integer.toString(index);
            String indexSpacing = " ".repeat(indexString.length());
            Output.info();
            Output.info(indexString, " ─┬─ Livro: ", loanHistory.getLoan().getCopy().getBook().getTitle());
            Output.info(indexSpacing, "  ├─ Solicitado em: ", loanCreationDate.format(formatter));
            Output.info(indexSpacing, "  ├─ Devolução para: ", loanCreationDate.format(formatter));
            Output.info(indexSpacing, "  └─ Devolvido em: ", loanReturnedOn.format(formatter), overdueInfo);
            ++index;
        }
    }
}
