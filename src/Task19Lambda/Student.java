package Task19Lambda;

public class Student implements Comparable<Student>{
    private Integer id;
    private String lastName;
    private String firstName;
    private double averageMark;
    private static  int idCounter = 0;

    public Student(String lastName, String firstName, double averageMark) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.averageMark = averageMark;
        this.id = ++idCounter;

    }

    @Override
    public String toString() {
        return "\nStudent: " + lastName + " " + firstName + ". Average mark: " + averageMark;
    }

    //натуральная сортировка будет выполняться по id, т.к. только это поле всегда уникально
    @Override
    public int compareTo(Student s) {
        return this.id.compareTo(s.id);
    }

    public Integer getId() {
        return id;
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

    public static int getIdCounter() {
        return idCounter;
    }
}
