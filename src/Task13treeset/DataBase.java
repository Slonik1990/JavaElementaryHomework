package Task13treeset;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

//TreeSet с аккаунтами вкладчиков
public class DataBase implements Set {
    private Account root;
    private Comparator comparator;
    private int size;

    public DataBase() {
    }

    public DataBase(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public String toString() {
        return printSubTree(root);
    }

    public String printSubTree(Account node) {
        if (size == 0) {
            return "База данных пуста";
        }
        String res = "";
        //обход левой ветки
        if (node.getLeft() != null) {
            res += printSubTree(node.getLeft());
        }

        //вывод на экран содержимого самой ноды
        res += node.getInvestor().toString() + "\n";

        //обход правой ветки
        if (node.getRight() != null) {
            res += printSubTree(node.getRight());
        }
        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //проверяет наличие вкладчика
    @Override
    public boolean contains(Object o) {
        if (size == 0 || o == null) {
            return false;
        } else {
            int compRes;
            Investor inv = (Investor) o;
            Account current = root; //указатель который будет перемещаться по дереву

            while (current != null) {

                if (comparator != null) {
                    compRes = comparator.compare(current.getInvestor(), inv);
                } else {
                    compRes = current.getInvestor().compareTo(inv);
                }

                if (compRes > 0) {
                    current = current.getLeft();
                } else if (compRes < 0) {
                    current = current.getRight();
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    //возвращает объект по имени
    public Investor get(String key) {
        Investor toReturn = null;

        if (size == 0 || key == null) {
            return toReturn;

        } else {
            Investor buffer = new Investor(key, 0, 0);
            Account current = root; //указатель который будет перемещаться по дереву
            int compRes;
            while (current != null) {

                if (comparator != null) {
                    compRes = comparator.compare(current.getInvestor(), buffer);
                } else {
                    compRes = current.getInvestor().compareTo(buffer);
                }

                if (compRes > 0) {
                    current = current.getLeft();
                } else if (compRes < 0) {
                    current = current.getRight();
                } else {
                    toReturn = current.getInvestor();
                    return toReturn;
                }
            }
        }
        return toReturn;
    }

    //возвращает информацию о вкладе
    public void investorInfo(String name) {
        if (get(name) != null) {
            System.out.println(get(name).toString());
        } else {
            System.out.println("Вкладчик не обнаружен");

        }
    }

    @Override
    public Iterator iterator() {
        return new BankIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Investor[size];
        int count = 0;
        for (Object o : this) {
            arr[count] = o;
            count++;
        }
        return arr;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (size >= a.length) {
            a = toArray();
            return a;
        } else {
            int count = 0;
            for (Object o : this) {
                a[count] = o;
                count++;
            }
            a[size] = null;

            return a;
        }
    }

    //добавление
    @Override
    public boolean add(Object o) {
        Account created = new Account((Investor) o);//оборачивание добавляемого объекта
        if (isEmpty()) {
            root = created;
            size++;
            return true;
        } else {
            return addRecursively(root, created);
        }
    }


    //метод добавляет ноду в дерево в правильное место
    private boolean addRecursively(Account current, Account added) {

        //переменная принимающая в себя результаты сравнений
        int compRes;

        //вычисление переменной сравнения на основе натурального сравнения либо принимаемого компаратора
        if (comparator != null) {
            compRes = comparator.compare(current.getInvestor(), added.getInvestor());
        } else {
            compRes = current.getInvestor().compareTo(added.getInvestor());
        }

        //запись либо рекурсивное продвижение в нужном направлении до обнаружения свободного места
        if (compRes > 0) {
            if (current.left == null) {
                current.setLeft(added);
                added.setParent(current);
                size++;
                return true;
            } else {
                addRecursively(current.getLeft(), added);
            }
        } else if (compRes < 0) {
            if (current.right == null) {
                current.setRight(added);
                added.setParent(current);
                size++;
                return true;
            } else {
                addRecursively(current.getRight(), added);
            }
        } else {
            return false;
        }
        return false;
    }

    //выполняет свою задачу хорошо, но превращает дерево в связный список из за упорядоченности добавления
    //Todo подумать над самобалансировкой дерева и оптимизацией этого метода
    @Override
    public boolean remove(Object o) {
        String name = (String) o;
        DataBase buffer = new DataBase();
        Iterator iter = new BankIterator();
        while (iter.hasNext()) {
            Investor inv = (Investor) iter.next();
            if (!inv.getName().equals(name)) {
                buffer.add(inv);
            }
        }
        clear();
        addAll(buffer);
        return true;
    }


    @Override
    public boolean containsAll(Collection c) {
        if (c == null || this.getClass() != c.getClass()) {
            return false;
        } else {

            int count = 0;
            for (Object o : c) {
                Investor i = (Investor) o;
                if (this.contains(i)) {
                    count++;
                }
            }
            return c.size() == count;
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (c == null || this.getClass() != c.getClass()) {
            return false;
        } else {
            for (Object o : c) {
                this.add(o);
            }
            return true;
        }
    }

    //методы removeAll и retainAll основаны на неоптимальном методе remove
    //эти методы обладают сложностью минимум n^2 из за вложенных циклов, что очень плохо
    @Override
    public boolean retainAll(Collection c) {
        if (c == null || this.getClass() != c.getClass()) {
            return false;
        }
        Iterator iter = new BankIterator();
        while (iter.hasNext()) {
            Investor inv = (Investor) iter.next();
            if (!c.contains(inv)) {
                remove(inv.getName());
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null || this.getClass() != c.getClass()) {
            return false;
        }
        Iterator iter = new BankIterator();
        while (iter.hasNext()) {
            Investor inv = (Investor) iter.next();
            if (c.contains(inv)) {
                remove(inv.getName());
            }
        }
        return true;
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    //сумма всех вкладов
    public int totalDepositVal() {
        int total = 0;
        for (Object o : this) {//итератор устроен так, что возвращает сразу вкладчика, а не ноду
            Investor a = (Investor) o;
            total = total + a.getDeposit();
        }
        return total;
    }

    //класс оборачивающий вкладчика в ноду, содержащую непосредственно объект Investor, а также отдельно его имя в качестве ключа
    public static class Account {
        private Account left;
        private Account right;
        private Account parent;
        private Investor investor;

        //конструктор
        public Account(Investor inv) {
            this.investor = inv;
        }


        //геттеры, сеттеры
        public Account getLeft() {
            return left;
        }

        public void setLeft(Account left) {
            this.left = left;
        }

        public Account getRight() {
            return right;
        }

        public void setRight(Account right) {
            this.right = right;
        }

        public Account getParent() {
            return parent;
        }

        public void setParent(Account parent) {
            this.parent = parent;
        }


        public Investor getInvestor() {
            return investor;
        }

        public boolean hasRight() {
            return getRight() != null;
        }

        public void setInvestor(Investor investor) {
            this.investor = investor;
        }
    }

    //итератор
    class BankIterator implements Iterator {

        Account current = root;
        Account lastCalled = root;


        //конструктор
        public BankIterator() {
            if (size == 0) {
                return;
            }
            while (current.getLeft() != null) {
                current = current.getLeft();
            }
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }


        //метод возращает элемент коллекции и продвигает курсор к элементу, который будет возвращаен следующим вызовом
        @Override
        public Object next() {

            if (!hasNext()) {
                return null;
            }
            lastCalled = current;
            if (current.hasRight()) {
                current = current.getRight();
                while (current.getLeft() != null) {
                    current = current.getLeft();
                }

            } else {
                int compRes;
                do {
                    current = current.getParent();
                    if (current == null) {
                        break;
                    }
                    if (comparator != null) {
                        compRes = comparator.compare(lastCalled.getInvestor(), current.getInvestor());
                    } else {
                        compRes = lastCalled.getInvestor().compareTo(current.getInvestor());
                    }
                }
                while (compRes > 0);

            }
            return lastCalled.getInvestor();
        }


    }
}
