package main;

import java.time.LocalDate;

public class MockDate {
    private static MockDate instance;
    private static LocalDate date;

    private MockDate() {
        instance = this;
        date = LocalDate.now();
    }

    public static LocalDate now() {
        if (instance == null) {
            instance = new MockDate();
        }
        return date;
    }
    
    public static void setDate(LocalDate date) {
        if (instance == null) {
            instance = new MockDate();
        }
        MockDate.date = date;
    }
}
