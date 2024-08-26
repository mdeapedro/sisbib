package main;

public class Output {
    private static String successDiv = "\033[32m|\033[0m ";
    private static String errorDiv = "\033[31m|\033[0m ";
    private static String infoDiv = "\033[33m|\033[0m ";

    public static void success(String ...message) {
        System.out.print(successDiv);
        for (String string : message) {
            System.out.print(string);
        }
        System.out.println();
    }

    public static void info(String ...message) {
        System.out.print(infoDiv);
        for (String string : message) {
            System.out.print(string);
        }
        System.out.println();
    }

    public static void error(String ...message) {
        System.out.print(errorDiv);
        for (String string : message) {
            System.out.print(string);
        }
        System.out.println();
    }
}
