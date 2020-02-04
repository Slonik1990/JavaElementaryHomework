package Child;

public class Main {
    public static void main(String[] args) {
        Child kid = new Child();
        HowToGreetable mom = new Mother();
        HowToGreetable dad = new Father();
        HowToGreetable granny = new GrandMother();

        kid.setVzrosliy(dad);
        System.out.println(kid.greet());
        kid.setVzrosliy(mom);
        System.out.println(kid.greet());
        kid.setVzrosliy(granny);
        System.out.println(kid.greet());
    }
}
