package MyUtils;

import java.util.Scanner;
//в процессе выполнения задач методы, на которые повышенный спрос были выделены в отдельный данный класс
public class Utils {


    //метод запрашивает ввод числа, считывает его, производит проверку соответствия типа данных
    //при некорректном - метод повторно себя вызывает, но с другим, соответствующим сообщением
    public static double inputDouble(String inputMassage){
        System.out.println(inputMassage);
        Scanner scanner = new Scanner(System.in);
        boolean ok = scanner.hasNextDouble();
        double number;
        if (ok){
            number = scanner.nextDouble();
        }else {
            number = inputDouble("Повторите ввод");
        }
        return number;
    }

    //метод запрашивает ввод числа целого, считывает его, производит проверку соответствия типа данных
    //при некорректном - метод повторно себя вызывает, но с другим, соответствующим сообщением
    public static int inputInt(String message){
        System.out.println(message);
        Scanner size = new Scanner(System.in);
        boolean ok = size.hasNextInt();
        int number;
        if (ok){
            number = size.nextInt();
        }else {
            number = inputInt("Повторите ввод");
        }
        return number;
    }

    //данный метод запрашивает у пользователя ввод, и возвращает его при условии что введено целое положительное число
    //необходим для введения ширины и высоты графических фигур, которые могут быть только положительными
    public static int inputSize(String message){
        System.out.println(message);
        Scanner size = new Scanner(System.in);
        boolean ok = size.hasNextInt();
        int number;
        if (ok){
            number = size.nextInt();
            if( number<=0){
                number = inputSize("Повторите ввод");
            }
        }else {
            number = inputSize("Повторите ввод");
        }
        return number;
    }
}
