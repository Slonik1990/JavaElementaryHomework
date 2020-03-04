package Task17GenericsAtList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Object obj[] = new Object[0];
        MyArrayList list = new MyArrayList();
        list.add(null);
        Object a = list.get(0);
        System.out.println(a);

        list.toArray(obj);
        System.out.println(list.contains(null));
        System.out.println(obj);

    }
}
