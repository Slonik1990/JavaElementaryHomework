package Task11;


import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        Object[] arr = new Object[4];
        ArrList x = new ArrList();

        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);


        Iterator itX = x.iterator();
        System.out.println(itX.hasNext());
        System.out.println(itX.next());
        System.out.println(itX.hasNext());
        System.out.println(itX.next());
        System.out.println(itX.hasNext());
        System.out.println(itX.next());
        System.out.println(itX.hasNext());
        System.out.println(itX.next());
        System.out.println(itX.hasNext());
        System.out.println(itX.next());


        ArrList y = new ArrList();
        y.add(1);
        y.add(2);
        y.add(3);
        y.add(4);


    }


}


