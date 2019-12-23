package Task5;

public class Main {
    public static void main(String[] args) {
        Student Ivanov = new Student("Ivanov");//абитуриет Иванов, массив с оценками не инициализирован
        Student Petrov = new Student("Petrov", 10);
        Student Sidorov = new Student("Sidortov", 10);
        StudentGroup java2019 = new StudentGroup("Java Students 2019", 10);
        java2019.addStud(Petrov);
        java2019.addStud(Sidorov);
        java2019.addStud(Ivanov);//Иванов зачислен в группу
        java2019.setNewGroupCourse(5);//установлено количество занятий для группы

        Ivanov.setMark(3,4);//Иванов получает оценку, т.к. его массив оценок был инициализирован вместе с группой
        Ivanov.setMark(1,6);
        Ivanov.setMark(2,8);
        Ivanov.setMark(4,10);
        Ivanov.setMark(5,7);
        Ivanov.setMark(3,10);//исправил оценку
        Petrov.setMark(1,9);
        Petrov.setMark(3,10);
        Sidorov.setMark(1,5);

        Ivanov.setVisit(5);
        Ivanov.setVisit(1);
        Ivanov.setVisit(3);
        Petrov.setVisit(1);
        Petrov.setVisit(4);
        Sidorov.setVisit(1);

        Petrov.studentInfo();
        Ivanov.studentInfo();
        Sidorov.studentInfo();

        java2019.groupInfo();
        java2019.groupRating();







    }


}
