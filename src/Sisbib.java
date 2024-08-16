public class Sisbib {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Espera-se 3 argumentos: usuarios, livros e exemplares.");
        }
        LoadTestCases.loadTestCases(args[0], args[1], args[2]);
    }
}
