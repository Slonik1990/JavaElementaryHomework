package Task14;

public class Investor {
    private String name;
    private int money;
    private int percent;

    //конструктор
    public Investor(String name, int money, int percent) {
        this.name = name;
        this.money = money;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "[ Вкладчик: " + name +
                "; Вклад: " + money +
                "; Ставка: " + percent +
                "% ]";
    }

    //геттеры, сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
