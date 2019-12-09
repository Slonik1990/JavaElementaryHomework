package Task3;

import java.util.Scanner;

//в процессе выполнения задач методы, на которые повышенный спрос были выделены в отдельный данный класс

public class Utils {

    //метод запрашивает ввод числа, считывает его, производит проверку соответствия типа данных
    //при некорректном - метод повторно себя вызывает, но с другим, соответствующим сообщением
    public static double inputNumber(String inputMassage){
        System.out.println(inputMassage);
        Scanner scanner = new Scanner(System.in);
        boolean ok = scanner.hasNextDouble();
        double number;
        if (ok){
            number = scanner.nextDouble();
        }else {
            number = inputNumber("Повторите ввод");
        }
        return number;
    }
}
