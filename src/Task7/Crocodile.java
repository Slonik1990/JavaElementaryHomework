package Task7;

public class Crocodile extends Wild {

    public Crocodile(){
        super.setPredator(true);
    }

    @Override
    public  void say(){
        super.say();
        System.out.print("Let's swim)))");
    }


    @Override
    public void food() {
        System.out.print("\n" + "I eat another animals");
    }
}