package commands;

import main.INotifiable;
import main.Output;

public class NtfCommand implements ICommand {
    private INotifiable notifiable;

    public NtfCommand(INotifiable notifiable) {
        this.notifiable = notifiable;
    }

    public void execute() {
        Output.success("O usuário foi notificado ", Integer.toString(notifiable.getNumberOfNotifications()), " vez(es).");
    }
}
