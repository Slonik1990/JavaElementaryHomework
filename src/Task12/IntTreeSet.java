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
    public void partToString(IntTreeNode parent) {

        //обход левой ветки
        if (parent.getLeft() != null) {
            partToString(parent.getLeft());
        }

        //вывод на экран содержимого самой ноды
        System.out.print("[" + parent.getData() + "] ");

        //вывод правой ветки
        if (parent.getRight() != null) {
            partToString(parent.getRight());
        }
    }

    //передача в проршлый метод корня всего древа в качестве аргумента, что выводит на экран все элементы множества
    @Override
    public String toString() {
        partToString(root);
        return null;
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
            IntTreeNode parent = root;//нода из которой перешел указатель по правильному направлению

            //цикл продвигает указатель по древу с учетом предусмотренной логики данной структуры данных
            //определяет ноду, которая станет родительской(parent) для добавляемой(created)
            while (current != null) {
                //условие продвижения влево
                if (current.getData() > created.getData()) {
                    parent = current;
                    current = parent.getLeft();

                    //условие продвижения вправо
                } else if (current.getData() < created.getData()) {
                    parent = current;
                    current = parent.getRight();

                    //встреча с таким же значением
                    //по сути благодаря следующему блоку можно отказаться от вызова contains, либо убрать следующий блок и ограничиться проверкой
                    //но пока я не знаю, что лучше, пусть будет все
                } else {
                    return false;
                }
            }

            //добавление ноды в правильном направлении
            if (created.getData() < parent.getData()) {
                parent.setLeft(created);
            } else {
                parent.setRight(created);
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
        return null;
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


}

//класс описывающий структурную единицу нашего дерева
class IntTreeNode {
    private IntTreeNode left;
    private IntTreeNode right;
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


}
