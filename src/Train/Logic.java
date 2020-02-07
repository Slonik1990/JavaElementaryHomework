package Train;

import java.util.Scanner;

public class Logic {
    public static void lohika(int x, int y) {

        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.equalsIgnoreCase("+")) {
            System.out.println(Mathem.add(x, y));
        } else if (s.equalsIgnoreCase("-")) {
            System.out.println(Mathem.minus(x, y));
        } else if (s.equalsIgnoreCase("/")) {
            System.out.println(Mathem.div(x, y));
        } else if (s.equalsIgnoreCase("*")) {
            System.out.println(Mathem.mul(x, y));
        } else {
            System.out.println(s);
        }
    }
}


