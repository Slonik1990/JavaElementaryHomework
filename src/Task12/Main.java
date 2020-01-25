package Task12;

import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {



        IntTreeSet my = new IntTreeSet();


            my.add(4);
            my.add(2);
            my.add(6);
            my.add(1);
            my.add(3);
            my.add(5);
            my.add(7);
            my.add(8);

        System.out.println(my);
        System.out.println();
        Iterator iter = my.iterator();

        System.out.println(iter.hasNext());
        System.out.println(iter.next());//1
        System.out.println(iter.next());//2
        System.out.println(iter.next());//3
        System.out.println(iter.next());//4
        System.out.println(iter.next());//5
        System.out.println(iter.next());//6
        System.out.println(iter.next());//7
        System.out.println(iter.next());//8




    }
}
