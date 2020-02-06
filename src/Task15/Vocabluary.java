package Task15;

import java.util.*;

/**
 * Данный класс представляет собой Массив объектов класса Entry,
 * каждый из которых содержит слово на английском в качестве ключа
 * и слово на русском в качестве значения.
 * При добавлении в таблицу, каждый ключ кодируется особым образом в HashCode,
 * и помещается в ячейку массива с таким индексом.
 * <p>
 * Для случаев, когда у разных объектов совпадет хэш код в Entry предусмотрена сслыка на следующий объект,
 * что по сути создает связный список из объектов с одинаковым хэш кодом.
 * <p>
 * В данной реализации объекты у которых совпал хэш код и совпал ключ будут перезаписываться, то есть у одного
 * слова будет только один возможный перевод.
 * <p>
 * В случае, если  временная сложность программы сильно возрастет из-за связных списков,
 * всегда можно усложнить алгоритм формирования хэш кода и создать большее количество возможных значений, что сведет
 * наличие таких списков к минимуму, но это повлечет возрастание затрат памяти. Очередной пример вечного противостояния
 * производительность-память.
 * <p>
 * Так же при формировании алгоритма хэширования следует учитывать процент комбинаций, которые никогда не сгенерируются.
 * В данной реализации они состовляют прмерно 21,2%, что довольно неплохо.
 */


public class Vocabluary implements Map, Iterable {

    private int size;
    private Entry[] table = new Entry[0];//размер по умолчанию


    //конструкторы
    public Vocabluary() {
    }

    public Vocabluary(int capacity) {
        this.table = new Entry[capacity];
    }

    @Override
    public Iterator iterator() {
        return new VocabluaryIterator();
    }

    public int getTableSize() {
        return table.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }
        int index = getHash((String) key);//вычисление HashCode
        if (index >= getTableSize()) {
            return false;
        }
        String k = (String) key;
        Entry current = table[index];
        while (current != null) {
            if (k.equalsIgnoreCase(current.getEng())) {
                return true;
            } else {
                current = current.getNext();
            }
        }
        return false;

    }

    @Override
    public boolean containsValue(Object value) {
        Iterator iter = iterator();
        String v = (String)value;
        Entry cursor;
        while(iter.hasNext()){
            cursor = (Entry)iter.next();
            if(value.equals(cursor.rus)){
                return true;
            }
        }
        return false;
    }

    //возвращает значение по ключу
    @Override
    public Object get(Object key) {
        if (key == null) {
            return null;
        }
        int index = getHash((String) key);//вычисление HashCode
        if (index >= getTableSize()) {
            return null;
        }
        String keyWord = (String) key;
        Entry current = table[index];
        while (current != null) {
            if (keyWord.equalsIgnoreCase(current.getEng())) {
                return current.getRus();
            } else {
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Метод генерирует хэш код на основе ключа, после чего проверяет размерность таблицы.
     * При размерности таблицы меньшей чем хэшкод, увеличивает таблицу.
     * <p>
     * Если ячейка table[hashcode] пуста, добавляет туда ноду, если там уже находится объект,
     * проходит по связному списку сравнивая ключи. При обнаружении такого, же ключа перезаписывает значение, а старое
     * возвращает. При отсутствии такого же ключа в списке, добавляется в качестве нового элемента списка и возвращает null
     */
    @Override
    public Object put(Object key, Object value) {
        if (key == null) {
            return null;
        }
        int index = getHash((String) key);//вычисление HashCode
        //увеличение таблицы если нужно
        if (index >= getTableSize()) {
            tableIncrease(index);
        }

        Entry created = new Entry((String) key, (String) value);//новая нода
        if (table[index] == null) {
            table[index] = created;
            size++;
            return null;
        } else {
            Entry current = table[index];
            //цикл оборачивается пока не встретит ноду с таким же ключем, либо дойдет последней ноды
            while (current.next != null && created.eng.equalsIgnoreCase(current.eng)) {
                current = current.getNext();
            }

            //перезапись значения при встрече такого же ключа
            if (created.eng.equalsIgnoreCase(current.eng)) {
                String lost = current.getRus();
                current.setRus(created.getRus());
                return lost;
            } else {
                current.setNext(created);
                size++;
                return null;
            }
        }
    }

    //увеличение таблицы
    public void tableIncrease(int capacity) {
        Entry[] arr = new Entry[capacity + 1];
        for (int i = 0; i < table.length; i++) {
            arr[i] = table[i];
        }
        table = arr;
    }

//TODO доработать эту ересь
    @Override
    public Object remove(Object key) {
        if (key == null) {
            return null;
        }
        int index = getHash((String) key);//вычисление HashCode
        if (index >= getTableSize()) {
            return null;
        }
        Entry current = table[index];
        String goodBye = null;
        //
        if(((String) key).equalsIgnoreCase(current.getEng())){
            goodBye = current.getRus();
        } else{
            while (current.getNext()!=null){
                if(current.getNext().getEng().equalsIgnoreCase((String) key)){
                    goodBye = current.getNext().getRus();
                    current.setNext(current.getNext().getNext());
                }else{
                    current = current.getNext();
                }
            }
        }
        return goodBye;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {
        table = new Entry[0];

    }

    @Override
    public Set keySet() {
        TreeSet<String> ts = new TreeSet<>();
        Iterator iter = iterator();
        Entry current;
        while(iter.hasNext()){
            current = (Entry)iter.next();
            ts.add(current.getEng());
            }
        return ts;
    }

    @Override
    public Collection values() {
        ArrayList<String> ts = new ArrayList<>();
        Iterator iter = iterator();
        Entry current;
        while(iter.hasNext()){
            current = (Entry)iter.next();
            ts.add(current.getRus());
        }
        return ts;
    }

    @Override
    public Set<Entry> entrySet() {
        HashSet<Entry> s = new HashSet<>();
        Iterator iter = iterator();
        Entry current;
        while (iter.hasNext()) {
            current = (Entry) iter.next();
            s.add(current);
        }
        return s;
    }


    /**
     * Метод переопределяющий стандартную кодировку char в кодировку от 1 до 26
     * другие символы возвращают значение (0) для дальнейшей обработки
     * <p>
     * Данный метод решает следующие проблемы:
     * 1) Стандартная кодировка символов создаст пустоту в начале таблицы
     * 2) Пренебрегает регистром, благодаря чему ключ не зависит от регистра, и пользователю становится проще
     * 3) Создает особую реакцию на символы, не входящие в латинский алфавит
     */
    public static int charConvertor(int c) {
        int i = c;
        if (c >= 65 && c <= 90) {
            return i = i - 64;
        } else if ((c >= 97 && c <= 122)) {
            return i = i - 96;
        } else {
            return 0;
        }
    }

    /**
     * Hash вычисляется по следующему принципу:
     * 1) Первые три символа в ключе образует первые цифры хэшкода (одну или две),
     * либо 0 если это не латинские буквы (метод firstPartOfHash)
     * <p>
     * 2) Сумма трех последних символов образует вторую часть хэшкода (метод secondPartOfHash)
     * <p>
     * 3) Если сумма последних трех символов ключа  менее двузначной, она дополняется нолем
     * Например слово add преобразуется в 909
     * <p>
     * 3) Преобразование char в int происходит по логике определенной в методе charConvertor
     * <p>
     * 4) Слова начинающиеся с символа не входящего в латинский алфавит будут распологаться раньше всех,
     * например !!!add преобразуется в 009 или просто 9
     * <p>
     * 5) Словам начинающимся с не латинских букв выделено довольно мало места, т.к. они не являются нашей целевой группой
     * но все таки немножко есть
     * <p>
     * 6) Данный адгоритм кодирования создает около 7900 уникальных ключей
     * <p>
     * 7) Если возникнет необходимость складывать более менее по алфавиту, можно в качестве первой части хэш кода
     * использовать кодировку первой буквы, но программы словари обычно просто выдают реакцию на ключ, и не важно
     * как там что хранится
     */
    public int getHash(String key) {
        StringBuffer sb = new StringBuffer();
        int first = firstPartOfHash(key);
        sb.append(first);

        int last = secondPartOfHash(key);
        //переменная добавляется в строку, дополняясь нолями если нужно
        if (last < 10) {
            sb.append("0").append(last);
        } else {
            sb.append(last);
        }
        String hash = sb.toString();

        return Integer.valueOf(hash);
    }

    //возвращает первую часть кода на основе суммы трех последних символов
    public int firstPartOfHash(String key) {
        char[] arr = key.toCharArray();
        int summ = 0;
        if (arr.length == 1) {
            return charConvertor(arr[0]);
        } else if (arr.length == 2) {
            return charConvertor(arr[0]) + charConvertor(arr[1]);
        } else {
            return charConvertor(arr[0]) + charConvertor(arr[1]) + charConvertor(arr[2]);
        }
    }

    //возвращает вторую часть кода на основе суммы трех последних символов
    public int secondPartOfHash(String key) {
        char[] arr = key.toCharArray();
        if (arr.length == 1) {
            return charConvertor(arr[arr.length - 1]);
        } else if (arr.length == 2) {
            return charConvertor(arr[arr.length - 1]) + charConvertor(arr[arr.length - 2]);
        } else {
            return charConvertor(arr[arr.length - 1]) + charConvertor(arr[arr.length - 2]) + charConvertor(arr[arr.length - 3]);
        }

    }

//нода
     class Entry {
        private String rus;
        private String eng;
        private Entry next;

        public Entry(String eng, String rus) {
            this.eng = eng;
            this.rus = rus;
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
    class VocabluaryIterator implements Iterator <Entry>{
        int currentIndex;
        Entry current;
        Entry last;

        public VocabluaryIterator() {
            if(size == 0){
                System.out.println("Нельзя создать итератор для пустой таблицы");
                return;
            }
            for (int i = 0; i < getTableSize(); i++) {
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
                for (int i = currentIndex+1; i <= getTableSize(); i++) {
                    if (i == getTableSize()) {
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
