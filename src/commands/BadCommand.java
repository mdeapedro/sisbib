package commands;

public class BadCommand implements ICommand {
    private String message;

    public BadCommand(String message) {
        this.message = message;
    }

    public void execute() {
        System.err.println(message);
    }
}
