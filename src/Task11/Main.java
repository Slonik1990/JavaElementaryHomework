package Task11;


import javax.swing.text.html.HTMLDocument;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrList arrList = new ArrList();
        arrList.add(1);
        arrList.add(2);
        arrList.add(6);




        NodeList n = new NodeList();
        n.add(1);
        n.add(2);
        n.add(6);
        n.add(6);
        System.out.println(n.retainAll(arrList));
        System.out.println(n);


    }


}


