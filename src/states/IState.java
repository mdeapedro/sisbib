package states;

public interface IState {
    public void onEnter();
    public void onTick();
    public void onExit();
    public boolean isFinal();
}
