package Task10;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyNodeList first = new MyNodeList();
        System.out.println(first);//NodeList is empty
        first.push("aaaa");
        System.out.println(first);//NodeList content: HEAD-> [aaaa] <- TAIL
        first.push("bbbb");
        first.push("cccc");
        first.push("ddd");
        System.out.println(first.size());
        System.out.println(first);//NodeList content: HEAD-> [ddd] [cccc] [bbbb] [aaaa] <- TAIL

        System.out.println(first.nodeToString(first.getPrevious(first.getHead())));//null
        System.out.println(first.nodeToString(first.getPrevious(first.getTail())));//[bbbb]
        first.pop();
        System.out.println(first);//NodeList content: HEAD-> [cccc] [bbbb] [aaaa] <- TAIL
        System.out.println(first.contains("bbbb"));//true
        System.out.println(first.contains("bbb"));//false



        first.remove("bbbb");
        first.remove("aaaa");

        System.out.println(first);//NodeList content: HEAD-> [cccc] <- TAIL

        first.remove("cccc");

        System.out.println(first);//NodeList is empty


        Iterator itFirst = first.iterator();
        System.out.println(itFirst.next());//null
        System.out.println(itFirst.hasNext());//false

        MyNodeList second = new MyNodeList();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);
        MyNodeList third = new MyNodeList();
        third.add(3);
        third.add(4);
        third.add(5);
        third.add(6);


        second.removeAll(third);
        System.out.println(second);//NodeList content: HEAD-> [1] [2] <- TAIL
        second.addAll(third);
        System.out.println(second);//NodeList content: HEAD-> [1] [2] [3] [4] [5] [6] <- TAIL

        Iterator itSecond = second.iterator();
        System.out.println(itSecond.next());//Task10.Node@1b6d3586
        System.out.println(itSecond.hasNext());//true


        second.retainAll(third);
        System.out.println(second);//NodeList content: HEAD-> [1] [2] <- TAIL





    }
}
