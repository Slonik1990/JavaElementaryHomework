package Task7;

public abstract class Wild extends Animal{

    private boolean isPredator;
    public Wild(){
    }

    @Override
    public  void say(){
        super.say();
        System.out.print("I am a wild animal ");
        if(isPredator){
            System.out.print( "and I am angry. ");
        }
    }


    public boolean isPredator() {
        return isPredator;
    }

    public void setPredator(boolean predator) {
        isPredator = predator;
    }
}
