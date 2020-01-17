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

    //возвращает содержимое ноды в виде строки
    public String nodeToString(Node n){
        if(n==null)return "null";
        StringBuilder text = new StringBuilder();
        text.append("[").append(n.getData()).append("] ");
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
        Node currant = head;
        while (currant!=null){
            if( currant.getData().equals(o)){
                return true;
            }
            currant = currant.getNext();
        }
        return false;
    }

    //преобразует в массив
    @Override
    public Object[] toArray() {
        Object [] obj = new Object[size];
        Node currant = head;
        int i =0;
        while (currant!=null){
            obj[i] =  currant.getData();
            currant = currant.getNext();
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

        //ситуация с пустым списком
        if(size==0){
            return false;

        //списком из 1 ноды
        } else if(size == 1 ){
            if(head.getData().equals(o)){
                clear();
            }else {
                return false;
            }

        //список из 2 или более нод
        } else {
            if (o.equals(head.getData())) {
                head = head.getNext();
                size--;
            } else if (o.equals(tail.getData())) {
                tail = getPrevious(tail);
                tail.setNext(null);
                size--;
            } else {
                for (Node current = head; current != null; current = current.getNext()) {
                    if (o.equals(current.getData())) removeNode(current);
                }
                return false;
            }
        }
        return  true;
    }

    //если у текущей ноды следующая соответствует принимаемой - возвращает текущую
    public Node getPrevious(Node pres){
        if(pres.equals(head)&&size==0)return null;
        for (Node n = head; n!= null; n = n.getNext()) {
            if(n.getNext()==pres){
                return n;
            }
        }
        return null;
    }

    public boolean removeNode(Node goodBye){
        //ситуация с пустым списком
        if(size==0){
            return false;

        //если в списке одна нода
        }else if(size == 1 ){
            //и она соответствует принимаемой - список очищается
            if(head.equals(goodBye)){
                clear();
            //не соответствует
            }else {
                return false;
            }

        //если в списке 2 и более нод
        }else {
            //удаляемая нода является головой, хвостом либо телом списка
            if (goodBye.equals(head)) {
                head = head.getNext();
                size--;
            } else if (goodBye.equals(tail)) {
                tail = getPrevious(tail);
                tail.setNext(null);
                size--;
            } else {
                //предыдущая нода для удаляемой связывается со следующей
                getPrevious(goodBye).setNext(goodBye.getNext());
                size--;
            }
        }
        return  true;

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

        Object [] assist = c.toArray();

        for (int i = 0; i < assist.length; i++) {
                add(assist[i]);
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
        Object [] assist = c.toArray();
        for (int i = 0; i < assist.length; i++) {
            if(!contains(assist[i])) {
                remove(assist[i]);
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        Object [] assist = c.toArray();
        for (int i = 0; i < assist.length; i++) {
            if(contains(assist[i])) {
                remove(assist[i]);
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object [] assist = c.toArray();
        for (int i = 0; i < assist.length; i++) {
            if(!contains(assist[i])) {
                return false;
            }
        }

        return true;
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


    public Node getFirst() {
        return head;
    }

    public Node getLast() {
        return tail;
    }

    @Override
    public Iterator iterator() {
        return new NodeIterator();
    }



       class NodeIterator implements Iterator {
        Node last;//индикатор последней ноды которая возвращалась методом Next
        Node current = head;//текущая нода на которую перешел итератор

        @Override
        public boolean hasNext() {
            if(current==null){
                return false;
            }
            return current.getNext()!=null;
        }

        @Override
        public Node next() {
            if(current==null){
                return null;
            } else {
                last = current;
                current = current.getNext();
                return last;
            }
        }
    }

     class Node{
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getData() {
            return data;
        }
        public void setData(Object data) {
            this.data = data;
        }
    }

}

