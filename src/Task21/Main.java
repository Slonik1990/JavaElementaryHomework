package Task21;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src\\Task20InOut\\total.txt"));
        while (scanner.hasNext()){
            System.out.print(scanner.next());
        }
    }
}
