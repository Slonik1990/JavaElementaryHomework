package Task6;

public class Main {
    public static void main(String[] args) {
        
        Collection nano = new Collection(3);
        Collection small = new Collection(5);
        Collection medium = new Collection(10);
        Collection big = new Collection(20);


        small.add(1);
        small.add(2);
        small.add(3);
        small.add(4);

        nano.add(5);
        nano.add(9);

        big.add(1);
        testPrint(nano, small, medium, big);
        big.hardAddingToEnd(small);
        big.hardAddingToEnd(nano);

//        small.hardAddingToEnd(nano);
//        small.hardAddingToEnd(nano);
//        small.bubbleSort();
//        small.downSort();
//
//        small.addAllToStart(nano);
//        small.addAllToStart(nano);
//        big.addAllToStart(medium);
//        big.hardAddingToEnd(medium);
//        medium.addAllToStart(small);
//        medium.addAllToStart(small);






        testPrint(nano, small, medium, big);
      

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    public static void testPrint(Collection a, Collection b, Collection c, Collection d ){
        System.out.println();
        System.out.println("*******CONTROL*******");
        a.print();
        b.print();
        c.print();
        d.print();
    }
    
    
    

}
