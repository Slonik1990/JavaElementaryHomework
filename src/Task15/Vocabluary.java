package Task15;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/** Данный класс представляет собой Массив объектов (Entry),
 *  каждый из которых содержит слово на английском в качестве ключа
 *  и слово на русском в качестве значения.
 *  При добавлении в таблицу, каждый ключ кодируется особым образом в HashCode,
 *  и помещается в ячейку массива с таким индексом.
 *
 *  Для случаев, когда у разных объектов совпадет хэш код в Entry предусмотрена сслыка на следующий объект,
 *  что по сути создает связный список из объектов с одинаковым хэш кодом.
 *
 *  Также возможны ситуации, при которых ключ может содержать несколько значений,
 *  в данной программе - несколько вариантов перевода, что так же будет создавать список в ячейке
 *
 *  Такие списки повысят временную сложность алгоритма для некоторых ячеек , но полностью исключат потерю данных.
 *
 *  В случае, если уникальность хэш кода станет недостаточной, и временная сложность программы сильно возрастет,
 *  всегда можно усложнить алгоритм его формирования, но как показывает анализ разнообразных подходов
 *  к формированию хэш кода, более сложный алгоритм влечет за собой увеличение ячеек,
 *  которые никогда не будут использованы
 *
 *  В данной реализации они состовляют прмерно 21,2%
 */


public class Vocabluary implements Map {
    private int size;
    private Entry[] table = new Entry[7900];//размер по умолчанию


    //конструкторы
    public Vocabluary() {
    }

    public Vocabluary(int capacity) {
        this.table = new Entry[capacity];
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
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }


    /**Метод переопределяющий стандартную кодировку char в кодировку от 1 до 26
     * другие символы возвращают значение (0) для дальнейшей обработки
     *
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

    /**Hash вычисляется по следующему принципу:
     * 1) Первые три символа в ключе образует первые цифры кода (одну или две),
     * либо 0 если это не латинские буквы (метод firstPartOfHash)
     *
     * 2) Сумма трех последних символов образует вторую часть хэшкода (метод secondPartOfHash)
     *
     * 3) Если сумма последних трех символов ключа менее менее двузначной, она дополняется нолем
     * Например слово add преобразуется в 909
     *
     * 3) Преобразование символов в числа происходит по логике определенной в методе charConvertor
     *
     * 4) Числа начинающиеся с символа не входящего в латинский алфавит будут распологаться раньше всех,
     * например !!!add преобразуется в 009 или просто 9
     *
     * 5) Словам начинающимся с не латинских букв выделено довольно мало места, т.к. они не являются нашей целевой группой
     * но все таки немножко есть
     *
     * 6) Данный адгоритм кодирования создает около 7500 уникальных ключей
     *
     *
    */
    public static int getHash(String key) {
        StringBuffer sb = new StringBuffer();

        int first = firstPartOfHash(key);
        sb.append(first);

        int last = secondPartOfHash(key);
        //переменная добавляется в строку, дополняясь нолями если нужно
        if (last < 10){
            sb.append("0").append(last);
        } else{
            sb.append(last);
        }
        String hash = sb.toString();

        return Integer.valueOf(hash);
    }

    //возвращает первую часть кода на основе суммы трех последних символов
    public static int firstPartOfHash(String key){
        char [] arr = key.toCharArray();
        int summ = 0;
        if(arr.length == 1){
            return charConvertor(arr[0]);
        }else if (arr.length == 2){
            return charConvertor(arr[0]) + charConvertor(arr[1]);
        } else {
            return charConvertor(arr[0]) + charConvertor(arr[1]) + charConvertor(arr[2]);
        }
    }

    //возвращает вторую часть кода на основе суммы трех последних символов
    public static int secondPartOfHash(String key){
        char [] arr = key.toCharArray();
        if(arr.length == 1){
            return charConvertor(arr[arr.length - 1]);
        }else if (arr.length == 2){
            return charConvertor(arr[arr.length - 1]) + charConvertor(arr[arr.length - 2]);
        } else {
            return charConvertor(arr[arr.length - 1]) + charConvertor(arr[arr.length - 2]) + charConvertor(arr[arr.length - 3]);
        }
    }




    class Entry{
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


    }
}
