package Task03;
import java.util.Scanner;
//if - else заменен на switch-case, что сделало код более читабельным
//проверка вводимых чисел и знака, а также требование повторного ввода при некорректности
//учтен принцип DRY и за ввод чисел теперь отвечает один метод вместо двух



public class Calculator {

    //метод запрашивает ввод числа, считывает его, производит проверку соответствия типа данных
    //при некорректном - метод повторно себя вызывает, но с другим, соответствующим сообщением
    private static double inputNumber(String inputMassage){
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


    // метод запрашивает и возвращает знак математической операции
    // считывает введенную пользователем информацию и проверяет на соответствие одному из знаков операций
    // если введен неверный символ, просит повторить
    private static String inputAction(String massage){
        System.out.println(massage);
        Scanner scanner = new Scanner(System.in);
        String op = scanner.next();
        if(op.equals("*")||op.equals("/")||op.equals("-")||op.equals("+")){

        } else {
            op =inputAction("Повторите ввод");
        }
        return op;
    }


    //вычислительная логика и вывод результата
    public static void calculation(){
        double first = inputNumber("Введите первое число");
        String action = inputAction("Укажите знак математической операции");
        double second = inputNumber("Введите второе число");

        switch (action){
            case "*":
                System.out.println("Результат: " + first*second);
                break;
            case "-":
                System.out.println("Результат: " + (first-second));
                break;
            case "+":
                System.out.println("Результат: " + (first+second));
                break;
            case "/":
                if(second!=0) {
                    System.out.println("Результат: " + first / second);
                }else {
                    System.out.println("Делить на ноль нельзя!!!");
                }
                break;
        }
    }
}
