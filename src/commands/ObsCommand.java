package commands;

import main.Book;
import main.IObserver;
import main.Output;
import main.Sisbib;

public class ObsCommand implements ICommand {
    private IObserver observer;
    private Book book;

    public ObsCommand(IObserver observer, Book book) {
        this.observer = observer;
        this.book = book;
    }

    public void execute() {
        Sisbib.getInstance().getSimultaneousReservesNotifier().subscribe(observer, book);
        Output.success("Inscrição realizada com sucesso.");
    }
}
