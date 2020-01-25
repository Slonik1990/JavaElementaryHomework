package Task12;

import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {



        IntTreeSet my = new IntTreeSet();


            my.add(4);
        my.add(50);
        my.add(30);
        my.add(70);
        my.add(10);
        my.add(40);
        my.add(60);
        my.add(80);
        my.add(15);
        my.add(35);
        my.add(13);
        my.add(55);
        my.add(65);
        my.add(75);

        System.out.println(my);
        System.out.println();
        Iterator iter = my.iterator();

        for (Object i: my){
            System.out.println(i);

        }




    }
}
