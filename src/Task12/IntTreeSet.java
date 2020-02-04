package Task12;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class IntTreeSet implements Set {
    private IntTreeNode root;
    private int size;

    public IntTreeSet() {
    }

    //выводит на экран часть древа, исходящую от принимаемой ноды
    //реализован с использованием рекурсивного обхода
    public String partToString(IntTreeNode prev) {


        if (size==0){
            return "Tree is empty";
        }

        String res = "";

        //обход левой ветки
        if (prev.getLeft() != null) {
            res += partToString(prev.getLeft());
        }

        //вывод на экран содержимого самой ноды
        res += "[" + prev.getData() + "] ";

        //обход правой ветки
        if (prev.getRight() != null) {
            res += partToString(prev.getRight());
        }


        return res;
    }

    //передача в проршлый метод корня всего древа в качестве аргумента, что выводит на экран все элементы множества
    @Override
    public String toString() {
        System.out.print("TreeSet content: ");
        return partToString(root);
    }


    //возвращает true если элемент добавлен
    @Override
    public boolean add(Object o) {
        IntTreeNode created = new IntTreeNode((int) o);//полученные данные заворачиваются в новую ноду
        //если дерево не содержит элементов, новая нода становится его корнем
        if (size == 0) {
            this.root = created;
            this.size++;
            return true;

        } else {
            if (contains(o)) {
                return false;
            }

            IntTreeNode current = root; //указатель который будет перемещаться по дереву и искать свободное место для созданной ноды
            IntTreeNode prev = root;//нода из которой перешел указатель по правильному направлению

            //цикл продвигает указатель по древу с учетом предусмотренной логики данной структуры данных
            //определяет ноду, которая станет родительской(prev) для добавляемой(created)
            while (current != null) {
                //условие продвижения влево
                if (current.getData() > created.getData()) {
                    prev = current;
                    current = prev.getLeft();

                    //условие продвижения вправо
                } else if (current.getData() < created.getData()) {
                    prev = current;
                    current = prev.getRight();

                    //встреча с таким же значением
                    //по сути благодаря следующему блоку можно отказаться от вызова contains, либо убрать следующий блок и ограничиться проверкой
                    //но пока я не знаю, что лучше, пусть будет все
                } else {
                    return false;
                }
            }

            //добавление ноды в правильном направлении
            if (created.getData() < prev.getData()) {
                prev.setLeft(created);
                created.setParent(prev);
            } else {
                prev.setRight(created);
                created.setParent(prev);

            }
            size++;


        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) {
            return false;
        }
        IntTreeNode current = root;//текущая нода

        //цикл будет продвигаться по древу и сравнивать каждый пройденный узел, пока не обнаружит совпадение или не достигнет конца древа
        //благодаря заложенной в алгоритм логике, будет проходить только часть древа, а не сканировать все узлы
        while (current != null) {
            //условие продвижения влево
            if (current.getData() > (int) o) {
                current = current.getLeft();

                //условие продвижения вправо
            } else if (current.getData() < (int) o) {
                current = current.getRight();

                //встреча с таким же значением
            } else {
                return true;
            }
        }


        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        return new TreeIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

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
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }




    //итератор
    class TreeIterator implements Iterator {

        IntTreeNode current = root;
        IntTreeNode lastCalled = root;


        public TreeIterator() {
            if(size==0){
                System.out.println("Нельзя создать итератор для пустого дерева");
                return;
            }
            while (current.getLeft()!=null){
                current = current.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return current!=null;
        }


        //метод возращает элемент коллекции и продвигает курсор к элементу, который будет возвращаен следующим вызовом
        @Override
        public Object next() {
            lastCalled = current;//сохраняется действующее положение для возврата и начинается поиск следующего элемента

            //итератор начинает свой путь с минимального, наилевейшего элемента, у которого однозначно нет левой ветки
            //если у текущей ноды есть правый потомок, курсор направляется туда и располагается в минимальном элементе ветви
            if(current.hasRight()){
                current = current.getRight();
                while (current.getLeft()!=null){
                    current = current.getLeft();
                }

            }else {

                do{
                    current = current.getParent();

                    if(current == null){
                        break;
                    }
                }
                while (current.getData()<lastCalled.getData());
                //вышеописанный блок можно было бы построить на анализе того из какой ветки мы откатились
                //если пришли к предку из правой ветки - предок уже пройден итератором и нужно делать обращение к его предку
                //если пришли из левой - предок необходимая нам нода
            }
            return lastCalled.getData();
        }


    }


    //класс описывающий структурную единицу нашего дерева
    class IntTreeNode {
        private IntTreeNode left;
        private IntTreeNode right;
        private IntTreeNode parent;
        private int data;

        //конструктор
        public IntTreeNode(int o) {
            this.data = o;
        }


        public IntTreeNode getLeft() {
            return left;
        }

        public void setLeft(IntTreeNode left) {
            this.left = left;
        }

        public IntTreeNode getRight() {
            return right;
        }

        public void setRight(IntTreeNode right) {
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }


        public IntTreeNode getParent() {
            return parent;
        }

        public void setParent(IntTreeNode parent) {
            this.parent = parent;
        }

        public boolean hasRight(){
            return getRight()!=null;
        }
    }
}
