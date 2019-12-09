package Task3;
import java.util.Scanner;

//в данном классе представлены методы связанные с числами
// два метода, расчитывающие среднее арифметическое: для двух чисел и для произвольного количества
//и методы определяющие простоту, четность и кратные числа

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

        double a = Utils.inputDouble("Первое число");
        double b = Utils.inputDouble("Второе число");
        System.out.println("Среднее арифметическое для данных двух чисел = " + (a+b)/2);
    }


    //метод проверяет четность числа
    public static void parity () {
        int number = Utils.inputInt("Введите целое число");
        if (number%2==0){
            System.out.println("Данное число является четным");
        } else {
            System.out.println("Данное число является нечетным");
        }

    }


    //проверяет простое ли введенное число и выводит отчет на экран
    public static void simplity () {
        int number = Utils.inputInt("Введите целое число");
        if(number<2){
            System.out.println("Данное число не является простым, т.к. оно меньше 2");
        }else{
            for (int i = 2; i < 10; i++) {
                if(number%i==0&&number!=i){
                    System.out.println("Данное число не является простым");
                    break;
                }else{
                    if(i==9){
                        System.out.println("Число простое");
                    }
                }
            }
        }
    }

    //более продвинутый метод, который выводит на экран числа, которым кратно введенное число
    //а так же ведет подсчет кратных чисел, и при их отсутствии сообщает что число простое
    public static void quantity(){
        int x = Utils.inputInt("Введите число");
        int y = 0;
        System.out.println("Число кратно следующим числам:");
        for (int i = 2; i < x; i++) {
            if(x%i == 0) {
                System.out.println(i);
                y++;
            }
        }if(y==0){
            System.out.println("Число простое и безостаточных делителей не имеет");
        }else{
            System.out.println("Число составное");
        }

    }


}
