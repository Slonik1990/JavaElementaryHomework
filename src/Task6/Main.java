package Task6;

public class Main {
    public static void main(String[] args) {

        Collection first = new Collection(5);
        Collection second = new Collection(6);
        Collection third = new Collection(2);
        first.add(3);
        first.add(5);
        first.add(1);
        first.add(4);
        first.add(2);

//        first.remove(4);
//        first.contains(4);
//
//        System.out.println(first.isEmpty(4));
//        first.indexOf(3);
//        first.test();
//        first.softClear();
//        first.test();
//        first.hardClear();
//        first.test();


        second.add(2);
        second.add(3);
        third.add(2);
        third.add(3);

        System.out.println(second.valueEquals(third));//true
        System.out.println(second.equals(third));//false

        second.print();//[2] [3]
        third.print(); //[2] [3]
        second.addAll(third);
        second.print();// [2] [3] [2] [3]
        second.addAll(third);
        second.print();// [2] [3] [2] [3] [2] [3]
        second.addAll(third);//Недостаточно свободного места. Необходимо удалить элементов: 2
        first.print(); // [3] [5] [1] [4] [2]

        first.bubbleSort();
        first.print(); // [1] [2] [3] [4] [5]

        first.downSort();
        first.print(); //[5] [4] [3] [2] [1]

        second.downSort();
        second.print(); //[3] [3] [3] [2] [2] [2]

        second.bubbleSort();
        second.print(); //[2] [2] [2] [3] [3] [3]








    }


}
