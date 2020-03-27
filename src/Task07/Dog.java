package Task07;

public class Dog extends Pet{


    public Dog (String name){
        super(name);

    }


    @Override
    public  void say(){
        super.say();
        System.out.print("Woof!!!");
    }


}
