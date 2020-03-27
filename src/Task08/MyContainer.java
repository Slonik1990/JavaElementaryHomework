package Task08;

import java.util.Collection;
import java.util.Iterator;

public class MyContainer implements Collection {

    private Object[] objects = new Object[0];


    @Override
    public String toString() {
        if(this.size()==0) return "\nContainer is empty";
        StringBuilder text = new StringBuilder("\nContainer content: ");
        for (Object o : objects) {
            text.append("[").append(o).append("] ");
        }
        return text.toString();
    }

    @Override
    public boolean add(Object o) {
        Object[] newArr = new Object[objects.length + 1];
        for (int i = 0; i < objects.length; i++) {
            newArr[i] = objects[i];
        }
        newArr[objects.length] = o;
        objects = newArr;
        return true;
    }

    //с целью оптимизации коллекция добавляется не по одному элементу, а преобразуется в массив и происходит слияние массивов
    @Override
    public boolean addAll(Collection donor) {
        //даже без проверки принимаемой коллекции на пустоту все работает корректно, но это приводит к бессмысленному
        //перекладыванию массива objects в такой же массив, поэтому проверка добавлена для оптимизации
        if(donor.size()==0)return false;

        Object [] newArr = new Object[objects.length+donor.size()];
        Object[] donorArr = donor.toArray();

        for (int i = 0; i < objects.length; i++) {
            newArr[i] = objects[i];
        }
        for (int i = objects.length; i < newArr.length; i++) {
            newArr[i]=donorArr[i-objects.length];
        }
        objects = newArr;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        //проверка на наличие
        if (!contains(o)) return false;
        int index=0;
        //вычисление индекса элемента, который необходимо удалить
        //не использовал метод getElement для сохранения полиморфизма, т.к данного метода нет в интерфейсе Collection
        for (int i = 0; i < objects.length; i++) {
            if(objects[i].equals(o)){
                index=i;
                break;
            }
        }
        //удаление элемента
        Object[] newArr = new Object[objects.length - 1];
        for(int i = 0;i<index;i++){
            newArr[i]=objects[i];
        }
        for (int i = index; i < newArr.length; i++) {
            newArr[i]=objects[i+1];
        }
        objects = newArr;
        return true;
    }

    //в отличии от метода remove данный метод удаляет все  объекты соответствующие принимаемому, а не первый обнаруженный
    //реализован рекурсией, а не циклом для практики работы с рекурсией
    public void removeEvery(Object o) {
        //условие продолжения рекурсии
        if(contains(o)) {
            //шаг
            remove(o);
            //рекурсия
            removeEvery(o);
        }
        //условие выхода из рекурсии -
        else return;
    }

    @Override
    //удаляет из вызывающей коллекции все элементы, которые есть в принимаемой
    public boolean removeAll(Collection rem) {
        int count = 0;
        Object[] arr = new Object[objects.length];
        //каждый элемент масива object проверяется на отсутствие в rem и переносится во вспомогательный массив
        for (int i = 0; i < objects.length; i++) {
            if(!rem.contains(objects[i])){
                arr[count] = objects[i];
                count++;
            }
        }
        //перенос без пустого хвоста
        Object[] assist = new Object[count];
        for (int i = 0; i < count; i++) {
            assist[i]=arr[i];
        }
        objects=assist;

        return true;
    }

    //сохраняет в вызывающей коллекции только те элементы, которые есть в принимаемой
    @Override
    public boolean retainAll(Collection rem) {
        int count=0;
        Object[] arr = new Object[objects.length];
        //каждый элемент масива object проверяется на наличие в rem и переносится во вспомогательный массив
        for (int i = 0; i < objects.length; i++) {
            if(rem.contains(objects[i])){
                arr[count] = objects[i];
                count++;
            }
        }
        //перенос без пустого хвоста
        Object[] assist = new Object[count];
        for (int i = 0; i < count; i++) {
            assist[i]=arr[i];
        }
        objects=assist;

        return true;
    }

    @Override
    public int size() {
        return objects.length;
    }


    @Override
    public boolean isEmpty() {
        if(objects.length==0)return true;
        return false;
    }


    //создается новый массив с размерностью коллекции и принимает все ее данные
    @Override
    public Object[] toArray() {
        Object[]arr = new Object[objects.length];
        if(objects.length==0)return arr;
        int i =0;
        for (Object o: objects) {
            arr[i]=o;
            i++;
        }
        return arr;
    }

    //записывает коллекцию в принимаемый массив аналогично реализации других коллекций, например ArrayList
    @Override
    public Object[] toArray(Object[] arr) {
        if(objects.length>=arr.length){
            toArray();
        }else {
            //если массив больше коллекции, информация записывается в начало массива
            // следующая за ней ячейка перезаписывается как null, а последующие ячейки массива сохраняют данные
            for (int i = 0; i < objects.length; i++) {
                arr[i] = objects[i];
            }
            arr[objects.length]=null;
    }
        return arr;
    }

    //возвращает элемент по индексу ячейки
    public Object getElement(int index) {
        if(index<objects.length && index>=0)return this.objects[index];
        else throw new IndexOutOfBoundsException();

    }

    //возвращает объект
    public Object getObject(Object o) {
        for (Object element :this.objects) {
            if(element.equals(o))return o;
        }
        return null;
    }

    @Override
    public void clear() {
        objects = new Object[0];
    }

    @Override
    public boolean contains(Object o) {
        for (Object element :this.objects) {
            if(element.equals(o))return true;
        }
        return false;
    }

    @Override
    //принимаемая коллекция преобразуется в массив, каждый элемент которого проверяется на наличие в вызывающей коллекции
    //при обнаружении элемента, которого нет в вызывающей коллекции метод возвращает false и дальнейшую проверку не производит
    public boolean containsAll(Collection c) {
        if(c.size()==0)return true;
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            if(!contains(arr[i]))return false;
        }
        return true;
        }

    @Override
    public Iterator iterator() {
        return new ArrIterator();
    }



    private class ArrIterator implements Iterator {
            int current = 0;
            int last = -1;


        @Override
        public boolean hasNext() {
            return (current<size());
        }

        @Override
        public Object next() {
            if(current>=size()){
                return null;
            } else {
                last = current;
                current = ++current;
                return objects[last];
            }
        }
    }




}
