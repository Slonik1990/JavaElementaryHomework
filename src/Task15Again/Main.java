package Task15Again;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        MyHash h= new MyHash();
        System.out.println(h.getHash("abc"));


        Dictionary slovar = new Dictionary();

        slovar.put("mouse", "мышь");
        slovar.put("tree", "дерево");
        slovar.put("pencil", "карандаш");
        for(Object o: slovar){
            Dictionary.Entry e = (Dictionary.Entry)o;
            System.out.println(e.toString());
        }
//        [pencil - карандаш]
//        [tree - дерево]
//        [mouse - мышь]

    }
}
