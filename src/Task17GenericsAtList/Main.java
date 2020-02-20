package Task17GenericsAtList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        String a = list.get(0);
        System.out.println(a);

    }
}
