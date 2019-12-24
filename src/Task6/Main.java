package Task6;

public class Main {
    public static void main(String[] args) {

        Collection first = new Collection(5);
        Collection second = new Collection(6);
        Collection third = new Collection(10);
        first.addToEnd(3);
        first.addToEnd(5);
        first.addToEnd(1);
        first.addToEnd(4);
        first.addToEnd(2);

//        first.remove(4);
//        first.contains(4);
//        System.out.println(first.isEmpty(4));
//        first.indexOf(3);
//        first.test();
//        first.softClear();
//        first.test();
//        first.hardClear();
//        first.test();

        second.addToEnd(2);
        second.addToEnd(3);
        third.addToEnd(2);
        third.addToEnd(3);
        System.out.println(second.valueEquals(third));//true
        System.out.println(second.equals(third));//false

        second.print();//[2] [3]
        third.print(); //[2] [3]
        second.addAllToEnd(third);
        second.print();// [2] [3] [2] [3]
        second.addAllToEnd(third);
        second.print();// [2] [3] [2] [3] [2] [3]

        first.print(); // [3] [5] [1] [4] [2]
        first.bubbleSort();
        first.print(); // [1] [2] [3] [4] [5]
        first.downSort();
        first.print(); //[5] [4] [3] [2] [1]


        first.addAllToEnd(third);//Недостаточно свободного места. Необходимо удалить элементов: 2
        //Удалите элементы вручную методом remove или используйте hardAddingToEnd

        first.hardAddingToEnd(third);//Для подтверждения опасной операции введите RECORD
        //RECORD
        first.print();// [5] [4] [3] [2] [3]
        third.print();//[2] [3]
        third.addAlltoStart(first);
        third.print();//[5] [4] [3] [2] [3] [2] [3]
        third.hardAddToStart(first);//Для подтверждения опасной операции введите RECORD
        third.print();//[5] [4] [3] [2] [3] [5] [4] [3] [2] [3]
        third.hardAddingToEnd(first);//Для подтверждения опасной операции введите RECORD
        third.print();//[5] [4] [3] [2] [3] [5] [4] [3] [2] [3]
        third.bubbleSort();
        third.print();//[2] [2] [3] [3] [3] [3] [4] [4] [5] [5]
        second.print();// [2] [3] [2] [3] [2] [3]
        third.softAdding(second);//4 элемента перенесено
       
    }


}
