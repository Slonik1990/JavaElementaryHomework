package Task20InOut;

import Task20InOut.Student;
import Task20InOut.StudentGroup;

import java.io.*;

public class Comparison {
    public static void main(String[] args) throws IOException {


        StudentGroup<Student> students10000 = creatingGroup(10000);
        StudentGroup<Student> students1000000 = creatingGroup(1000000);

        csvTest(students10000);
        csvTest(students1000000);

        serTest(students10000);
        serTest(students1000000);

        jsonTest(students10000);
        jsonTest(students1000000);



    }

    public static StudentGroup<Student> creatingGroup(int numberOfStudents){
        StudentGroup<Student> group = new StudentGroup<>();
        while (group.size()<numberOfStudents) {
            group.add(new Student("Petrov", "Andrey" ));
            group.add(new Student("Sidorov", "Victor" ));
            group.add(new Student("Zayceva", "Irina"));
            group.add(new Student("Berkut", "Zahar"));
            group.add(new Student("Boyko", "Nykolay"));
            group.add(new Student("Ivanov", "Ivan" ));
            group.add(new Student("Krasko", "Dasha" ));
            group.add(new Student("Shevchenko", "Taras"));
            group.add(new Student("Sidorov", "Sidor"));
            group.add(new Student("Franko", "Ivan"));
        }
        group.feelMarks();
        return group;
    }

    public static void jsonTest(StudentGroup<Student> students){
        System.out.println("JSON " + students.size() + " студентов:");
        long start = System.currentTimeMillis();
        students.saveAsJSON("src\\Task20InOut", "test_json.json");
        long outTime = System.currentTimeMillis();
        StudentGroup<Student> fromJSON = StudentGroup.restoreFromJSON("src\\Task20InOut\\test_json.json");
        long inTime = System.currentTimeMillis();
        System.out.println("Время сериализации: " + (double)(outTime-start)/1000 + " сек");
        System.out.println("Время десериализации: " + (double)(inTime-outTime)/1000 + " сек");
        try(FileInputStream f = new FileInputStream("src\\Task20InOut\\test_json.json")){
            System.out.println("Объем файла:" + f.available()/1024 + " КБ");
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void csvTest(StudentGroup<Student> students){
        System.out.println("CSV " + students.size() + " студентов:");
        long start = System.currentTimeMillis();
        students.saveAsJSON("src\\Task20InOut", "test_csv.csv");
        long outTime = System.currentTimeMillis();
        StudentGroup<Student> fromJSON = StudentGroup.restoreFromJSON("src\\Task20InOut\\test_csv.csv");
        long inTime = System.currentTimeMillis();
        System.out.println("Время сериализации: " + (double)(outTime-start)/1000 + " сек");
        System.out.println("Время десериализации: " + (double)(inTime-outTime)/1000 + " сек");
        try(FileInputStream f = new FileInputStream("src\\Task20InOut\\test_csv.csv")){
            System.out.println("Объем файла:" + f.available()/1024 + " КБ");
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void serTest(StudentGroup<Student> students){
        System.out.println("SERIALIZATION " + students.size() + " студентов:");
        long start = System.currentTimeMillis();
        students.saveAsJSON("src\\Task20InOut", "test_serialization.stg");
        long outTime = System.currentTimeMillis();
        StudentGroup<Student> fromJSON = StudentGroup.restoreFromJSON("src\\Task20InOut\\test_serialization.stg");
        long inTime = System.currentTimeMillis();
        System.out.println("Время сериализации: " + (double)(outTime-start)/1000 + " сек");
        System.out.println("Время десериализации: " + (double)(inTime-outTime)/1000 + " сек");
        try(FileInputStream f = new FileInputStream("src\\Task20InOut\\test_serialization.stg")){
            System.out.println("Объем файла:" + f.available()/1024 + " КБ");
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();
    }


}
