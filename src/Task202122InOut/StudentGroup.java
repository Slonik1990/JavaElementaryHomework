package Task202122InOut;


import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

public class StudentGroup<T extends Student> {
    private TreeSet<T> group;

    public StudentGroup() {
        this.group = new TreeSet<>();
    }

    public void add(T student) {
        group.add(student);
    }

    public int getSize(){
        return group.size();
    }

    //заполняет оценки всех студентов группы рандомными значениями
    public void feelMarks() {
        for (Student s : group) {
            s.feelMarks();
        }
    }

    //метод возвращает строковое представление группы
    public String toString() {
        int n = 0;
        String text = "\n         STUDENTS                                LESSONS & MARKS     ";
        text = text.concat("\n Last Name        Name          1   2   3   4   5   6   7   8   9   10     Average Mark");
        text = text.concat("\n_______________________________________________________________________________________ ");
        for (Student s : group) {
            text = text.concat("\n" + ++n + ")");
            text = text.concat(s.toString());

        }
        return text;
    }

    //сохраняет строковое представление в txt файл
    public void saveAsTxt(String parent, String child) {
        File toTXT = new File(parent, child);
        String s = toString();
        try (FileOutputStream outputStream = new FileOutputStream(toTXT, false)) {
            outputStream.write(s.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //методы работающие с CSV

    //сохраняет всю группу в CSV формате применяя в цикле аналогичный метод для каждого отдельного студента
    public void groupAsCSV(String parent, String child) {
        File file = new File(parent, child);
        try (FileWriter fw = new FileWriter(file)) {
            for (Student s : group) {
                fw.write(s.asCSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод создает пустую группу, наполняет ее студентами десериализованными из CSV формата и возвращает
    public StudentGroup restoreFromCSV(String fileSource) {

        StudentGroup<Student> toReturn = new StudentGroup<>();

        //внешний сканер продвигается по строкам
        try(Scanner lines = new Scanner(new FileReader(fileSource));) {
            while (lines.hasNextLine()) {
                String l = lines.nextLine();

                //вложенный сканер разбирает каждую строку
                Scanner inLine = new Scanner(l);
                inLine.useDelimiter(", *");

                //преобразование элементов CSV строки в объекты, которые станут полями для студента

                Student model = new Student(inLine.next(), inLine.next());//студент десериализованный из CSV

                int[] marks = new int[10];
                int i = 0;
                while (inLine.hasNextInt()) {
                    marks[i++] = inLine.nextInt();
                }

                model.setMarks(marks);
                toReturn.add(model);

                inLine.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не обнаружен");
        }
        return toReturn;
    }
}

