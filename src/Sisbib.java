import states.IState;
import states.LoadTestCaseState;

public class Sisbib {
    private static Sisbib instance;
    private IState state;

    private Sisbib() {
        this.state = new LoadTestCaseState();
        this.state.onEnter();
    }

    public static Sisbib getInstance() {
        if (instance == null) {
            instance = new Sisbib();
        }
        return instance;
    }

    public boolean isInFinalState() {
        return this.state.isFinal();
    }

    public void setState(IState state) {
        this.state.onExit();
        this.state = state;
        this.state.onEnter();
    }

    public void tick() {
        this.state.onTick();
    }
}
