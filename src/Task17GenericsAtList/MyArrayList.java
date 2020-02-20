package Task17GenericsAtList;

import java.util.*;

public class MyArrayList <E>implements List<E> {
    private E[] data;

    public MyArrayList () {
        this.data = (E[])new Object[0];
    }

    //конструктор принимающий коллекцию и записывающий ее в создаваемую
    public MyArrayList(Collection<? extends E>  c) {
        this.data = (E[])c.toArray();
    }

    public MyArrayList(E[] obj) {
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


    @Override
    public E[] toArray() {
        E[] obj = (E[]) new Object[size()];
        for (int i = 0; i < size(); i++) {
            obj[i] = data[i];
        }
        return obj;
    }

    @Override
    public <E> E[] toArray(E[] arr) {
        if (data.length >= arr.length) {
            return arr = (E[])toArray();
        } else {
            for (int i = 0; i < data.length; i++) {
                arr[i] = (E) data[i];
            }
            arr[data.length] = null;
        }
        return arr;
    }

    @Override
    public boolean add(E o) {
        E[] newArr = (E[])new Object[data.length + 1];
        for (int i = 0; i < data.length; i++) {
            newArr[i] = data[i];
        }
        newArr[data.length] = o;
        data = newArr;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("WRONG INDEX") ;
        //сохранение элемента
        Object removed = data[index];
        //удаление элемента
        E[] assist = (E[])new Object[data.length - 1];
        for (int i = 0; i < index; i++) {
            assist[i] = data[i];
        }
        for (int i = index; i < assist.length; i++) {
            assist[i] = data[i + 1];
        }
        data = assist;

        return (E)removed;
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
        E[] newArr = (E[])new Object[data.length - 1];
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
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            if (!contains(arr[i])) return false;
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;
        E[] assist = (E[])new Object[data.length + c.size()];
        E[] input = (E[])c.toArray();

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
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("WRONG INDEX");
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) return false;

        E[] assist = (E[])new Object[data.length + c.size()];
        E[] input = (E[])c.toArray();

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
            assist[i + input.length] = data[i];
        }
        data = assist;
        return true;
    }

    //удаляет из  коллекции все элементы содержащиеся в принимаемой
    //если в  коллекции несколько элементов соответствуют одному из элементов вызывающей - удаляет все
    //метод возвращает true если коллекция изменилась
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0){
            return false;//не с чем сравнивать для удаления
        }
        int count = 0;
        E[] arr = (E[])new Object[data.length];
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
        E[] assist = (E[])new Object[count];
        for (int i = 0; i < count; i++) {
            assist[i] = arr[i];
        }
        //перезапись
        data = assist;
        return true;
    }



    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) {
            clear();
            return true;
        }
        int count = 0;
        E[] arr = (E[])new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            if (c.contains(data[i])) {
                arr[count] = data[i];
                count++;
            }
        }
        if (count == data.length) {
            return false;
        }
        E[] assist =(E[]) new Object[count];
        for (int i = 0; i < count; i++) {
            assist[i] = arr[i];
        }
        data = assist;
        return true;
    }

    @Override
    public void clear() {
        data = (E[])new Object[0];

    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }
        return (E)data[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }
        Object old = data[index];
        data[index] = element;
        return (E)old;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size()) + "; Index:" + index);
        }
        E[] assist = (E[])new Object[data.length + 1];

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

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //проверка индексов
        if (fromIndex < 0 || fromIndex >= size() || toIndex < 0 || toIndex >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1));
        }
        //новый массив необходимой длины
        E[] obj = (E[]) new Object[toIndex - fromIndex];
        //запись в массив нужного диапазона элементов
        for (int i = 0; i < obj.length; i++) {
            obj[i] = data[i + fromIndex];
        }
        //передача массива в конструктор
        List<E> list = new MyArrayList<>(obj);
        return list;
    }

    //метод возвращающий итератор
    @Override
    public Iterator iterator() {
        return new MyArrIterator();
    }

    //метод возвращает листИтератор
    @Override
    public ListIterator listIterator() {
        return new MyArrListIterator();
    }

    ////метод возвращает листИтератор и устанавливает его курсор на принимаемый индекс
    @Override
    public ListIterator listIterator(int index) {
        return new MyArrListIterator(index);
    }





    private class MyArrIterator implements Iterator {
        int current = 0;//элемент на который указывает итератор
        int lastCalled = -1;//
        boolean  status = false;//индикатор возможности вызвать remove, set, add (modification operation)


        @Override
        public boolean hasNext() {
            return (current < size());
        }

        @Override
        public E next() {
            if (current >= size()) {
                throw new NoSuchElementException("END_OF_LIST");
            } else {
                lastCalled = current;
                current = ++current;
                status=true;
                return (E)data[lastCalled];
            }
        }
    }

    private class MyArrListIterator extends MyArrIterator implements ListIterator {


        public MyArrListIterator() {
            super();
        }
        public MyArrListIterator(int index) {
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
        public E previous() {
            if (current <= 0) {
                throw new NoSuchElementException("NO_ELEMENTS_BEFORE");
            } else {
                lastCalled = current-1;
                current = --current;
                status=true;
                return (E)data[lastCalled];
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
            E[] assist = (E[])new Object[data.length - 1];
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
            data[lastCalled] = (E)o;
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
            E[] assist = (E[]) new Object[data.length+1];

            //переносится часть исходного массива до ячейки вставки
            for (int i = 0; i <current; i++) {
                assist[i] = data[i];
            }
            //вставка
            assist[current] = (E)o;

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
