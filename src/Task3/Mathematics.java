package Task3;
import java.util.Scanner;

//в данном классе представлены два метода, расчитывающие среднее арифметическое: для двух чисел и для произвольного количества
public class Mathematics {




//метод будет запрашивать и считывать числа, до момента пока не произойдет ввод другого типа данных, после чего посчитает
// и выведет на экран среднее арифметическое введенных чисел
//при вводе менее двух чисел, сообщит о неуместности данного мероприятия

    public  static void mediumNumber(){
        int numbers = 0;   //счетчик количества чисел
        double total = 0;  //сумма всех введенных чисел

        System.out.println("Введите числа, для которых желаете узнать среднее арифметическое");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextDouble()){
            double present = scanner.nextDouble();
            total = total + present;
            numbers++;
        }if(numbers>=2) {
            System.out.println("Среднее арифметическое введенных чисел: " + total / numbers);
        } else{
            System.out.println("Для расчета необходимо минимум 2 числа");
        }
    }



    //метод расчитывает среднеарифметическое значение для двух чисел
    public  static void mediumFromTwo (){

        double a = Utils.inputNumber("Первое число");
        double b = Utils.inputNumber("Второе число");
        System.out.println("Среднее арифметическое для данных двух чисел = " + (a+b)/2);
    }
}
