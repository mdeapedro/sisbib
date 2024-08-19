package commands;
import main.Sisbib;
import states.EmptyState;

public class SaiCommand implements ICommand {
    public void execute() { 
        Sisbib sisbib = Sisbib.getInstance();
        sisbib.setState(new EmptyState());
    }
}
