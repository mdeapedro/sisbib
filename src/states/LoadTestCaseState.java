package states;

import main.Sisbib;

public class LoadTestCaseState implements IState {
    public void onEnter() { }

    public void onTick() {
        this.loadUsers();
        this.loadBooks();
        this.loadCopies();
        Sisbib.getInstance().setState(new ReadCommandState());
    }

    public void onExit() { }

    public boolean isFinal() {
        return false;
    }

    private void loadUsers() { }
    private void loadBooks() { }
    private void loadCopies() { }
}
