package Task7;

public abstract class Animal {
    private static int count;

    final private  int id;
    private int age;
    private int weight;
    private String color;

    public Animal(){
        count++;
        this.id = count;
    }


    //абстрактный метод say() ничего не реализует, но обязывает своих наслендников к переопределению
    public  void say(){
        System.out.print("\nHello!!! ");
    }


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
        return id;
    }

    public static int getCount() {
        return count;
    }
}
