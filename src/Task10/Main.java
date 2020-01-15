package Task10;

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
        first.pop();
        System.out.println(first);//NodeList content: HEAD-> [cccc] [bbbb] [aaaa] <- TAIL
        System.out.println(first.contains("bbbb"));//true
        System.out.println(first.contains("bbb"));//false
        first.removeFirst();
        first.removeFirst();
        first.removeLast();
        first.removeLast();

        System.out.println(first);


    }
}
