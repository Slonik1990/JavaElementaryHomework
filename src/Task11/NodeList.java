package Task11;


import java.util.*;

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
        if (size == 0) {
            return false;
        }
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

    //добавляет элемент в конец списка
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

    //добавляет в начало
    public void push(Object o) {
        Node created = new Node(o);
        if (isEmpty()) {
            this.head = created;
            this.tail = created;
            this.size++;
        } else {
            created.setNext(head);
            head.setPrev(created);
            this.head = created;
            this.size++;
        }
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
        Object[] buffer = c.toArray();
        for (int i = 0; i < buffer.length; i++) {
            add(buffer[i]);
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
        Object[] buffer = new Object[input.length + data.length];//объединяющий массив

        //переносится часть исходной коллекции до индекса
        for (int i = 0; i < index; i++) {
            buffer[i] = data[i];
        }
        //вставка
        for (int i = 0; i < input.length; i++) {
            buffer[i + index] = input[i];
        }

        //дозапись исходных данных после вставки
        for (int i = index; i < data.length; i++) {
            buffer[i + input.length] = data[i];
        }

        //очистка
        clear();

        //перезапись
        for (int i = 0; i < buffer.length; i++) {
            add(buffer[i]);
        }

        return true;

    }

    //удаляет из  коллекции все элементы содержащиеся в принимаемой
    //если в  коллекции несколько элементов соответствуют одному из элементов вызывающей - удаляет все
    //метод возвращает true если коллекция изменилась
    @Override
    public boolean removeAll(Collection c) {
        if (c == null) throw new NullPointerException("SPECIFIED COLLECTION IS NULL");
        if (c.size() == 0) {
            return false;//не с чем сравнивать для удаления
        }

        boolean changing = false;
        for (Node n = head; n != null; n = n.getNext()) {
            if (c.contains(n.getData())) {
                removeNode(n);
                changing = true;//если хоть одна нода удалена - коллекция изменилась
            }
        }
        return changing;
    }

    //метод сохраняет в принимаемой коллекции элементы присутствующие в вызываемой
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
        boolean changing = false;
        for (Node n = head; n != null; n = n.getNext()) {
            if (!c.contains(n.getData())) {
                removeNode(n);
                changing = true;//если хоть одна нода удалена - коллекция изменилась
            }
        }
        return changing;
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
        return getNode(index).getData();
    }

    //перезаписывает данные в ноде с указанным индексом, возвращает исходные данные
    @Override
    public Object set(int index, Object element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }

        Node current = getNode(index);//необходимая нода
        Object buffer = current.getData();//сохранение данных
        current.setData(element);//перезапись
        return buffer;

    }

    //преобразует принимаемы объект в ноду и вставляет по указанному индексу
    //если в качестве индекса предать размер списка - элемент прибавится в конце
    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size()) + "; Index:" + index);
        }

        if (index == size) {
            add(element);
        } else if (index == 0) {
            push(element);
        } else {
            Node added = new Node(element);//генерация ноды
            Node after = getNode(index);//нода по указанному индексу, которая станет следующей для добавляемой
            Node before = getNode(index - 1);//нода перед указанным индексом, которая станет предыдущей для добавляемой
            //перенаправление ссылок
            after.setPrev(added);
            added.setNext(after);
            before.setNext(added);
            added.setPrev(before);
            size++;
        }

    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }
        Object buffer = getNode(index).getData();
        Node lost = getNode(index);
        Node before = lost.getPrev();
        Node after = lost.getNext();


        if (index == size) {
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
        } else if (index == 0) {
            head = head.getNext();
            head.setPrev(null);
            size--;
        } else {
            before.setNext(after);
            after.setPrev(before);
            size--;
        }

        return buffer;
    }

    @Override
    public int indexOf(Object o) {
        //проверка на наличие, сразу определяет стоит ли производить дальнейшие операции
        if (!contains(o)) {
            return -1;
        }

        int index = 0;
        for (Node n = head; !o.equals(n.getData()); n = n.getNext()) {
            index++;
        }
        return index;
    }


    @Override
    public int lastIndexOf(Object o) {
        //проверка на наличие, сразу определяет стоит ли производить дальнейшие операции
        if (!contains(o)) {
            return -1;
        }
        int index = size - 1;
        for (Node n = tail; !o.equals(n.getData()); n = n.getPrev()) {
            index--;
        }
        return index;
    }

    //создает новую коллекцию содержащую элементы от первого аргумента(включительно), до второго (не включительно)
    @Override
    public List subList(int fromIndex, int toIndex) {
        //проверка индексов
        if (fromIndex < 0 || fromIndex >= size() || toIndex < 0 || toIndex >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1));
        }


        List sub = new NodeList();//пустой лист для заполнения и последующего возврата методом
        int index = 0;//индекс элемента растущий параллельно с перебором листа
        for (Node n = head; n != null; n = n.getNext()) {
            if (index >= fromIndex && index < toIndex) {
                sub.add(n.getData());
            }
            index++;
        }

        return sub;
    }

    @Override
    public Iterator iterator() {
        return new NodeIterator();
    }

    @Override
    public ListIterator listIterator() {
        return new NodeListIterator();
    }

    @Override
    public ListIterator listIterator(int index) {
        return new NodeListIterator(index);
    }

    //дополнительные методы для работы с нодами

    //возвращает содержимое ноды в виде строки
    public static String nodeToString(Object n){
        Node node = (Node)n;
        if(n==null)return "null";
        StringBuilder text = new StringBuilder();
        text.append("[").append(node.getData()).append("] ");
        return text.toString();
    }

    public boolean removeNode(Node goodBye) {
        //ситуация с пустым списком
        if (size == 0) {
            return false;

            //если в списке одна нода
        } else if (size == 1) {
            //и она соответствует принимаемой - список очищается
            if (head.equals(goodBye)) {
                clear();
                //не соответствует
            } else {
                return false;
            }

            //если в списке 2 и более нод
        } else {
            //удаляемая нода является головой, хвостом либо телом списка
            if (goodBye.equals(head)) {
                head = head.getNext();
                head.setPrev(null);
                size--;
                return true;
            } else if (goodBye.equals(tail)) {
                tail = tail.getPrev();
                tail.setNext(null);
                size--;
                return true;
            } else {
                for (Node n = head.getNext(); n != tail; n = n.getNext()) {
                    if (n.equals(goodBye)) {
                        n.getPrev().setNext(n.getNext());//от прошлой ноды ссылка устанавливается на следующую
                        n.getNext().setPrev(n.getPrev());//от следующей ноды ссылка устанавливается на прошлую
                        size--;
                        return true;
                    }
                }

            }
        }
        return false;
    }

    //возвращает ноду по индексу
    public Node getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("MinIndex:0; " + "MaxIndex:" + (size() - 1) + "; Index:" + index);
        }
        if (index == 0) {
            return head;
        }
        if (index == size - 1) {
            return tail;
        }
        Node current = head;
        for (int i = 0; i != index; i++) {
            current = current.getNext();
        }
        return current;
    }


//    public int getNodeIndex (Node n){
//        int index = 0;
//        if(n==null){
//            return -1;
//        }
//        for (Node curr = head; curr!=null ; curr = curr.getNext()) {
//            if(curr.equals(n)){
//
//            }
//        }
//        return index;
//    }
//
//    public Object getData(Node n){
//        return n.getData();
//    }
//




    //итератор реализующий интерфейс Iterator
    class NodeIterator implements Iterator {

        Node current = head;//текущая нода на которую перешел итератор
        Node previous;
        int cursorIndex = 0;
        boolean status = false;//индикатор вызова next или previous
        Node lastCalled;//последняя вызывавшаяся нода



        @Override
        public boolean hasNext() {
            return current!= null;
        }

        @Override
        public Node next() {
            if (current == null) {
                throw new NoSuchElementException("END_OF_LIST");
            } else {
                previous = current;
                current = current.getNext();
                cursorIndex++;
                status=true;
                lastCalled = previous;
                return lastCalled;
            }
        }

        public int getCursorIndex() {
            return cursorIndex;
        }
    }



//    реализация итератора при которой курсой занимает промежуточное положение между нодами списка, и не имеет входящих ссылок
//    class NodeIterator implements Iterator {
//
//        Node current = new Node();//нода без данных, будет содержать только ссылки
//
//        public NodeIterator(){
//            current.setNext(head);
//            current.setPrev(null);
//        }
//
//        @Override
//        public boolean hasNext() {
//            return current.getNext() != null;
//        }
//
//        @Override
//        public Node next() {
//            if (current.getNext() == null) {
//                return null;
//            } else {
//                current.setPrev(current.getNext());
//                current.setNext(current.getNext().getNext());
//                return current.getPrev();
//            }
//        }
//    }

    //реализация интерфейса ListIterator
    class NodeListIterator extends NodeIterator implements ListIterator {


        public NodeListIterator(){
        }

        public NodeListIterator(int index){
            current = getNode(index);
        }

        //методы возвращающие данные
        @Override
        public boolean hasPrevious() {
            return current.getPrev()!=null;
        }

        @Override
        public Object previous() {
            if (previous==null) {
                throw new NoSuchElementException("END_OF_LIST");
            } else {
                current = previous;
                previous = current.getPrev();
                cursorIndex--;
                status=true;
                lastCalled = current;
                return lastCalled;
            }

        }

        @Override
        public int nextIndex() {
            if(cursorIndex==size()){
                System.out.println("Курсор вне листа, вызов метода next() приведет к ошибке");
            }

            return cursorIndex;
        }

        @Override
        public int previousIndex() {
            if(cursorIndex==-1){
                System.out.println("Курсор вне листа, вызов метода previous() приведет к ошибке");
            }
            return cursorIndex -1;
        }

        //методы итератора изменяющие коллекцию

        //метод удаляет из коллекции элемент, который был вызван последним вызовом next() или  previous()
        //не может вызываться если вызывался полсе последнего вызова next/previous
        @Override
        public void remove() {
            if(!status){
                throw new IllegalStateException("Please use next() or previous() before called this method");
            }
            current = current.getNext();
            removeNode(lastCalled);
            status = false;
        }


        //устанавливает значение для поля данных последней вызывавшейся ноды
        @Override
        public void set(Object o) {
            if(!status){
                throw new IllegalStateException("Please use next() or previous() before called this method");
            }
            lastCalled.setData(o);
            status=false;
        }

        //добавляет на позицию курсора новую ноду, смещая последующие
        @Override
        public void add(Object o) {
            if(!status){
                throw new IllegalStateException("Please use next() or previous() before called this method");
            }
            Node added = new Node(o);
            lastCalled.setNext(current);
            lastCalled.setPrev(previous);
            previous.setNext(lastCalled);
            current.setNext(lastCalled);
            size++;
            cursorIndex++;

        }
    }


    //класс описывающий ноду
    class Node {
        private Object data;
        private Node next;
        private Node prev;


        public Node() {
        }

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