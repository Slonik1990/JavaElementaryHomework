package Task19Lambda;

public class Stud implements Comparable<Stud>{
    private Integer id;//поле для натурального сравнения
    private String lastName;
    private String firstName;
    private double averageMark;
    private static  int idCounter = 0;

    public Stud(String firstName, String lastName, double averageMark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageMark = averageMark;
        this.id = ++idCounter;

    }

    @Override
    public String toString() {
        String result = lastName;
        while (result.length()<15) result = result.concat(" ");
        result = result.concat(firstName);
        while (result.length()<30) result = result.concat(" ");
        result = result.concat(String.valueOf(averageMark));
        return result;
    }

    //натуральная сортировка будет выполняться по id, т.к. только это поле всегда уникально
    @Override
    public int compareTo(Stud s) {
        return this.id.compareTo(s.id);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getAverageMark() {
        return averageMark;
    }

}

class SuperStud extends Stud {
    private boolean goodStudent;
    public SuperStud(String firstName, String lastName, double averageMark) {
        super(firstName, lastName, averageMark);
        this.goodStudent = true;
    }
}
