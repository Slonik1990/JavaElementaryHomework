package Task14;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Comparator reverseName = new Reverse();
        BankDataBase privat = new BankDataBase();
        BankDataBase ukrsib = new BankDataBase();


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

        System.out.println(privat.size());//7

        System.out.println(privat);

        System.out.println(privat.contains("дарья"));//false
        System.out.println(privat.contains("Дарья"));//true

        Object[] arr = privat.toArray();

        System.out.println();
        for (Object o: arr){
            System.out.println(o);
        }

        Investor ivan = privat.get("Иван");
        System.out.println();
        System.out.println(ivan);//[ Вкладчик: Иван; Вклад: 1000; Ставка: 10% ]

        ivan.setPercent(15);
        System.out.println(ivan);//[ Вкладчик: Иван; Вклад: 1000; Ставка: 15% ]

        ukrsib.add(new Investor("Кирилл", 30000,7 ));
        ukrsib.add(new Investor("Вадим", 43443,18 ));
        System.out.println(ukrsib);

        privat.addAll(ukrsib);
        System.out.println(privat);


        System.out.println(privat.containsAll(ukrsib));

        privat.setBankChange(new Collapse());
        System.out.println();
        System.out.println(privat);//все обнулилось
        System.out.println(privat.size());






    }
}
