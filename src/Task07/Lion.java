package Task07;

public class Lion extends Wild {

    public Lion(){
        super.setPredator(true);
    }

    @Override
    public  void say(){
        super.say();
        System.out.print("RRRRRRRRRR");
    }

    @Override
    public void food() {
        System.out.print("\n" + "I eat another animals");
    }

}
