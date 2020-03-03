package Task17GenericsAtList;

public class Main {
    public static void main(String[] args) {
        Object[] o = null;
        MyArrayList list = new MyArrayList(o);
        list.add("a");
        Object a = list.get(0);
        System.out.println(a);

        System.out.println(list.contains(null));

    }
}
