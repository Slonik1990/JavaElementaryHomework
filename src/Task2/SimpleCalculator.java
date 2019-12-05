package Task2;

import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {

        //объявление переменных, которым присвоены данные, введенные пользователям
        double first = inputFirst();
        String action = inputAction();
        double second = inputSecond();


        //блок содержащий вычислительную логику на основе оператора if-else-if и выводящий результат на экран
        if(action.equals("-")){
            System.out.println("Результат: " + (first - second));
        }else if(action.equals("+")){
            System.out.println("Результат: " + (first+second));
        } else if(action.equals("*")){
            System.out.println("Результат: " + first*second);
        } else if(action.equals("/")){
            //исправлен баг возникающий при делении на 0
            if(second!=0) {
                System.out.println("Результат: " + first / second);
            }else{
                System.out.println("Делить на ноль нельзя!!!");

            }
        }

    }

    //метод возвращает число  введенное пользователем
    private static double inputFirst(){
        System.out.println("Введите первое число");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    //метод возвращает число  введенное пользователем
    private static double inputSecond(){
        System.out.println("Введите второе число");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    //метод возвращает считанную до первого пробела или перехода на новую строку информацию от пользователя
    private static String inputAction(){
        System.out.println("Укажите знак математической операции");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
