package Task10;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyNodeList first = new MyNodeList();
        System.out.println(first);//NodeList is empty
        first.push("aaaa");
        System.out.println(first);//NodeList content: HEAD-> [aaaa] <- TAIL
        first.push("bbbb");
        Iterator itFirst = first.iterator();
        first.push("cccc");
        first.push("ddd");
        System.out.println(first.size());
        System.out.println(first);//NodeList content: HEAD-> [ddd] [cccc] [bbbb] [aaaa] <- TAIL

        System.out.println(itFirst.hasNext());//true
        System.out.println(itFirst.next());//MyNodeList$Node@1b6d3586
        System.out.println(itFirst.next());//MyNodeList$Node@4554617c

        System.out.println(itFirst.next());//null потому что ноды добавленные методом push добавляются перед положением итератора

        System.out.println(first.nodeToString(first.getPrevious(first.getFirst())));//null
        System.out.println(first.nodeToString(first.getPrevious(first.getLast())));//[bbbb]
        first.pop();
        System.out.println(first);//NodeList content: HEAD-> [cccc] [bbbb] [aaaa] <- TAIL
        System.out.println(first.contains("bbbb"));//true
        System.out.println(first.contains("bbb"));//false



        first.remove("bbbb");
        first.remove("aaaa");

        System.out.println(first);//NodeList content: HEAD-> [cccc] <- TAIL

        first.remove("cccc");

        System.out.println(first);//NodeList is empty


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



        for (Object n: second) {
            System.out.print(MyNodeList.nodeToString(n)); //[1] [2] [3] [4] [5] [6]
        }


        System.out.println(second);//NodeList content: HEAD-> [1] [2] <- TAIL





    }
}
