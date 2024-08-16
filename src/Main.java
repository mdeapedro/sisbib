public class Main {
    public static void main(String[] args) {
        Sisbib sisbib = Sisbib.getInstance();
        while (!sisbib.isInFinalState()) {
            sisbib.tick();
        }
    }
}
