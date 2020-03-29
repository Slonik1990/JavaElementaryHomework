package Task20InOut;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class StudentGroup<T extends Student> implements Serializable {
    private LinkedList<T> group;


    public StudentGroup() {
        this.group = new LinkedList<>();
    }

    public void add(T student) {
        group.add(student);
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
    public void saveAsCSV(String parent, String child) {
        File file = new File(parent, child);
        try (FileWriter fw = new FileWriter(file, false)) {
            for (Student s : group) {
                fw.write(s.asCSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод создает пустую группу, наполняет ее студентами десериализованными из CSV формата и возвращает
    public static StudentGroup restoreFromCSV(String fileSource) {

        StudentGroup<Student> toReturn = new StudentGroup<>();

        //внешний сканер продвигается по строкам
        try (Scanner lines = new Scanner(new FileReader(fileSource));) {
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

    public void saveAsJSON(String parent, String child) {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(parent, child);
        try (FileWriter fw = new FileWriter(file)) {
            for (Student s : group) {
                fw.write(objectMapper.writeValueAsString(s) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentGroup<Student> restoreFromJSON(String fileSource) {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentGroup<Student> toReturn = new StudentGroup();
        try (Scanner scanner = new Scanner(new FileInputStream(fileSource))) {
            while (scanner.hasNextLine()) {
                String json = scanner.nextLine();
                Student s = objectMapper.readValue(json, Student.class);
                toReturn.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }


    public void saveAsSerialized(String parent, String child) {
        File file = new File(parent, child);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static StudentGroup restoreSerialized(String fileSource) {
        File file = new File(fileSource);
        StudentGroup toReturn = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            toReturn = (StudentGroup) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup<?> that = (StudentGroup<?>) o;
        return Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group);
    }

    public int size(){
        return group.size();
    }
}

