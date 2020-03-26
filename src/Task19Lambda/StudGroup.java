package Task19Lambda;

import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

public class StudGroup<T extends Stud>{
    private TreeSet<T> group;

    public StudGroup() {
        this.group = new TreeSet<>();
    }

    public void add (T stud){
        group.add(stud);
    }


    public String toString(){
        int n = 0;
        String text = "\n                STUDENTS                ";
        text = text.concat("\n Last Name        Name       Average Mark");
        text = text.concat("\n__________________________________________");
        for (Stud s: group) {
            text = text.concat("\n" + ++n + ")");
            text = text.concat(s.toString());

        }
        return text;
    }

    public void sort(Comparator<T> comparator){
        TreeSet<T> bufer = new TreeSet<>(comparator);
        bufer.addAll(this.group);
        this.group = bufer;

    }

    public void sortAndSave(Comparator<T> comparator){
        TreeSet<T> bufer = new TreeSet<>(comparator);
        bufer.addAll(this.group);
        this.group = bufer;
        saveToTxt();

    }

    public void saveToTxt() {
        File file = new File("src\\Task20InOut", "students.txt");
        String s = toString();
        try(FileOutputStream outputStream = new FileOutputStream(file, false)) {
            outputStream.write(s.getBytes());

        }catch (IOException e){
            e.printStackTrace();
        }



    }


}
