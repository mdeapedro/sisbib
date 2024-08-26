package commands;

public class ErrorCommand implements ICommand {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    public void execute() {
        System.out.println(message);
    }
}
