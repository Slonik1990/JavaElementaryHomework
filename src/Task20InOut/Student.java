package Task20InOut;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Student implements Serializable {
    private String lastName;
    private String firstName;
    private int[] marks = new int[10];

    private Student() {
    }

    public Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }


    //метод заполняет массив оценок студента рандомными значениями в диапазоне [1;10]
    public void feelMarks() {
        for (int i = 0; i < marks.length; i++) {
            marks[i] = (int) (Math.random() * 10 + 1);
        }
    }

    //вычисляет среднюю оценку студента
    public double avMark() {
        if(marks==null){
            return 0.0;
        }
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return (double) sum / marks.length;
    }

    //возвращает строковое представление студента
    @Override
    public String toString() {
        String result = lastName;
        while (result.length() < 15) result = result.concat(" ");
        result = result.concat(firstName);
        while (result.length() < 30) result = result.concat(" ");
        for (int i = 0; i < marks.length; i++) {
            if(marks[i] == 10){
            result = result.concat(String.valueOf(marks[i]) + "  ");
            }else {
                result = result.concat(String.valueOf(marks[i]) + "   ");
            }
        }
        result = result.concat("       " + String.valueOf(avMark()));
        return result;
    }


    //преобразование студента в CSV формат
    public String asCSVString(){
        String s = getLastName() + "," + getFirstName() + ",";
        for (int i = 0; i < marks.length; i++) {
            s = s.concat(String.valueOf(marks[i]) + ",");
        }
        s = s.concat(String.valueOf(avMark()) + " \n");
        return s;
    }

    public void saveAsJSON(String parent, String child) {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(parent, child);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(objectMapper.writeValueAsString(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Student restoreFromJSON(String fileSource) {
        ObjectMapper objectMapper = new ObjectMapper();
        Student toReturn = null;
        try(Scanner scanner = new Scanner(new FileInputStream(fileSource))) {
            String json = scanner.nextLine();
            toReturn = objectMapper.readValue(json, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }


    //геттеры
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public int[] getMarks() {
        return marks;
    }

    //сеттеры
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(lastName, student.lastName) &&
                Objects.equals(firstName, student.firstName) &&
                Arrays.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(lastName, firstName);
        result = 31 * result + Arrays.hashCode(marks);
        return result;
    }
}
