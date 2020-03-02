package Task13Treeset;

public class Bank {
    private String name;
    private DataBase accounts;//база данных с информацмей о вкладах
    private int foundationYear;//год основания
    private int NumberOfDeposits;//количество вкладов

    public Bank(String name, int foundationYear) {
        this.name = name;
        this.accounts = new DataBase();
        this.foundationYear = foundationYear;

    }


    public void addInvestor(Investor investor){
        accounts.add(investor);
        System.out.println("Вклад добавлен\n");
    }

    //удаление по имени
    public void removeInvestor(String name){
        accounts.remove(name);

    }

    //данные о вкладчике по его имени
    public void showInvestor(String name){
       accounts.investorInfo(name);
    }


    //краткая информация о банке
    public void info(){
        System.out.println("Банк: " + name + "; \n" +
                "Основан в " + foundationYear + " году; \n" +
                "Количество вкладов: " + getNumberOfDeposits() + "; \n" +
                "Сумма вкладов: " + totalDeposit() + ";\n");

    }

    //полная информация
    public void totalInfo(){
        info();
        printInvestorsByName();
    }

    //следующие методы не вносят изменения в базу данных, не зависимо по какому принципу сортировки она основана
    public void printInvestorsByName(){
        DataBase buffer = new DataBase();
        buffer.addAll(accounts);
        System.out.println(buffer.toString());
    }
    public void printInvestorsByMoney(){
        DataBase buffer = new DataBase(new DepositComparator());
        buffer.addAll(accounts);
        System.out.println(buffer.toString());
    }


    //чтобы узнать количество вкладчиков, просто узнает размер базы данных
    public int getNumberOfDeposits() {
        return accounts.size();
    }

    //сумма всех вкладов
    public int totalDeposit(){
        return accounts.totalDepositVal();
    }

}
