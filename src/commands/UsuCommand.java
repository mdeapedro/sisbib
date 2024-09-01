package commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import main.Loan;
import main.LoanHistory;
import main.MockDate;
import main.Output;
import main.Reserve;
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
        Output.info();
        outputUserCurrentLoans();
        Output.info();
        outputUserReserves();
    }

    private void outputUserLoanHistories() {
        Output.info("• Histórico de empréstimos");

        List<LoanHistory> userLoanHistories = Sisbib.getInstance().getLoanHistoryManager().getUserLoanHistories(user);
        if (userLoanHistories.isEmpty()) {
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
            Output.info(indexString, " ─┬─ ", loanHistory.getLoan().getCopy().getBook().getTitle());
            Output.info(indexSpacing, "  ├─ Solicitado em: ", loanCreationDate.format(formatter));
            Output.info(indexSpacing, "  ├─ Devolução para: ", loanHistory.getLoan().getReturnDate().format(formatter));
            Output.info(indexSpacing, "  └─ Devolvido em: ", loanReturnedOn.format(formatter), overdueInfo);
            ++index;
        }
    }
    
    private void outputUserCurrentLoans() {
        Output.info("• Empréstimos em andamento");
        
        List<Loan> userLoans = Sisbib.getInstance().getLoanManager().getUserLoans(user);
        if (userLoans.isEmpty()) {
            Output.info("    (nada)");
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int index = 1;
        for (Loan loan : userLoans) {
            long overdue = MockDate.now().toEpochDay() - loan.getReturnDate().toEpochDay();
            String overdueInfo;
            if (overdue > 0) {
                overdueInfo = " (Atraso!)";
            } else {
                overdueInfo = "";
            }
            String indexString = Integer.toString(index);
            String indexSpacing = " ".repeat(indexString.length());
            Output.info();
            Output.info(indexString, " ─┬─ ", loan.getCopy().getBook().getTitle());
            Output.info(indexSpacing, "  ├─ Solicitado em: ", loan.getCreationDate().format(formatter));
            Output.info(indexSpacing, "  └─ Devolução para: ", loan.getReturnDate().format(formatter), overdueInfo);
            ++index;
        }
    }
    
    private void outputUserReserves() {
        Output.info("• Reservas do usuário");

        List<Reserve> userReserves = Sisbib.getInstance().getReserveManager().getUserReserves(user);
        if (userReserves.isEmpty()) {
            Output.info("    (nada)");
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int index = 1;
        for (Reserve reserve : userReserves) {
            String indexString = Integer.toString(index);
            String indexSpacing = " ".repeat(indexString.length());
            Output.info();
            Output.info(indexString, " ─┬─ ", reserve.getCopy().getBook().getTitle());
            Output.info(indexSpacing, "  └─ Solicitado em: ", reserve.getCreationDate().format(formatter));
            ++index;
        }
    }
}
