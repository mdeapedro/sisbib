package states;

public class LoadTestCaseState implements IState {
    public void onEnter() { }

    public void onTick() {
        this.loadUsers();
        this.loadBooks();
        this.loadCopies();
    }

    public void onExit() { }

    public boolean isFinal() {
        return false;
    }

    private void loadUsers() { }
    private void loadBooks() { }
    private void loadCopies() { }
}
