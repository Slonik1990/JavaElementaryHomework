package Task15Map;

public class Main {
    public static void main(String[] args) {


        MyMap my = new MyMap(5);
        my.put("a", "Привет");
        my.put("b", "Дом");
        my.put("c", "куку");
        my.put("d", null);
        my.put("e", "аыва");
        my.put("f", "Дом");
        my.put("g", "ыва");
        my.put("h", "ываыва");
        my.put("i", "Дом");
        my.put("j", "авп");
        my.put("k", "впр");
        my.put("l", "Дом");
        my.put("m", "вапвап");
        my.put("n", "рапрапр");
        my.put("o", "Двпрвом");
        my.put("p", null);
        System.out.println(((MyMap) my).size());
        System.out.println(my.keySet());
        System.out.println(my.entrySet());
        System.out.println(my.values());
        System.out.println(my.containsKey("a"));
        System.out.println(my.containsKey("q"));
        System.out.println(my.containsValue(null));
        System.out.println(my.containsValue("Привет"));
        System.out.println(my.get("z"));
        System.out.println(my.remove("o"));
        System.out.println(my.remove("p"));
        System.out.println(((MyMap) my).size());



    }

}