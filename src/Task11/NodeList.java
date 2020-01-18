package Task11;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NodeList implements List {

    private Node head;
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
        if(size>=arr.length){
            toArray();
        }else {
            //если массив больше списка, информация записывается в начало массива
            // следующая за ней ячейка перезаписывается как null, а последующие ячейки массива сохраняют данные
            int i = 0;
            for ( Node n = head; n != null; n = n.getNext()) {
                arr[i] = n.getData();
            }
            arr[size]=null;
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

    @Override
    public boolean remove(Object o) {
        return false;
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
