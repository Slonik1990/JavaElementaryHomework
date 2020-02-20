package Task17GenericsAtList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List list = new MyArrayList();
        list.add("hello");
        Object a = list.get(0);
        System.out.println(a);

    }
}
