package Task19Lambda;

import java.util.Comparator;
import java.util.TreeSet;

public class StudentGroup <T extends Student>{
    private TreeSet<T> group;

    public StudentGroup() {
        this.group = new TreeSet<>();
    }

    public void add (T stud){
        group.add(stud);
    }

    public void print(){
        System.out.println(group);
    }
    
    //используется метод, который не изменяет множество, а печатает его отсортированную копию
    public void printSorted(Comparator<T> comparator){
        TreeSet<T> toPrint = new TreeSet<>(comparator);
        toPrint.addAll(this.group);
        System.out.println(toPrint);
    }
}
