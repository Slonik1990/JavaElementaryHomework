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
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }


    class NodeIterator implements Iterator {
        //нода на которую не имеют ссылок элементы списка
        //как шпион постоянно находится между элементами списка
        Node cursor = new Node();

        public NodeIterator(){
            cursor.setNext(head);
            cursor.setPrev(null);
        }

        @Override
        public boolean hasNext() {
            return cursor.getNext()!=null;
        }

        @Override
        public Node next() {
            if(cursor.getNext()==null){
                return null;
            } else {
                cursor.setPrev(cursor.getNext());
                cursor.setNext(cursor.getNext().getNext());
                return cursor.getPrev();
            }
        }
    }

    class NodeListIterator  extends NodeIterator implements ListIterator {
        @Override
        public boolean hasNext() {
            return super.hasNext();
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }




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
