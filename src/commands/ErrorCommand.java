package commands;

import main.Output;

public class ErrorCommand implements ICommand {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    public void execute() {
        Output.error(message);
    }
}
