package states;

import commands.*;
import java.util.Scanner;

public class ReadCommandState implements IState {
    private Scanner scanner;

    public ReadCommandState() {
        this.scanner = new Scanner(System.in);
    }

    public void onEnter() {  }

    public void onTick() {
        this.getNextCommand().execute();
    }

    public void onExit() {
        this.scanner.close();
    }

    public boolean isFinal() {
        return false;
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
