package Task15Upgrade;

//интерфейс определяет методы, необходимые для подобных словарей и попутно приплетает итератор и for each

public interface HashTableDictionary <E> extends Iterable <E>{

    int size();

    int tableSize();

    String get(String key);

    boolean put(String key, String value);

    boolean remove(String key);

    boolean containsKey(String key);

    boolean containsValue(String value);

    void tableIncrease(int capacity);

    void clear();

    int getHashCode(String key);
}
