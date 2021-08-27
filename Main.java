package bullscows;


public class Main {
    public static void main(String[] args) {
        String code = Game.createCode();
        String[] test = code.split("");
        if (!test[0].equals("E")) {
            int count = 1;
            while (true) {
                System.out.printf("Turn %d:\n", count);
                if (Game.codeChecker(code)) {
                    break;
                }
                count++;
            }
        }
        else {
            System.out.println(code);
        }
    }
}
