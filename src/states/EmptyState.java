package states;

public class EmptyState implements IState {
    public void onEnter() { }

    public void onTick() { }

    public void onExit() { }

    public boolean isFinal() {
        return true;
    }
}
