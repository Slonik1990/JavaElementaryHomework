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
        ListIterator xIt = x.listIterator();
        System.out.println(xIt.next());
        System.out.println(xIt.next());
        xIt.add(9);
        xIt.add(9);
        System.out.println(x);
        System.out.println(xIt.previous());








    }


}


