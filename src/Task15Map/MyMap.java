package Task15Map;
/**
 * Основан на массиве содержащем элементы ключ-значение
 * Не может содержать одинаковых ключей
 * Имеет коэффициент загрузки, для того, чтобы связные списки не замедляли чрезмерно программу
 * Ключ не может быть null
 * Значение может быть null
 * Индекс вычисляется на основе хэш кода
 * Новые элементы при занятой ячейке по вычисленному индексу вставляются в начало цепочки
 */


import java.util.*;

public class MyMap implements Map {
    private final double LOAD_KOEF = 1.0;   //предельное соотношение записей к размеру таблицы
    private final int INCREASE_KOEF = 2;
    private static final int MIN_POSSIBLE_CAPACITY = 16;
    private static final int MAX_POSSIBLE_CAPACITY = 16384;
    private Entry[] table;
    private int capacity;
    private int records;

    //дефолтный конструктор
    public MyMap() {
        this.capacity = MIN_POSSIBLE_CAPACITY;
        this.table = new Entry[capacity];

    }

    //конструктор с установкой размерности таблицы
    public MyMap(int capacity) {
        if (capacity < MIN_POSSIBLE_CAPACITY) {
            this.capacity = MIN_POSSIBLE_CAPACITY;
        } else if (capacity > MAX_POSSIBLE_CAPACITY) {
            this.capacity = MAX_POSSIBLE_CAPACITY;
        } else {
            this.capacity = capacity;
        }

        this.table = new Entry[this.capacity];
    }

    //количество записей
    @Override
    public int size() {
        return records;
    }

    //true если нет записей
    @Override
    public boolean isEmpty() {
        return records == 0;
    }


    //есть ли в таблице элемент с таким ключем
    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("NULL KEY NOT SUPPORTED");
        }
        Set set = keySet();
        for (Object o : set) {
            if (o.equals(key)) {
                return true;
            }
        }
        return false;
    }

    //есть ли в таблице элемент с таким значением
    @Override
    public boolean containsValue(Object value) {
        Collection col = values();
        for (Object o : col) {
            if (o == null) {
                if (value == null) {
                    return true;
                } else {
                    continue;
                }
            }

            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }


    //возвращает значение по ключу
    @Override
    public Object get(Object key) {
        if (key == null) {
            throw new NullPointerException("NULL KEY NOT SUPPORTED");
        }
        Entry current = table[getIndex(key)];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            } else {
                current = current.next;
            }

        }
        return null;
    }

    //вычисляет будущий индекс в таблице
    public int getIndex(Object key) {
        return Math.abs(key.hashCode()) % capacity;
    }


    //просто добавление, без проверки на перегрузку
    //данный метод необходим для отделения процесса добавдения объекта от проверки таблицы на перегрузку
    //это сделано для того, чтобы при добавлении группы объектов, проверка вызывалась после полного добавления
    //а не после каждого
    public Object justPut(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException("NULL KEY NOT SUPPORTED");
        }
        int index = getIndex(key);
        Entry current = table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                Object toReturn = current.getValue();
                current.setValue(value);
                return toReturn;
            } else {
                current = current.getNext();
            }
        }
        Entry created = new Entry(key, value);//новая нода
        created.setNext(table[index]);
        table[index] = created;
        records++;
        return null;
    }


    @Override
    public Object put(Object key, Object value) {
        Object toReturn = justPut(key, value);
        if (needIncrease()) {
            increase();
        }
        return toReturn;
    }

    @Override
    public void putAll(Map m) {
        Set buffer = m.entrySet();
        for (Object k : buffer) {
            justPut(((Entry) k).getKey(), ((Entry) k).getValue());
        }
        while (needIncrease()) {
            increase();
        }
    }

    //true если нужно увеличить и размер еще не максимальный
    //false если не нужно или достигнут максимальный размер
    public boolean needIncrease() {
        if ((double) records / (double) capacity > LOAD_KOEF && capacity < MAX_POSSIBLE_CAPACITY) {
            return true;
        } else {
            return false;
        }
    }


    //увеличивает объем таблицы если  метод needIncrease() подтвердил необходимость этого
    public void increase() {
        MyMap buffer;
        if (this.capacity * INCREASE_KOEF > MAX_POSSIBLE_CAPACITY) {
            buffer = new MyMap(MAX_POSSIBLE_CAPACITY);
        } else {
            buffer = new MyMap(this.capacity * INCREASE_KOEF);
        }

        buffer.putAll(this);
        this.table = buffer.table;
        this.capacity = buffer.capacity;
        this.records = buffer.records;
    }


    //удаление по ключу с возвратом удаленного значения
    @Override
    public Object remove(Object key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return null;
        }
        if (table[index].key.equals(key)) {
            Object toReturn = table[index].getValue();
            table[index] = table[index].next;
            records--;
            return toReturn;
        }
        Entry current = table[index];
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                Object toReturn = current.next.getValue();
                current.setNext(current.next.next);
                records--;
                return toReturn;
            } else {
                current = current.getNext();
            }
        }
        return null;
    }


    //очищение
    @Override
    public void clear() {
        this.capacity = MIN_POSSIBLE_CAPACITY;
        this.table = new Entry[capacity];
        records = 0;

    }

    //методы просмотра и итерации
    @Override
    public Set keySet() {
        Set<Object> set = new TreeSet<>();
        for (Entry entry : table) {
            while (entry != null) {
                set.add(entry.key);
                entry = entry.getNext();
            }
        }
        return set;
    }

    @Override
    public Collection values() {
        Collection col = new ArrayList();
        for (Entry entry : table) {
            while (entry != null) {
                col.add(entry.value);
                entry = entry.getNext();
            }
        }
        return col;
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> set = new HashSet<>();
        for (Entry entry : table) {
            while (entry != null) {
                set.add(entry);
                entry = entry.getNext();
            }
        }
        return set;
    }


    public int getCapacity() {
        return capacity;
    }

    public Entry[] getTable() {
        return table;
    }

    public static int minCapacity() {
        return MIN_POSSIBLE_CAPACITY;
    }

    public static int maxCapacity() {
        return MAX_POSSIBLE_CAPACITY;
    }

    class Entry {
        private Object key;
        private Object value;
        private Entry next;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        @Override
        public String toString() {
            String s;
            if (value == null) {
                s = null;
            } else {
                s = value.toString();
            }
            return " [" + key.toString() + " - " + s + "]";
        }
    }
}
