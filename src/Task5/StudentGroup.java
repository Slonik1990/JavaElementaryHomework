package Task5;


import Task5.Student;

public class StudentGroup {
    String groupName;
    Student[] group;


    public StudentGroup(String groupName, int students) {
        this.groupName = groupName;
        this.group = new Student[students];
    }

    public void addStud(Student name) {
        int record = 0;//индикатор записи студента
        for (int i = 0; i < group.length; i++)
            if (group[i] == null) {
                group[i] = name;
                record++;//индикатор записи студента
                System.out.println("Студент  добавлен в учебную группу");
                break;
            }
        if (record == 0) {
            System.out.println("Свободных мест нет. Студент  не записан");
        }
    }

    //определяет наличие студента по фамилии
    public void findStudent(String name) {
        int x = 0;
        for (int i = 0; i < this.group.length; i++) {
            if (group[i] != null && group[i].name.equals(name)) {
                System.out.println("Студент с такой фамилией есть в данной группе");
                x = 1;
                break;
            }
        }
        if (x == 0) {
            System.out.println("Нет такого студента, поищите в другой группе");
        }
    }


    //когда группа набрана и количество занятий определено, оно устанавливается для каждого ученика одинаковым
    //например начался новый учебный год и количество занятий изменилось, а оценки и посещения стираются
    public void setNewGroupCourse(int lessons) {
        for (int i = 0; i < this.group.length; i++) {
            if (group[i] != null) {
                group[i].setCourse(lessons);
            }
        }
    }

    //удаление студента из группы
    public void removeStudent(String name) {
        int x = 0;
        for (int i = 0; i < this.group.length; i++) {
            if (group[i] != null && group[i].name.equals(name)) {
                group[i] = null;
                System.out.println("Студент с такой фамилией удален из группы");
                x = 1;
                break;
            }
        }
        if (x == 0) {
            System.out.println("Его и так нет в группе");
        }
    }

    //очистка группы - группа распускается, но сами по себе объекты которые в ней содержались продолжают существовать
    //с теми же состояниями
    public void cleanGroup() {
        for (int i = 0; i < group.length; i++) {
            group[i] = null;
        }
    }

    public void printGroup() {
        System.out.println();
        System.out.println(groupName);
        for (int i = 0; i < group.length; i++) {
            if (group[i] == null) {
                continue;
            }
            System.out.print(group[i].name + ": ");
            for (int j = 0; j < group[i].marks.length; j++) {
                System.out.print(group[i].marks[j] + " ");
            }
            System.out.println(";");
        }
    }
}
