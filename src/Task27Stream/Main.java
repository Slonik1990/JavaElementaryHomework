package Task27Stream;



import Task19Lambda.Stud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> list = creatingGroup();

        List<String> result =
                        list.stream().
                        sorted(Comparator.comparing(Student::getLastname)).
                        filter(student -> vowelsCounter(student)>=3).
                        map(Student::getLastname).
                        map(String::toUpperCase).
                        collect(Collectors.toList());
        System.out.println(result);//[BOYKO, SIDOROV, ZAYCEVA]


    }


    public static int vowelsCounter(Student student){
        int counter = 0;
        char[]name = student.getFirstname().toCharArray();
        List<Character>vowels = Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' );
        for (int i = 0; i < name.length; i++) {
            if(vowels.contains(name[i])){
                counter++;
            }
        }
        return counter;
    }

    public static ArrayList<Student> creatingGroup(){
        ArrayList<Student> students = new ArrayList<>();
            students.add(new Student("Petrov", "Andrey" ));
            students.add(new Student("Sidorova", "Inna" ));
            students.add(new Student("Zayceva", "Irina"));
            students.add(new Student("Berkut", "Zahar"));
            students.add(new Student("Boyko", "Nikolai"));
            students.add(new Student("Ivanov", "Ivan" ));
            students.add(new Student("Krasko", "Dasha" ));
            students.add(new Student("Shevchenko", "Taras"));
            students.add(new Student("Sidorov", "Aleksander"));
            students.add(new Student("Franko", "Ivan"));
        return students;
    }











}
