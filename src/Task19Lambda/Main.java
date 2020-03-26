package Task19Lambda;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        StudGroup<Stud> students = new StudGroup<>();

        students.add(new Stud("Andrey", "Petrov", 9.3));
        students.add(new Stud("Victor", "Sidorov", 7));
        students.add(new SuperStud("Anna", "Zayceva", 8.4));
        students.add(new Stud("Zahar", "Berkut", 5.9));
        students.add(new SuperStud("Nikolay", "Boyko", 4.3));

        Comparator<Stud> byLastName = Comparator.comparing(Stud::getLastName);
        Comparator<Stud> byName = Comparator.comparing(Stud::getFirstName);
        Comparator<Stud> byMarks = Comparator.comparing(Stud::getAverageMark);


        students.saveToTxt();
        students.sortAndSave(byMarks);
        students.sortAndSave(byLastName);
        System.out.println(args.length);




    }
}
