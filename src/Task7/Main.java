package Task7;

public class Main {
    public static void main(String[] args) {

        Dog muhtar = new Dog("Muhtar");
        GuideDog rex = new GuideDog("Rex", true);
        GuideDog chappi = new GuideDog("Chappi", false);
        Hamster mikki = new Hamster("Mikki");
        Fish dori = new Fish("Dori");
        Cat barsik = new Cat("Barsik");
        Lion simba = new Lion();
        Giraffe bill = new Giraffe();
        Wolf akela = new Wolf();
        Crocodile gena = new Crocodile();

        Animal[] zoo = {muhtar, rex, chappi, mikki, dori, barsik, simba, bill, akela, gena};


        for (int i = 0; i < zoo.length; i++) {
            zoo[i].say();
        }


        for (int i = 0; i < zoo.length; i++) {
            zoo[i].food();
        }

        System.out.println();
        for (int i = 0; i < zoo.length; i++) {
            System.out.println(zoo[i].getId());
        }



        chappi.goHome();//Sorry I am not trained dog, but next time I will be ready!!!
        chappi.goHome();//Hello!!! My name is Chappi. Woof!!!I can take you home. Let's go!!!

        System.out.println(Animal.getCount());//10
    }
}
