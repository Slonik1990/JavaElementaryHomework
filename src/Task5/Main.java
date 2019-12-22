package Task5;

public class Main {
    public static void main(String[] args) {
        Student Ivanov = new Student("Ivanov");//абитуриет Иванов, массив с оценками не инициализирован
        Student Petrov = new Student("Petrov", 10);
        StudentGroup java2019 = new StudentGroup("Drivers", 15);
        java2019.addStud(Ivanov);//Иванов зачислен в группу
        java2019.addStud(Petrov);
        java2019.setNewGroupCourse(20);//установлено количество занятий для группы
        Ivanov.setMark(3,10);//Иванов получает оценку, т.к. его массив оценок был инициализирован вместе с группой
        System.out.println(Ivanov.marks[2]);//вывод на экран 10
        java2019.findStudent("Petrov");
        //java2019.cleanGroup();//группа закончила обучение
        System.out.println(Ivanov.marks[2]);//зачетка с оценками никуда не делась, вывод на экран 10
        //Ivanov.cleanStudent();
        System.out.println(Ivanov.marks[2] + " " + Ivanov.visits[2]);

        java2019.printGroup();


    }


}
