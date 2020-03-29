package Task20InOut;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        StudentGroup<Student> students = new StudentGroup<>();
        students.add(new Student("Petrov", "Andrey" ));
        students.add(new Student("Sidorov", "Victor" ));
        students.add(new Student("Zayceva", "Irina"));
        students.add(new Student("Berkut", "Zahar"));
        students.add(new Student("Boyko", "Nykolay"));
        students.feelMarks();


        //сохранение в различные форматы
        students.saveAsTxt("src\\Task20InOut", "group_as_txt.txt");
        students.saveAsCSV("src\\Task20InOut", "group_as_csv.csv");
        students.saveAsJSON("src\\Task20InOut", "group_as_json.json");
        students.saveAsSerialized("src\\Task20InOut", "group_as_serialized.stg");


        //восстановление
        StudentGroup<Student> fromCSV = StudentGroup.restoreFromCSV("src\\Task20InOut\\group_as_csv.csv");
        StudentGroup<Student> fromJSON = StudentGroup.restoreFromJSON("src\\Task20InOut\\group_as_json.json");
        StudentGroup<Student> fromSER = StudentGroup.restoreSerialized("src\\Task20InOut\\group_as_serialized.stg");

        //сравнение оригинала с восстановленными группами
        System.out.println(students.equals(fromCSV));//true
        System.out.println(students.equals(fromJSON));//true
        System.out.println(students.equals(fromSER));//true

















    }
}
