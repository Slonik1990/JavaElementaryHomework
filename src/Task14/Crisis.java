package Task14;


//во время кризиса банк немного ограбит вкладчиков
public class Crisis implements Situation {
    @Override
    public int moneyChange(int money) {
        return money - 50;
    }

    @Override
    public int percentChange(int percent) {
        return percent - 3;
    }
}
