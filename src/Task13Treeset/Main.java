package Task13Treeset;

public class Main {
    public static void main(String[] args) {
        Bank privat = new Bank("Приват", 2000);
        while (true) {
            try {
                Input.run(privat);
            } catch (UnsupportedOperationException e) {
                System.out.println("команда не поддерживается");
            } finally {
                Input.run(privat);
            }
        }

    }
}
