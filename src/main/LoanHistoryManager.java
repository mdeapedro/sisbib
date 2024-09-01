package main;

import java.util.ArrayList;
import java.util.List;

import users.IUser;

public class LoanHistoryManager {
    private List<LoanHistory> loanHistories = new ArrayList<>();

    public LoanHistoryManager() { }
    
    public void addLoanHistory(LoanHistory loanHistory) {
        loanHistories.add(loanHistory);
    }
    
    public List<LoanHistory> getUserLoanHistories(IUser user) {
        List<LoanHistory> userLoanHistories = new ArrayList<>();
        for (LoanHistory loanHistory : loanHistories) {
            if (loanHistory.getLoan().getUser().equals(user)) {
                userLoanHistories.add(loanHistory);
            }
        }
        return userLoanHistories;
    }
}
