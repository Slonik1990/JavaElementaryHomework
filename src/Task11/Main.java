package Task11;


import java.util.ArrayList;
import java.util.Collection;



public class Main {

    public static void main(String[] args) {

        Object [] arr = new Object[4];
        ArrList x = new ArrList();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);
        Object [] obj = new Object[]{5, 5, 5 ,5};
        obj[0] = x.remove((Object)22);
        System.out.println(x);



        System.out.println(obj[0]);








    }




    }


