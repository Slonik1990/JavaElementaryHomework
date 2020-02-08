package Task13treeset;

public class Main {
    public static void main(String[] args) {


        Bank privat = new Bank("Приват", 2000);
//       Input.run(privat);
       privat.addInvestor(new Investor("Вова", 10000, 10));
       privat.addInvestor(new Investor("Ваня", 10000, 10));
       privat.addInvestor(new Investor("Витя", 10000, 10));
       privat.addInvestor(new Investor("Виталик", 10000, 10));

       privat.info();
       privat.removeInvestor("Вова");
       privat.info();
       privat.removeInvestor("Витя");
       privat.info();
       privat.removeInvestor("Ваня");
       privat.info();
       privat.removeInvestor("Виталик");
       privat.info();

       privat.addInvestor(new Investor("Валентин", 10000, 10));
       privat.info();
    }
}
