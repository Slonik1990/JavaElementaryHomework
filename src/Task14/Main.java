package Task14;

import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {


        BankDataBase privat = new BankDataBase();


        Investor inv1 = new Investor("Иван", 1000, 10);
        Investor inv2 = new Investor("Петр", 5000, 13);
        Investor inv3 = new Investor("Анна", 500000, 20);
        Investor inv4 = new Investor("Федор", 12345, 14);


        privat.add(inv1);
        privat.add(inv2);
        privat.add(inv3);
        privat.add(inv4);

        privat.add(new Investor("Борис", 3000, 11));
        privat.add(new Investor("Дарья", 1000000, 50));
        privat.add(new Investor("Илья", 10203, 11));
        System.out.println(privat.size());

        System.out.println(privat);

        System.out.println(privat.contains("дарья"));//false
        System.out.println(privat.contains("Дарья"));//true
        System.out.println(privat.containsInvestor(inv1));//false
        System.out.println(privat.containsInvestor(inv2));//true

        Object[] arr = privat.toArray();

        for (Object o: arr){
            System.out.println((Investor)o);
        }

        Investor ivan = privat.get("Иван");
        System.out.println(ivan);//[ Вкладчик: Иван; Вклад: 1000; Ставка: 10% ]

        ivan.setPercent(15);
        System.out.println(ivan);//[ Вкладчик: Иван; Вклад: 1000; Ставка: 15% ]

        privat.setBankChange(new Collapse());

        System.out.println(privat);//все обнулилось



    }
}