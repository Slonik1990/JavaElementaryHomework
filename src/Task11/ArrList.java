package Task11;

import java.util.*;

public class ArrList implements List {
    private Object[] data;


    //согласно конвенции любая коллекция должна содержать  два обязательных конструктора
    //конструктор создающий пустую коллекцию
    public ArrList(){
        this.data = new Object[0];
    }
    //конструктор принимающий коллекцию и записывающий ее в создаваемую
    public ArrList(Collection c){
        this.data = c.toArray();
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
        return data.length==0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element :this.data) {
            if(element.equals(o))return true;
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
        if(data.length>=arr.length){
            return arr = toArray();
        }else {
            //если массив больше коллекции, информация записывается в начало массива
            // следующая за ней ячейка перезаписывается как null, а последующие ячейки массива сохраняют данные
            for (int i = 0; i < data.length; i++) {
                arr[i] = data[i];
            }
            arr[data.length]=null;
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
        if(index<0||index>=size()) return "WRONG INDEX";
        //сохранение элемента
        Object removed = data[index];
        //удаление элемента
        Object[] assist = new Object[data.length - 1];
        for(int i = 0;i<index;i++){
            assist[i]=data[i];
        }
        for (int i = index; i < assist.length; i++) {
            assist[i]=data[i+1];
        }
        data = assist;

        return removed;
    }


    @Override
    public boolean remove(Object o) {
        //проверка на наличие
        if(!contains(o)) return false;
        //поиск индекса
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            if(data[i].equals(o)){
                index=i;
                break;
            }
        }
        //удаление элемента
        Object[] newArr = new Object[data.length - 1];
        for(int i = 0;i<index;i++){
            newArr[i]=data[i];
        }
        for (int i = index; i < newArr.length; i++) {
            newArr[i]=data[i+1];
        }
        data = newArr;
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }


    @Override
    public Iterator iterator() {
        return new ArrIterator();
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    private class ArrIterator implements Iterator {
        int current = 0;
        int last = -1;


        @Override
        public boolean hasNext() {
            return (current!=size());
        }

        @Override
        public Object next() {
            if(current>=size()){
                return null;
            } else {
                last = current;
                current = ++current;
                return data[last];
            }
        }
    }





}
