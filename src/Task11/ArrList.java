package Task11;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;

public class ArrList implements List {
    private Object[] data;


    //согласно конвенции любая коллекция должна содержать  два обязательных конструктора
    //конструктор создающий пустую коллекцию
    public ArrList() {
        this.data = new Object[0];
    }

    //конструктор принимающий коллекцию и записывающий ее в создаваемую
    public ArrList(Collection c) {
        this.data = c.toArray();
    }

    //т.к. данная коллекция основана на массиве, считаю необходимой возможность создавать коллекцию передавая массив
    public ArrList(Object[] obj) {
        if (obj == null) throw new NullPointerException();
        this.data = obj;
    }


    //выводит содержимое коллекции в строку, либо сообщение о том, что коллекция пуста
    @Override
    public String toString() {
        if (this.size() == 0) return "List is empty";
        StringBuilder text = new StringBuilder("List content: ");
        for (Object o : data) {
            text.append("[").append(o).append("] ");
        }
        return text.toString();
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : this.data) {
            if (element.equals(o)) return true;
        }
        return false;
    }


    //невероятная оптимизация)))
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size());
    }

    @Override
    public Object[] toArray(Object[] arr) {
        if (data.length >= arr.length) {
            return arr = toArray();
        } else {
            //если массив больше коллекции, информация записывается в начало массива
            // следующая за ней ячейка перезаписывается как null, а последующие ячейки массива сохраняют данные
            for (int i = 0; i < data.length; i++) {
                arr[i] = data[i];
            }
            arr[data.length] = null;
        }
        return arr;
    }

    @Override
    public boolean add(Object o) {
        Object[] newArr = new Object[data.length + 1];
        for (int i = 0; i < data.length; i++) {
            newArr[i] = data[i];
        }
        newArr[data.length] = o;
        data = newArr;
        return true;
    }

    //метод возвращает элемент по индексу и удаляет его из коллекции
    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size()) return "WRONG INDEX";
        //сохранение элемента
        Object removed = data[index];
        //удаление элемента
        Object[] assist = new Object[data.length - 1];
        for (int i = 0; i < index; i++) {
            assist[i] = data[i];
        }
        for (int i = index; i < assist.length; i++) {
            assist[i] = data[i + 1];
        }
        data = assist;

        return removed;
    }


    @Override
    public boolean remove(Object o) {
        //проверка на наличие
        if (!contains(o)) return false;
        //поиск индекса
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(o)) {
                index = i;
                break;
            }
        }
        //удаление элемента
        Object[] newArr = new Object[data.length - 1];
        for (int i = 0; i < index; i++) {
            newArr[i] = data[i];
        }
        for (int i = index; i < newArr.length; i++) {
            newArr[i] = data[i + 1];
        }
        data = newArr;
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        //принимаемая коллекция преобразуется в массив, каждый элемент которого проверяется на наличие в вызывающей коллекции
        //при обнаружении элемента, которого нет в вызывающей коллекции метод возвращает false и дальнейшую проверку не производит

        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            if (!contains(arr[i])) return false;
        }
        return true;
    }


    //производится по принципу слияния двух массивов
    @Override
    public boolean addAll(Collection c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;
        Object[] assist = new Object[data.length + c.size()];
        Object[] input = c.toArray();

        for (int i = 0; i < data.length; i++) {
            assist[i] = data[i];
        }
        for (int i = data.length; i < assist.length; i++) {
            assist[i] = input[i - data.length];
        }
        data = assist;
        return true;
    }


    //добавляет все элементы принимаемой коллекции в вызывающую со вставкой начиная с ячейки, обозначенной индексом
    @Override
    public boolean addAll(int index, Collection c) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("WRONG INDEX");
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;
        Object[] assist = new Object[data.length + c.size()];
        Object[] input = c.toArray();

        //переносится часть исходного массива до ячейки вставки
        for (int i = 0; i < index; i++) {
            assist[i] = data[i];
        }
        //вставка
        for (int i = 0; i < input.length; i++) {
            assist[i + index] = input[i];
        }
        //дозапись исходных данных после вставки
        for (int i = index; i < data.length; i++) {
            assist[index + input.length] = data[i];
        }
        data = assist;
        return true;
    }

    //удаляет из  коллекции все элементы содержащиеся в принимаемой
    //если в  коллекции несколько элементов соответствуют одному из элементов вызывающей - удаляет все
    //метод возвращает true если коллекция изменилась
    @Override
    public boolean removeAll(Collection c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;
        int count = 0;
        Object[] arr = new Object[data.length];
        //каждый элемент масива data проверяется на отсутствие в c и переносится во вспомогательный массив
        for (int i = 0; i < data.length; i++) {
            if (!c.contains(data[i])) {
                arr[count] = data[i];
                count++;
            }
        }
        //если все элементы перенесены, значит совпадений не обнаружено и осуществится выход из метода
        if (count == data.length) {
            return false;
        }
        //перенос без пустого хвоста
        Object[] assist = new Object[count];
        for (int i = 0; i < count; i++) {
            assist[i] = arr[i];
        }
        //перезапись
        data = assist;
        return true;
    }


    //сохраняет в коллекции все элементы содержащиеся в принимаемой
    //если в  коллекции несколько элементов соответствуют одному из элементов вызывающей - сохраняет все
    //метод возвращает true если коллекция изменилась
    @Override
    public boolean retainAll(Collection c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        //если принимаемая коллекция пуста, то и вызывающая опустеет
        if (c.size() == 0) {
            clear();
            return true;
        }

        int count = 0;
        Object[] arr = new Object[data.length];
        //каждый элемент масива data проверяется на наличие в c и переносится во вспомогательный массив
        for (int i = 0; i < data.length; i++) {
            if (c.contains(data[i])) {
                arr[count] = data[i];
                count++;
            }
        }
        //если все элементы перенесены, значит отсутствий не обнаружено и осуществится выход из метода
        if (count == data.length) {
            return false;
        }
        //перенос без пустого хвоста
        Object[] assist = new Object[count];
        for (int i = 0; i < count; i++) {
            assist[i] = arr[i];
        }
        //перезапись
        data = assist;
        return true;
    }

    @Override
    public void clear() {
        data = new Object[0];

    }

    //возвращает элемент с соответствующим индексом
    @Override
    public Object get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }
        return data[index];
    }

    //записывает в заданный индекс новый элемент, возвращает стертый
    @Override
    public Object set(int index, Object element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }
        Object old = data[index];
        data[index] = element;
        return old;
    }


    //вставляет элемент по указанному индексу, смещая находящийся там и все последующие
    //метод допускает вставку в несуществующую следующую за массивом ячейку - элемент прибавится в конце
    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size()) + "; Index:" + index);
        }
        Object[] assist = new Object[data.length + 1];

        //переносится часть исходного массива до ячейки вставки
        for (int i = 0; i < index; i++) {
            assist[i] = data[i];
        }
        //вставка
        assist[index] = element;

        //дозапись исходных данных после вставки
        for (int i = index; i < data.length; i++) {
            assist[i + 1] = data[i];
        }
        data = assist;
    }


    //возвращает индекс первого элемента соответствующего принимаемому, при отсутствии соответствия возвращает -1
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    //возвращает индекс последнего элемента соответствующего принимаемому, при отсутствии соответствия возвращает -1
    //основан на обратном переборе с конца массива до первого соответствия
    @Override
    public int lastIndexOf(Object o) {
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    //создает новую коллекцию содержащую элементы от первого аргумента(включительно), до второго (не включительно)
    @Override
    public List subList(int fromIndex, int toIndex) {
        //проверка индексов
        if (fromIndex < 0 || fromIndex >= size() || toIndex < 0 || toIndex >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1));
        }
        //новый массив необходимой длины
        Object[] obj = new Object[toIndex - fromIndex];
        //запись в массив нужного диапазона элементов
        for (int i = 0; i < obj.length; i++) {
            obj[i] = data[i + fromIndex];
        }
        //передача массива в конструктор
        return new ArrList(obj);
    }

    //метод возвращающий итератор
    @Override
    public Iterator iterator() {
        return new ArrIterator();
    }

    //метод возвращает листИтератор
    @Override
    public ListIterator listIterator() {
        return new ArrListIterator();
    }

    ////метод возвращает листИтератор и устанавливает его курсор на принимаемый индекс
    @Override
    public ListIterator listIterator(int index) {
        return new ArrListIterator(index);
    }





    private class ArrIterator implements Iterator {
        int current = 0;//элемент на который указывает итератор
        int lastCalled = -1;//
        boolean  status = false;//индикатор возможности вызвать remove, set, add (modification operation)


        @Override
        public boolean hasNext() {
            return (current < size());
        }

        @Override
        public Object next() {
            if (current >= size()) {
                throw new NoSuchElementException("END_OF_LIST");
            } else {
                lastCalled = current;
                current = ++current;
                status=true;
                return data[lastCalled];
            }
        }
    }

    private class ArrListIterator extends ArrIterator implements ListIterator {


        public ArrListIterator() {
            super();
        }
        public ArrListIterator(int index) {
            super();
            this.current = index;
        }

        @Override
        public boolean hasPrevious() {
            return current>0;
        }

        //возвращает элемент находящийся перед курсором
        //если вызвать next и previous подряд, вернет тот же элемент
        @Override
        public Object previous() {
            if (current <= 0) {
                throw new NoSuchElementException("NO_ELEMENTS_BEFORE");
            } else {
                lastCalled = current-1;
                current = --current;
                status=true;
                return data[lastCalled];
            }
        }

        //возвращает индекс элемента который будет вызван методом next, если находится в конце коллекции вернет ее размер
        @Override
        public int nextIndex() {
            return current;
        }

        //возвращает индекс элемента который будет вызван методом previous, если находится в начале коллекции вернет -1
        @Override
        public int previousIndex() {
            return current-1;
        }


        //метод удаляет из коллекции элемент, который был вызван последним вызовом next() или  previous()
        //не может вызываться если вызывался полсе последнего вызова next/previous
        @Override
        public void remove() {
            if(!status){
                throw new IllegalStateException("Please use next() or previous() before called this method");
            }
            Object[] assist = new Object[data.length - 1];
            for (int i = 0; i < lastCalled; i++) {
                assist[i] = data[i];
            }
            for (int i = lastCalled; i < assist.length; i++) {
                assist[i] = data[i + 1];
            }
            current--;
            data = assist;
            status=false;

        }

        @Override
        public void set(Object o) {
            if(!status){
                throw new IllegalStateException("Please use next() or previous() before called this method");
            }
            data[lastCalled] = o;
            status = false;
        }

        //добавляет элемент на место курсора
        //метод next() после данного метода вернет следующий после добавленного элемент
        //метод previous() после данного метода вернет добавленный элемент
        @Override
        public void add(Object o) {

            if(!status){
                throw new IllegalStateException("Please use next() or previous() before called this method");
            }
            Object[] assist = new Object[data.length+1];

            //переносится часть исходного массива до ячейки вставки
            for (int i = 0; i <current; i++) {
                assist[i] = data[i];
            }
            //вставка
            assist[current] = o;

            //дозапись исходных данных после вставки
            for (int i = current; i < data.length; i++) {
                assist[i + 1] = data[i];
            }
            data = assist;
            current++;
            status=false;
        }

    }


}
