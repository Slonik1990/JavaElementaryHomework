package Task15;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Map slovar = new Vocabluary();


        slovar.put("aaa", "Дом");
        slovar.put("aab", "Дым");

        System.out.println(slovar.size());

        Iterator it = ((Vocabluary) slovar).iterator();
        System.out.println(it.hasNext());

        Vocabluary.Entry a = (Vocabluary.Entry)it.next();
        Vocabluary.Entry b = (Vocabluary.Entry)it.next();
        System.out.println(a.getEng());
        System.out.println(b.getEng());

        System.out.println(slovar.containsValue("Дам"));

        Set ts = slovar.keySet();
        System.out.println(ts);
        Collection coll = slovar.values();
        System.out.println(coll);









    }

}
