package commands;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.MockDate;
import main.Output;

public class MockCommand implements ICommand {
    private int day;
    private int month;
    private int year;
    
    public MockCommand(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void execute() {
        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            Output.error("Data inválida");
            return;
        }
        MockDate.setDate(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Output.success("Data do programa alterada para ", date.format(formatter));
    }
}
