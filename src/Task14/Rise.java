package Task14;

//подъем в экономике накинет денежек и процентов

public class Rise implements Situation{
    @Override
    public int moneyChange(int money) {
        return money + 100;
    }

    @Override
    public int percentChange(int percent) {
        return percent + 5;
    }
}
