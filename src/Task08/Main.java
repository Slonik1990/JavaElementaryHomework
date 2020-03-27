package Task08;


import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        Collection x = new MyContainer();

        Collection y = new MyContainer();
      x.add(1);
        System.out.println(x);
      x.remove(100);


    }


    //remove и retane являются противоположными операциями, и совместно должны полностью очистить коллекцию
    public static boolean test(Collection a, Collection b){
        System.out.println("\nTest result:");

        a.removeAll(b);
        a.retainAll(b);
        return a.isEmpty();
    }


}
