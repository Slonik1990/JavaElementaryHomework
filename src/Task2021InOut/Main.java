package Task2021InOut;


public class Main {
    public static void main(String[] args) {
        StudentGroup<Student> students = new StudentGroup<>();

        students.add(new Student("Petrov", "Andrey" ));
        students.add(new Student("Sidorov", "Victor" ));
        students.add(new Student("Zayceva", "Irina"));
        students.add(new Student("Berkut", "Zahar"));
        students.add(new Student("Boyko", "Nykolay"));

        students.feelMarks();

        students.saveAsTxt("src\\Task2021InOut", "group_as_csv.txt");
        students.groupAsCSV("src\\Task2021InOut", "group_as_csv.csv");
        StudentGroup<Student> fromCSV = students.restoreFromCSV("src\\Task2021InOut\\group_as_csv.csv");
        System.out.println(students);
        System.out.println(fromCSV);








    }
}
