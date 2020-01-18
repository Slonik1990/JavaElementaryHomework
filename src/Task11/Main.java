package Task11;


import javax.swing.text.html.HTMLDocument;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrList arrList = new ArrList();
        arrList.add(1);
        arrList.add(1);
        arrList.add(1);
        arrList.add(1);

        Object[] o = new Object[]{1,2,3};

        NodeList nL = new NodeList(arrList);
        System.out.println(nL);
        System.out.println(nL.isEmpty());
        System.out.println(nL.contains(2));
        nL.add(2);
        System.out.println(nL.contains(2));





    }


}


