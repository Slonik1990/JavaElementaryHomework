package Task14;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class BankDataBase implements Set {


    private Account root;
    private int size;
    private Situation bankChange;//при разных изменениях в экономике, банк реагирует по разному. поведение описано в данном поле


    //когда произошла некоторая ситуация и банк готов на нее реагировать, она передается в сеттер
    //в сеттере сразу же происходят реакции для всех вкладчиков
    public void setBankChange(Situation bankChange) {
        this.bankChange = bankChange;
        //в сеттере сразу же происходят реакции для всех вкладчиков
        //методы принимаеют действующие значения полей вкладчика, возвращают и устанавливают измененное
        for(Object o: this){
            Investor inv = (Investor)o;
            inv.setMoney(bankChange.moneyChange(inv.getMoney()));
            inv.setPercent(bankChange.percentChange(inv.getPercent()));
        }

    }

    @Override
    public String toString() {
        return printSubTree(root);
    }


    public String printSubTree(Account node) {
        if (size==0){
            return "Database is empty";
        }
        String res = "";
        //обход левой ветки
        if (node.getLeft() != null) {
            res += printSubTree(node.getLeft());
        }

        //вывод на экран содержимого самой ноды
        res +=   node.getInvestor().toString() + "\n";

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
        return size==0;
    }

    //проверяет наличие вкладчика по ключу
    @Override
    public boolean contains(Object o) {
        if (size == 0 || o == null) {
            return false;
        } else {
            String name = (String)o;
            Account current = root; //указатель который будет перемещаться по дереву

            while (current != null) {
                if (current.getKey().compareTo(name) > 0) {
                    current = current.getLeft();
                } else if (current.getKey().compareTo(name) < 0) {
                    current = current.getRight();
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    //проверяет наличие в дереве ранее созданного вкладчика
    public boolean containsInvestor(Investor inv) {
        if (size == 0 || inv == null) {
            return false;
        } else {
            Account current = root; //указатель который будет перемещаться по дереву

            while (current != null) {

                if (current.getKey().compareTo(inv.getName()) > 0) {
                    current = current.getLeft();

                } else if (current.getKey().compareTo(inv.getName()) < 0) {
                    current = current.getRight();

                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //возвращает ноду содержащую данного вкладчика
    public Account getNode(Investor inv) {
        if (size == 0 || inv == null) {
            return null;
        } else {
            Account current = root; //указатель который будет перемещаться по дереву

            while (current != null) {
                if (current.getKey().compareTo(inv.getName()) > 0) {
                    current = current.getLeft();
                } else if (current.getKey().compareTo(inv.getName()) < 0) {
                    current = current.getRight();
                } else {
                    return current;
                }
            }
        }
        return null;
    }
    //возвращает объект по ключу
    public Investor get (String key){
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
                    inv =  current.getInvestor();
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
        Object [] arr = new Investor[size];
        int count = 0;
        for(Object o: this){
            arr[count] = o;
            count++;
        }
        return  arr;
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

    //добавление вклада при придварительно имеющемся вкладчике
    @Override
    public boolean add(Object o) {
        Account created = new Account((Investor) o);//полученные данные заворачиваются в новую ноду
        //если дерево не содержит элементов, новая нода становится его корнем
        if (size == 0) {
            this.root = created;
            this.size++;
            return true;

        } else {
            Account current = root; //указатель который будет перемещаться по дереву и искать свободное место для созданной ноды
            Account parent = root;//нода из которой перешел указатель по правильному направлению

            //определяет ноду, которая станет родительской(prev) для добавляемой(created)
            while (current != null) {

                if (current.getKey().compareTo(created.getKey()) > 0 ) {
                    parent = current;
                    current = parent.getLeft();


                } else if (current.getKey().compareTo(created.getKey()) < 0 ) {
                    parent = current;
                    current = parent.getRight();

                } else {
                    System.out.println("Данный ключ уже занят");
                    return false;
                }
            }

            //добавление ноды в правильном направлении
            if (parent.getKey().compareTo(created.getKey()) > 0 ) {
                parent.setLeft(created);
                created.setParent(parent);
            } else {
                parent.setRight(created);
                created.setParent(parent);
            }
            size++;
        }
        return true;
    }

    //добавление вклада без предварительного создания вкладчика
    public boolean addNew(String name, int money, int percent){
        Investor newInv = new Investor(name, money, percent);
        return  add(newInv);
    }

    //на потом
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
            if(!hasNext()){
                return null;
            }

            lastCalled = current;//сохраняется действующее положение для возврата и начинается поиск следующего элемента
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
                while (lastCalled.getKey().compareTo(current.getKey())>0);

            }
            return lastCalled.getInvestor();
        }


    }




    //класс оборачивающий вкладчика в ноду, содержащую непосредственно объект Investor, а также отдельно его имя в качестве ключа
   public static class Account{
        private Account left;
        private Account right;
        private Account parent;
        private String key;
        private Investor investor;

        //конструктор создающий ноду на основе существующего вкладчика
        public Account(Investor inv) {
            this.key = inv.getName();
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

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Investor getInvestor() {
            return investor;
        }

        public void setInvestor(Investor investor) {
            this.investor = investor;
        }

        public boolean hasRight(){
            return getRight()!=null;
        }
    }
}
