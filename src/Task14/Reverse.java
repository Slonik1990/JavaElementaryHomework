package Task14;

import java.util.Comparator;

//реверсивный компаратор который посылает в неизменную логику базы данных, противоположные результаты сравнений
//реализация паттерна стратегия, при которой изменимая логика выносится в поле, описуемое другим классом
public class Reverse implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Comparable node1 = (BankDataBase.Account)o1;
        Comparable node2 = (BankDataBase.Account)o2;
        return node2.compareTo(node1);
    }
}
