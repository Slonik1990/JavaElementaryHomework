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
        n.add(9);
        n.add(0);
        n.add(1);
        n.add(2);
        n.add(3);
        n.add(4);
        n.add(0);

        System.out.println(n);

        System.out.println(n.lastIndexOf(9));

        System.out.println(n);

        List w = (NodeList)n.subList(1,2);
        System.out.println(w);


    }


}


