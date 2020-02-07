package Task14;


import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;


public class BankDataBase implements Set {


    private Account root;
    private int size;
    private Comparator myComparator;
    private Situation bankChange;//при разных изменениях в экономике, банк реагирует по разному. поведение описано в данном поле

    //дефолтный конструктор
    public BankDataBase() {
    }

    //конструктор устанавливающий компаратор
    public BankDataBase(Comparator comparator) {
        this.myComparator = comparator;
    }

    //когда произошла некоторая ситуация и банк готов на нее реагировать, она передается в сеттер
    //в сеттере сразу же происходят реакции для всех вкладчиков
    public void setBankChange(Situation bankChange) {
        this.bankChange = bankChange;
        //в сеттере сразу же происходят реакции для всех вкладчиков
        //методы принимаеют действующие значения полей вкладчика, возвращают и устанавливают измененное
        for (Object o : this) {
            Investor inv = (Investor) o;
            inv.setMoney(bankChange.moneyChange(inv.getMoney()));
            inv.setPercent(bankChange.percentChange(inv.getPercent()));
        }

    }

    @Override
    public String toString() {
        return printSubTree(root);
    }


    public String printSubTree(Account node) {
        if (size == 0) {
            return "Database is empty";
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
        if (myComparator != null) {
            compRes = myComparator.compare(current.getKey(), added.getKey());
        } else {
            compRes = current.getKey().compareTo(added.getKey());
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
            System.out.println("Данный ключ уже занят");
            return false;
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

    //проверяет наличие вкладчика по ключу
    @Override
    public boolean contains(Object o) {
        if (size == 0 || o == null) {
            return false;
        } else {
            int compRes;
            String name = (String) o;
            Account current = root; //указатель который будет перемещаться по дереву

            while (current != null) {

                if (myComparator != null) {
                    compRes = myComparator.compare(current.getKey(), name);
                } else {
                    compRes = current.getKey().compareTo(name);
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


    //возвращает объект по ключу
    public Investor get(String key) {
        Investor inv = null;
        if (size == 0 || key == null) {
            return null;

        } else {
            Account current = root; //указатель который будет перемещаться по дереву
            while (current != null) {
                if (current.getKey().compareTo(key) > 0) {
                    current = current.getLeft();
                } else if (current.getKey().compareTo(key) < 0) {
                    current = current.getRight();
                } else {
                    inv = current.getInvestor();
                    return inv;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return new BankDataIterator();
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


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c == null || this.getClass() != c.getClass()) {
            return false;
        } else {
            int cSize = c.size();
            int count = 0;
            for(Object o : c){
                Investor i = (Investor)o;
                if(this.contains(i)){
                    count++;
                }
            }

        return cSize == count;
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (c == null || this.getClass() != c.getClass()) {
            return false;
        } else {
            for(Object o : c){
                this.add(o);
            }
            return true;
        }
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
    public void clear() {
        root = null;

    }


    //итератор
    class BankDataIterator implements Iterator {

        Account current = root;
        Account lastCalled = root;


        public BankDataIterator() {
            if (size == 0) {
                System.out.println("Нельзя создать итератор для пустого дерева");
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
            //если после прошлого выполнения курсор перешел за рамки древа, итератор прекращает работу
            if (!hasNext()) {
                return null;
            }
            lastCalled = current;//сохраняется действующее положение для возврата и начинается поиск следующего элемента

            //элемент обработает себя и попытается найти минимальное значение правой ветки, если она есть
            if (current.hasRight()) {
                current = current.getRight();
                while (current.getLeft() != null) {
                    current = current.getLeft();
                }

                //если правой ветки нет, нужно переходить к родителю, но иногда родитель, к которому перейдет курсор уже обработан
                // в такой ситуации происходит поиск прародителя, который еще не был обработан
            } else {
                int compRes;//переменная задающая логику сравнения, которую получает от компаратора
                do {
                    current = current.getParent();//переход к родителю
                    if (current == null) {//такая ситуация говорит о том, что все дерево пройдено и курсор перешел к родителю корня
                        break; //поиск следующего элемента прекращается
                    }

                    if (myComparator != null) {
                        compRes = myComparator.compare(lastCalled.getKey(), current.getKey());
                    } else {
                        compRes = lastCalled.getKey().compareTo(current.getKey());
                    }
                }
                while (compRes > 0);//

            }
            return lastCalled.getInvestor();
        }


    }


    //класс оборачивающий вкладчика в ноду, содержащую непосредственно объект Investor, а также отдельно его имя в качестве ключа
    public static class Account implements Comparable {
        private Account left;
        private Account right;
        private Account parent;
        private String key;
        private Investor investor;

        //конструктор
        public Account(Investor inv) {
            this.key = inv.getName();
            this.investor = inv;
        }

        @Override
        public int compareTo(Object o) {
            Account a = (Account) o;
            return getKey().compareTo(((Account) o).getKey());
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

        public String getKey() {
            return key;
        }

        public Investor getInvestor() {
            return investor;
        }

        public boolean hasRight() {
            return getRight() != null;
        }
    }
}
