package Task202122InOut;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeSet;

public class StudentGroup <T extends Student>{
    private TreeSet<T> group;

    public StudentGroup() {
        this.group = new TreeSet<>();
    }

    public void add (T student){
        group.add(student);
    }

    public String toString(){
        int n = 0;
        String text = "\n         STUDENTS                                LESSONS & MARKS     ";
        text = text.concat("\n Last Name        Name          1   2   3   4   5   6   7   8   9   10     Average Mark");
        text = text.concat("\n_______________________________________________________________________________________ ");
        for (Student s: group) {
            text = text.concat("\n" + ++n + ")");
            text = text.concat(s.toString());

        }
        return text;
    }

    public void saveToTxt() {
        File toTXT = new File("src\\Task202122InOut", "group_as_txt.txt");
        String s = toString();
        try(FileOutputStream outputStream = new FileOutputStream(toTXT, false)) {
            outputStream.write(s.getBytes());

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}

