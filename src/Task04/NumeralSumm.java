package Task04;
import MyUtils.Utils;



public class NumeralSumm {
    public static void main(String[] args) {
        numSum();
    }

    //метод запрашивает целое положительное или отрицательное число и выводит сумму цифр его абсолютного значения
    public static void numSum(){
        int number = Math.abs(Utils.inputInt("Введите число, сумма цифр которого вас интересует"));
        int summ = 0;
        do{summ = summ + number%10;//цифра в наименьшем разряде записывается в сумму
            number=number/10; //из числа убирается наименьший разряд

        }while(number!=0);//если результат прировняется к нулю, все разряды пройдены и цикл остановится

        System.out.println("Сумма цифр данного числа составляет: " + summ);
    }

}
