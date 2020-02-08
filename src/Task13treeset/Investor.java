package Task13treeset;

public class Investor implements Comparable{

    String name;
    int deposit;
    int percent;

    //все поля вкладчика будет устанавливаться при создании
    public Investor(String name, int deposit, int percent) {
        this.name = name;
        this.deposit = deposit;
        this.percent = percent;
    }

    @Override
    public int compareTo(Object o) {
        Investor inv = (Investor)o;
        return getName().compareTo(inv.getName());
    }

    @Override
    public String toString() {
        return "[ Вкладчик: " + name +
                "; Вклад: " + deposit +
                "; Ставка: " + percent +
                "% ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(short percent) {
        this.percent = percent;
    }
}
