package Task7;

public class Wolf extends Wild {
    public Wolf(){
        super.setPredator(true);
    }

    @Override
    public  void say(){
        super.say();
        System.out.print("Aaauuuuuuuuuu");
    }

}
