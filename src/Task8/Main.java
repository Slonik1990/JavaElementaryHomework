package Task8;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        Collection z = new MyContainer();
        String aaa = new String("priver");
        String bbb = new String("zdarova");
        z.add(aaa);
        z.add(bbb);
        System.out.println(((MyContainer) z).getObject(aaa));
        System.out.println(aaa);
        aaa = bbb;
        System.out.println(((MyContainer) z).getObject(aaa));
        System.out.println(aaa);
        System.out.println(((MyContainer) z).getElement(0));



        Collection x = new MyContainer();
        x.add(0);
        x.add(20);
        x.add(20);
        x.add(50);
        x.add(30);
        x.add(40);
        Collection y = new MyContainer();
        y.add(20);
        y.add(0);
        y.add(40);

        System.out.println(test(x, y));//true


        Collection first = new MyContainer();

        //тест вывода на экран и добавления элемента
        System.out.println(first);//Container is empty
        first.add("Hello");
        System.out.println(first);//Container content: [Hello]
        first.add(3);
        System.out.println(first);//Container content: [Hello] [3]
        first.add('A');
        System.out.println(first);//Container content: [Hello] [3] [A]

        //addAll
        first.addAll(first);
        System.out.println(first);//Container content: [Hello] [3] [A] [Hello] [3] [A]

        //тест преобразования в массив
        Object [ ] arr = first.toArray();
        System.out.println(arr[0]);//Hello
        System.out.println(arr[1]);//3
        System.out.println(arr[2]);//
        System.out.println(arr[3]);//Hello
        System.out.println(arr[4]);//3
        System.out.println(arr[5]);//A

        //тест getElement
        System.out.println(((MyContainer) first).getElement(0));//Hello
        System.out.println(((MyContainer) first).getElement(1));//3
        System.out.println(((MyContainer) first).getElement(2));//A
        System.out.println(((MyContainer) first).getElement(3));//Hello
        System.out.println(((MyContainer) first).getElement(4));//3
        System.out.println(((MyContainer) first).getElement(5));//A


//        System.out.println(((MyContainer) first).getElement(6));//ошибка


        //проверка на баги при работе с пустой коллекцией
        Collection second = new MyContainer();//пустой контейнер
        Object[] emptyArr = second.toArray();//пустой массив
        first.addAll(second);
        System.out.println(first);//Container content: [Hello] [3] [A] [Hello] [3] [A]

        System.out.println(emptyArr);//[Ljava.lang.Object;@1b6d3586

        System.out.println(first.contains("Hello"));//true
        System.out.println(first.contains(3));//true
        System.out.println(first.contains('A'));//true
        System.out.println(first.contains('a'));//false

        first.remove("Hello");
        System.out.println(first);//Container content:  [3] [A] [Hello] [3] [A]
        first.remove("Hello");
        System.out.println(first);//Container content:  [3] [A] [3] [A]
      ((MyContainer) first).removeEvery(3);
        System.out.println(first);//Container content:  [A] [A]


    }


    //remove и retane являются противоположными операциями, и совместно должны полностью очистить коллекцию
    public static boolean test(Collection a, Collection b){
        a.removeAll(b);
        a.retainAll(b);
        return a.isEmpty();
    }


}
