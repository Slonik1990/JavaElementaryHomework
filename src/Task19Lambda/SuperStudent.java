package Task19Lambda;


//этот класс существует только для того, чтобы попрактиковаться с дженериками


public class SuperStudent extends Student{
    private boolean goodStudent;
    public SuperStudent(String lastName, String firstName, double averageMark) {
        super(lastName, firstName, averageMark);
        this.goodStudent = true;
    }
}
