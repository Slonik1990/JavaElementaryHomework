package Task2021InOut;

import Task2021InOut.Student;
import Task2021InOut.StudentGroup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//в основе тестирования лежит сравнение оригинальной группы студентов и группы воссозданной из ее CSV представления

public class CSVTest {
    StudentGroup<Student> students = new StudentGroup<>();


    @Before
    public void groupCreate() {

        students.add(new Student("Andrey", "Petrov"));
        students.add(new Student("Victor", "Sidorov"));
        students.add(new Student("Irina", "Zayceva"));
        students.add(new Student("Zahar", "Berkut"));
        students.add(new Student("Nikolay", "Boyko"));

        students.feelMarks();
        students.groupAsCSV("src\\Task2021InOut", "group_as_csv.csv");
    }


    //сравнение количества студентов оригинальной группы и группы дессериализованной из формата CSV
    @Test
    public void sizeTest() {
        StudentGroup<Student> fromCSV = students.restoreFromCSV("src\\Task2021InOut\\group_as_csv.csv");
        Assert.assertEquals(students.getSize(), fromCSV.getSize());
    }

    //сравнение строкового представления групп
    @Test
    public void toStringTest(){
        StudentGroup<Student> fromCSV = students.restoreFromCSV("src\\Task2021InOut\\group_as_csv.csv");
        String x = students.toString();
        String y = fromCSV.toString();
        Assert.assertEquals(x,y);
    }


    @Test(expected = RuntimeException.class)
    public void fileNotFoundTest(){
        StudentGroup<Student> fromCSV = students.restoreFromCSV("src\\Task\\group_as_csv.csv");
    }


}
