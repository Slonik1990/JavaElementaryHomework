package Task11;


import javax.swing.text.html.HTMLDocument;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrList x = new ArrList();

        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);
        x.add(5);


        ListIterator itX = x.listIterator();


        System.out.println(itX.next());
        System.out.println(itX.next());

        System.out.println(itX.hasNext());
        itX.remove();
        System.out.println(itX.next());
        System.out.println(itX.previous());
        System.out.println(itX.next());



        System.out.println(x);

        ArrList y = new ArrList();
        y.add(1);
        y.add(2);
        y.add(3);
        y.add(4);


    }


}


