package Task11;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NodeList implements List {

    private Node head = null;
    private Node tail;
    private int size;

    //конструкторы:

    //дефолтный
    public NodeList() {
    }

    //принимающий массив
    public NodeList(Object[] obj) {
        for (Object o : obj) {
            add(o);
        }
    }

    //принимающий коллекцию
    public NodeList(Collection coll) {
        Object[] obj = coll.toArray();
        for (Object o : obj) {
            add(o);
        }
    }


    @Override
    public String toString() {
        if (this.size() == 0) return "NodeList is empty";
        StringBuilder text = new StringBuilder("NodeList content: HEAD-> ");
        Node present = head;
        while (present != null) {
            text.append("[").append(present.getData()).append("] ");
            present = present.getNext();
        }
        text.append("<- TAIL");
        return text.toString();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node currant = head;
        while (currant != null) {
            if (currant.getData().equals(o)) {
                return true;
            }
            currant = currant.getNext();
        }
        return false;

    }


    @Override
    public Object[] toArray() {
        Object[] obj = new Object[size];
        Node currant = head;
        int i = 0;
        while (currant != null) {
            obj[i] = currant.getData();
            currant = currant.getNext();
            i++;
        }
        return obj;
    }

    @Override
    public Object[] toArray(Object[] arr) {
        if (size >= arr.length) {
            arr = toArray();
        } else {
            //если массив больше списка, информация записывается в начало массива
            // следующая за ней ячейка перезаписывается как null, а последующие ячейки массива сохраняют данные
            int i = 0;
            for (Node n = head; n != null; n = n.getNext()) {
                arr[i] = n.getData();
            }
            arr[size] = null;
        }
        return arr;
    }

    @Override
    public boolean add(Object o) {
        Node created = new Node(o);
        if (isEmpty()) {
            this.head = created;
            this.tail = created;
            this.size++;
            return true;
        } else {
            this.tail.setNext(created);
            created.setPrev(tail);
            this.tail = created;
            this.size++;
        }
        return true;
    }

    //true если коллекция изменилась
    @Override
    public boolean remove(Object o) {
        //проверка на наличие, сразу определяет стоит ли производить дальнейшие операции
        if (!contains(o)) {
            return false;
        }
        //списком из 1 ноды (если метод прошел прошлый блок, эта нода соответствует принимаемому объекту)
        if (size == 1) {
            clear();
            return true;
        }

        //список из 2 или более нод
        if (o.equals(head.getData())) {
            head = head.getNext();
            head.setPrev(null);
            size--;
            return true;
        } else if (o.equals(tail.getData())) {
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
            return true;
        } else {
            for (Node n = head.getNext(); n != tail; n = n.getNext()) {
                if (n.getData().equals(o)) {
                    n.getPrev().setNext(n.getNext());//от прошлой ноды ссылка устанавливается на следующую
                    n.getNext().setPrev(n.getPrev());//от следующей ноды ссылка устанавливается на прошлую
                    size--;
                    return true;
                }
            }

        }
        return false;
    }

    //true если все элементы принимаемой коллекции присутствуют в вызывающей
    @Override
    public boolean containsAll(Collection c) {
        //принимаемая коллекция преобразуется в массив, каждый элемент которого проверяется на наличие в вызывающей коллекции
        //при обнаружении элемента, которого нет в вызывающей коллекции метод возвращает false и дальнейшую проверку не производит

        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) {
            return false;
        }
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            if (!contains(arr[i])) return false;
        }
        return true;
    }

    //true если коллекция изменилась
    @Override
    public boolean addAll(Collection c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) {
            return false;
        }
        Object[] assist = c.toArray();
        for (int i = 0; i < assist.length; i++) {
            add(assist[i]);
        }

        return true;
    }


    //вызывающая и принимаемая коллекции преобразуются в массивы, которые объединяются в необходимом порядке в массив
    //коллекция затирается и перезаполняется из массива содержащего обе коллекции
    //метод допускает добавление после всех элементов если в качестве индекса передать размерность текущей коллекции
    @Override
    public boolean addAll(int index, Collection c) {
        //проверки индексов и принимаемой коллекции
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException("WRONG INDEX");
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) {
            return false;
        }
        //вспомогательные массивы
        Object[] input = c.toArray();//принимаемая коллекция
        Object[] data = toArray();//текущая
        Object[] assist = new Object[input.length + data.length];//объединяющий массив

        //переносится часть исходной коллекции до индекса
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

        //очистка
        clear();

        //перезапись
        for (int i = 0; i < assist.length; i++) {
            add(assist[i]);
        }

        return true;

    }

    //удаляет из  коллекции все элементы содержащиеся в принимаемой
    //если в  коллекции несколько элементов соответствуют одному из элементов вызывающей - удаляет все
    //метод возвращает true если коллекция изменилась
    @Override
    public boolean removeAll(Collection c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0){
            return false;
        }
        int removes = 0;
        Object [] assist = c.toArray();
        for (int i = 0; i < assist.length; i++) {
            if(contains(assist[i])) {
                remove(assist[i]);
                removes++;
            }
        }

        return removes!=0;
    }

    //TODO не работает
    @Override
    public boolean retainAll(Collection c) {
        Object [] assist = c.toArray();
        for (int i = 0; i < assist.length; i++) {
            if(!contains(assist[i])) {
                remove(assist[i]);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Node current = head;
        Node prev;
        while (current != null) {
            prev = current;
            current = current.getNext();
            prev.setPrev(null);
            prev.setNext(null);
        }
        size = 0;
        head = null;
        tail = null;
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
    public Object remove(int index) {
        return null;
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
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }


    class Node {

        private Object data;
        private Node next;
        private Node prev;

        public Node(Object data) {
            this.data = data;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }


}
