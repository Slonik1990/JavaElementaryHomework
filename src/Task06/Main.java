package Task06;

public class Main {
    public static void main(String[] args) {
        IntContainer first = new IntContainer();

        first.add(1);
        first.add(2);
        first.add(3);
        first.add(4);
        first.add(5);
        first.add(6);
        first.add(7);
        first.add(8);
        first.print();//List: 1, 2, 3, 4, 5, 6, 7, 8,
        int[]donor = {9,9,9};
        first.addAll(donor);//добавление массива в коллекцию
        first.print();//List: 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9,
        first.addAll(first);//добавление коллекции саму в себя
        first.print();//List: 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9,

        first.bubbleSort();
        first.print();//List: 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9, 9,

        first.downSort();
        first.print();//List: 9, 9, 9, 9, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1,


        System.out.println(first.findIndex(0));//-1
        first.clear();//Контейнер очицен
        System.out.println(first.getSize());//0
    }

}
