package Task11;


import javax.swing.text.html.HTMLDocument;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrList arrList = new ArrList();
        arrList.add(1);
        arrList.add(6);
        arrList.add(9);


        NodeList n = new NodeList();
        n.add(0);
        n.add(1);
        n.add(2);


        System.out.println(n);
        ListIterator it = n.listIterator();
        System.out.println(it.nextIndex());
        it.next();
        System.out.println(it.nextIndex());

        System.out.println(it.previousIndex());
        it.previous();
        System.out.println(it.previousIndex());
        it.next();
        System.out.println(it.nextIndex());
        it.next();
        System.out.println(it.nextIndex());
        for (Object o : n) {
            System.out.print(NodeList.nodeToString(o)); //[0] [1] [2] [3] [4] [5]
        }


    }


}


