package Task14JustVocabluary;

public class Main {
    public static void main(String[] args) {
        Vocabluary slovar = new Vocabluary();
        slovar.add("House", "Дом");
        slovar.add("Tree", "Дерево");
        slovar.add("Chair", "Стол");


        slovar.translate("House");
        slovar.translate("Tree");
        slovar.translate("Chair");
        slovar.translate("Horse");

        slovar.showAll();
        slovar.remove("Tree");
        slovar.remove("Horse");
        slovar.showAll();
    }

}
