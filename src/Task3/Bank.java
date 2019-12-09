package Task3;

public class Bank {

    public static void deposit(){

        System.out.println();
        System.out.println("Данная программа производит расчеты \nдля вклада накопительного типа");
        System.out.println();

        double deposit = Utils.inputDouble("Сумма вклада, грн");
        double proc = Utils.inputDouble("Годовой процент");
        double years = Utils.inputDouble("Срок, лет");
        double firstMoney = deposit;

        for (int i = 1; i <= years; i++) {
            double prof = deposit*proc/100;
            System.out.println("Прибыль за " +  i + " год: " + prof + " грн");
            deposit = deposit + prof;
        }
        System.out.println("\nСумма на вашем балансе: " + deposit + " грн");

        double totalProf = (deposit-firstMoney)/firstMoney*100;

        System.out.println("\nЗа данный срок Ваш вклад вырос на " + totalProf + " %");



    }
}
