package Task7;

public class Lion extends Wild {

    public Lion(){
        super.setPredator(true);
    }

    @Override
    public  void say(){
        super.say();
        System.out.print("RRRRRRRRRR");
    }


}
