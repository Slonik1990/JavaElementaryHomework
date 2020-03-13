package Task19Lambda;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {

        StudentGroup<Student> students = new StudentGroup<>();

        students.add(new Student("Andrey", "Petrov", 9.3));
        students.add(new Student("Victor", "Sidorov", 7));
        students.add(new SuperStudent("Anna", "Zayceva", 8.4));
        students.add(new Student("Zahar", "Berkut", 5.9));
        students.add(new SuperStudent("Nikolay", "Boyko", 4.3));

        Comparator<Student> byLastName = Comparator.comparing(Student::getLastName);
        Comparator<Student> byName = Comparator.comparing(Student::getFirstName);
        Comparator<Student> byMarks = Comparator.comparing(Student::getAverageMark);


        students.saveToTxt();
        students.sortAndSave(byMarks);
        students.sortAndSave(byLastName);



    }
}
