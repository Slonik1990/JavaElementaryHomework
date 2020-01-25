package Task12;

import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {



        IntTreeSet my = new IntTreeSet();



        my.add(50);
        my.add(30);
//        my.add(70);
//        my.add(10);
//        my.add(40);
//        my.add(60);
//        my.add(80);
//
//        my.add(1000);

        System.out.println(my);//TreeSet content: [30] [50]
        System.out.println();
        Iterator iter = my.iterator();

        System.out.println(iter.hasNext());//true
        System.out.println(iter.next());//30
        System.out.println(iter.hasNext());//true
        System.out.println(iter.next());//50
        System.out.println(iter.hasNext());//false

        for (Object i: my){

            System.out.print (i + " ");//30 50

        }




    }
}
