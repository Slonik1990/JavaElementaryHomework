package Task7;

public abstract class Animal {

    //счетчик созданных животных, который будет по совместительству источником уникального id для каждого животного
    private static int count;

    final private  int ID;

    private int age;
    private int weight;
    private String color;

    //
    public Animal(){
        this.ID = ++count;
    }


    //не является абстрактным и может вообще не переопределяться, а нести свой функционал
    public  void say(){
        System.out.print("\nHello!!! ");
    }


    //создан сугубо для практики работы с абстрактными методами, является обязательным для переопределения
    //в каждой из ветвей наследования идущих от Animal протестирован разный подход переопределения
    //у домашних животных реализован в классе питомец и наследуется его классами наследниками
    //у диких минуя абстрактный класс дикое реализован в конкретных классах
    public abstract void food();


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getId() {
        return ID;
    }

    public static int getCount() {
        return count;
    }

    //
    public void setInfo(int age, String color, int weight){
        this.age = age;
        this.color = color;
        this.weight = weight;
    }

}
