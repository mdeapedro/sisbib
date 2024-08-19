package states;

import commands.*;
import java.util.Scanner;

public class ReadCommandState implements IState {
    private Scanner scanner;

    public ReadCommandState() {
        this.scanner = new Scanner(System.in);
    }

    public void onEnter() {
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("                              SISBIB                            ");
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }

    public void onTick() {
        System.out.print("Digite um comando (emp, dev, res, obs, sai): ");
        this.getNextCommand().execute();
        System.out.println();
    }

    public void onExit() {
        System.out.println("Sayonara! (^ . ^)");
        this.scanner.close();
    }

    public boolean isDone() {
        return false;
    }

    private ICommand getNextCommand() {
        String tokens[] = this.scanner.nextLine().split(" ");
        switch (tokens[0]) {
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
