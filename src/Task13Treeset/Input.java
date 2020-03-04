package Task13Treeset;

import java.util.Scanner;

public class Input {

    public static void run(Bank bank) {

        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        switch (command) {
            case "info":
                bank.info();
                break;
            case "totalinfo":
                bank.totalInfo();
                break;
            case "bynames":
                bank.printInvestorsByName();
                break;
            case "deposit":
                System.out.println("Введит имя вкладчика, о котором нужна информация");
                String nname = scan.next();
                bank.showInvestor(nname);
                break;
            case "bymoney":
                bank.printInvestorsByMoney();
                break;
            case "clients":
                System.out.println(bank.getNumberOfDeposits());
                break;
            case "total":
                System.out.println(bank.totalDeposit());
                break;
            case "put":
            case "add":
                System.out.println("Введите имя вкладчика для создания вклада");
                String name = scan.next();
                System.out.println("Сумма вклада");
                int money = scan.nextInt();
                System.out.println("Годовой процент");
                int percent = scan.nextInt();
                bank.addInvestor(new Investor(name, money, percent));
                break;
            case "remove":
                System.out.println("Введите имя вкладчика, которого необходимо удалить");
                String s = scan.next();
                bank.removeInvestor(s);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}

