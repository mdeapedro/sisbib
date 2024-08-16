import java.util.Scanner;
import commands.*;

public class Engine {
    private static Engine instance;
    private Scanner scanner;
    
    private Engine(Scanner scanner) {
        this.scanner = scanner;
    }

    public static Engine getInstance() {
        if (instance == null) {
            Scanner scanner = new Scanner(System.in);
            instance = new Engine(scanner);
        }
        return instance;
    }

    public void executeNextCommand() {
        ICommand command = this.getNextCommand();
        command.execute();
    }

    private ICommand getNextCommand() {
        String nextToken = this.scanner.next();
        switch (nextToken) {
            case "emp":
                return new EmpCommand();
            case "dev":
                return new DevCommand();
            case "res":
                return new ResCommand();
            case "obs":
                return new ObsCommand();
            case "sai":
                return new SaiCommand();
            default:
                return new BadTokenCommand();
        }
    }
}
