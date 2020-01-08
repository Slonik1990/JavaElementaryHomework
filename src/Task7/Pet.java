package Task7;

public abstract class Pet extends Animal{

    private String name;
    private boolean isVaccinated;

    public Pet(String name){
        super();
        this.name=name;
    }

    @Override
    public  void say(){
        super.say();
        System.out.print("My name is " + name + ". ");
    }

    @Override
    public void food() {
        System.out.print("\n" + "I eat what the owner gives me");
    }


    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }
}
