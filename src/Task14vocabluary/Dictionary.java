package Task14vocabluary;

import java.util.Iterator;

//доработанная версия, в отличии от предыдушей может принимать разные алгоритмы хэширования
//поддерживает цикл foreach
//возможно в чем - то данная гибкость является излишней, но мне захотелось поработать с гибкой реализацией

public class Dictionary implements HashTableDictionary {

    private Hasher hasher;
    private int size;
    private Entry[] table = new Entry[0];

    //конструктор c генератором хэш кода по умолчанию
    public Dictionary() {
        this.hasher = new MyHash();
    }

    //конструктор принимающий генератор хэш кода
    public Dictionary(Hasher h) {
        this.hasher = h;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int tableSize() {
        return table.length;
    }

    @Override
    public String get(String key) {
        if (getHashCode(key) >= tableSize()) {
            return null;
        }
        int index = getHashCode(key);
        Entry current = table[index];

        while (current != null) {
            if (key.equalsIgnoreCase(current.getEng())) {
                return current.getRus();
            } else {
                current = current.getNext();
            }
        }
        return null;
    }

    @Override
    public boolean put(String key, String value) {
        int index = getHashCode(key);//вычисление HashCode
        //увеличение таблицы если нужно
        if (index >= tableSize()) {
            tableIncrease(index);
        }

        Entry created = new Entry(key, value);//новая нода
        if (table[index] == null) {
            table[index] = created;
            size++;
            return false;
        } else {
            Entry current = table[index];
            //цикл оборачивается пока не встретит ноду с таким же ключем, либо дойдет последней ноды
            while (current.next != null && created.eng.equalsIgnoreCase(current.eng)) {
                current = current.getNext();
            }

            //перезапись значения при встрече такого же ключа либо добавление в список
            if (created.eng.equalsIgnoreCase(current.eng)) {
                String lost = current.getRus();
                current.setRus(created.getRus());
                return true;
            } else {
                current.setNext(created);
                size++;
                return false;
            }
        }
    }

    @Override
    public boolean remove(String key) {
        int index = getHashCode(key);
        //ситуации когда соответствующая ячейка пуста, либо вообще не существует
        if (tableSize() <= index & table[index] == null) {
            return false;
        }
        Entry current = table[index];

        //ситуация, при которой искомый ключ является первым элементом списка
        if (current.getEng().equalsIgnoreCase(key)) {
            table[index] = current.getNext();
            size--;
            return true;
        //не первым элементом
        } else {
            while (current.getNext()!=null){
                if(current.getNext().getEng().equalsIgnoreCase(key)){
                    current.setNext(current.getNext().getNext());
                    size--;
                    return true;
                } else{
                    current = current.getNext();
                }
            }

        }
        return false;
    }

    @Override
    public boolean containsKey(String key) {
        int index = getHashCode(key);//вычисление HashCode
        if (index >= tableSize()) {
            return false;
        }
        Entry current = table[index];
        while (current != null) {
            if (key.equalsIgnoreCase(current.getEng())) {
                return true;
            } else {
                current = current.getNext();
            }
        }
        return false;

    }

    @Override
    public boolean containsValue(String value) {
        Iterator iter = iterator();
        Entry cursor;
        while (iter.hasNext()) {
            cursor = (Entry) iter.next();
            if (value.equals(cursor.rus)) {
                return true;
            }
        }
        return false;
    }

    private void tableIncrease(int capacity) {
        Entry[] arr = new Entry[capacity + 1];
        for (int i = 0; i < table.length; i++) {
            arr[i] = table[i];
        }
        table = arr;
    }

    @Override
    public void clear() {
        table = new Entry[0];
    }

    @Override
    public int getHashCode(String key) {
        return hasher.getHash(key);
    }

    @Override
    public Iterator iterator() {
        return new DictIterator();
    }

    //нода
    static class Entry {
        private String rus;
        private String eng;
        private Entry next;

        public Entry(String eng, String rus) {
            this.eng = eng;
            this.rus = rus;
        }

        @Override
        public String toString() {
            return " [" + eng + " - " + rus + "] ";
        }

        public String getRus() {
            return rus;
        }

        public String getEng() {
            return eng;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public void setRus(String rus) {
            this.rus = rus;
        }
    }

    //итератор
    class DictIterator implements Iterator<Entry> {
        int currentIndex;
        Entry current;
        Entry last;

        //конструктор находит первый элемент в таблице
        public DictIterator() {
            if (size == 0) {
                System.out.println("Нельзя создать итератор для пустой таблицы");
                return;
            }
            for (int i = 0; i < tableSize(); i++) {
                if (table[i] != null) {
                    this.currentIndex = i;
                    break;
                }
            }
            this.current = table[currentIndex];
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Entry next() {
            if (!hasNext()) return null;
            last = current;//сохранение для возврата и начало поиска следующего

            //в первую очередь проверяется возможность продвижения внутри индекса по связному списку
            if (current.next != null) {
                current = current.getNext();

            } else {//поиск следующей не пустой ячейки таблицы
                for (int i = currentIndex + 1; i <= tableSize(); i++) {
                    if (i == tableSize()) {
                        current = null;
                        break;
                    } else if (table[i] != null) {
                        current = table[i];
                        currentIndex = i;
                        break;
                    }
                }
            }
            return last;
        }
    }
}
