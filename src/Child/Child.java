package Child;

public class Child {

    private HowToGreetable vzrosliy;

    public void setVzrosliy(HowToGreetable vzrosliy) {
        this.vzrosliy = vzrosliy;
    }

    public String greet(){
        return vzrosliy.howToGreet();
    }
}
