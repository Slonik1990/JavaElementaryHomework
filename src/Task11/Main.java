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

        NodeList nL = new NodeList(arrList);
        System.out.println(nL);


    }


}


