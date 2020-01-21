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
        n.add(3);
        n.add(4);
        n.add(5);
        n.add(6);
        n.add(7);
        n.add(8);
        n.add(9);
        System.out.println(n);

        //тест: цепочка преобразований, которая в конце должна вернуть исходный индекс
        int testIndex = 5;//
        NodeList.Node testNode = n.getNode(testIndex);//int -> Node
        testIndex = n.getNodeIndex(testNode);//Node -> int
        Object testObj = n.get(testIndex);//int -> Object
        testIndex = n.indexOf(testObj);//Object -> int
        System.out.println(testIndex);//5

        ListIterator it = n.listIterator();












    }


}


