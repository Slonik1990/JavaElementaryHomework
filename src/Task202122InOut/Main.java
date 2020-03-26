package Task202122InOut;


public class Main {
    public static void main(String[] args) {
        StudentGroup<Student> students = new StudentGroup<>();

        students.add(new Student("Andrey", "Petrov"));
        students.add(new Student("Victor", "Sidorov"));
        students.add(new Student("Irina", "Zayceva"));
        students.add(new Student("Zahar", "Berkut"));
        students.add(new Student("Nikolay", "Boyko"));
        students.saveToTxt();
        System.out.println(students);





    }
}
