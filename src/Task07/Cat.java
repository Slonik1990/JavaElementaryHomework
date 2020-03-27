package Task07;

public class Cat extends Pet {
    public Cat (String name){
        super(name);
    }


    @Override
    public  void say(){
        super.say();
        System.out.print("Meeeeoooooowwwwww!!!");
    }

}
