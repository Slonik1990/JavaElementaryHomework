package Task7;

public class GuideDog extends Dog {

    private boolean isTrained;

    public GuideDog(String name, boolean trained){
        super(name);
        this.isTrained = trained;
    }

    @Override
    public  void say(){
        super.say();
        if(this.isTrained) {
            System.out.print("I can take you home. ");
        }
    }

    public boolean isTrained() {
        return isTrained;
    }

    public void setTrained(boolean trained) {
        isTrained = trained;
    }

    //если собака не дрессированная, ей становится стыдно и она идет обучаться у лучших мастеров и становится профи
    public void goHome(){
        if(this.isTrained){
            this.say();
            System.out.print("Let's go!!!");
        } else {
            System.out.print("\nSorry I am not trained dog, but next time I will be ready!!!");
            this.isTrained = true;
        }

    }



}
