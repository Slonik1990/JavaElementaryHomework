package Task10;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class MyNodeList implements Collection {
    private Node head;
    private Node tail;
    private int size;

    @Override
    public String toString() {
        if(this.size()==0) return "NodeList is empty";
        StringBuilder text = new StringBuilder("NodeList content: HEAD-> ");
        Node current = head;
        while (current!=null) {
            text.append("[").append(current.getData()).append("] ");
            current=current.getNext();
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
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        Object currant = head;
        while (currant!=null){
            if(((Node) currant).getData().equals(o))return true;
            currant = ((Node) currant).getNext();
        }
        return false;
    }

    //преобразует в массив
    @Override
    public Object[] toArray() {
        Object [] obj = new Object[size];
        Object currant = head;
        int i =0;
        while (currant!=null){
            obj[i] = ((Node) currant).getData();
            currant = ((Node) currant).getNext();
            i++;
        }
        return obj;
    }

    //добавляет в конец
    @Override
    public boolean add(Object o) {
        Node created = new Node(o);
        if(isEmpty()){
            this.head = created;
            this.tail = created;
            this.size++;
            return true;
        }else{
            this.tail.setNext(created);
            this.tail = created;
            this.size++;
        }
        return true;
    }

    //добавляет в начало
    public void push(Object o) {
        Node created = new Node(o, head);
        if (isEmpty()) {
            this.head = created;
            this.tail = created;
            this.size++;
        } else {
            created.setNext(head);
            this.head = created;
            this.size++;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    public boolean removeFirst() {
        if(size == 0) return false;
        if(size==1){
            clear();
            return true;
        }
        this.head = head.getNext();
        size--;
        return true;
    }

    public boolean removeLast() {
        if(size == 0) return false;
        if(size==1){
            clear();
            return true;
        }
        Node current = head;
        for (int i =1; i <size-1; i++) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
        size--;
        return true;
    }

    //возвращает и удаляет первый элемент
    public Object pop(){
        if(size==0)return false;
        Object top = head;
        head = head.getNext();
        size--;
        return top;
    }

    //возвращает первый элемент
    public Object peek(){
        if(size==0)return false;
        return head;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object obj: c) {
            add(obj);
        }
        return true;
    }

    //затираются все связи между нодами, благодаря чему GC быстрее удалит их из памяти, чем если просто удалить ссылку на голову
    @Override
    public void clear() {
        Node current = head;
        Node prev;
        while (current!=null){
            prev = current;
            current = current.getNext();
            prev.setNext(null);
        }
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Iterator iterator() {
        return iterator();
    }




}
