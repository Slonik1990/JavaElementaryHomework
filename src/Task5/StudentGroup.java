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
        for (int j = 0; j < group.length; j++)
            if(group[j] != null&&group[j].name.equals(name.name)){
                System.out.println("Студент с такой фамилией уже есть в группе");
                return;
            }
        for (int i = 0; i < group.length; i++)

            if (group[i] == null) {
                group[i] = name;
                record++;//индикатор записи студента
                System.out.println("Студент "+ name.name + " добавлен в учебную группу");
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

    //количество студентов обучающихся в группе
    public int inGroup(){
        int x = 0;
        for (int i = 0; i < group.length; i++) {
            if(group[i]!=null){
                x++;
            }
        }
        return x;
    }
//рейтинг группы
    public void groupRating(){
        System.out.println("\nРейтинг группы " + groupName);
        Student[] rating = new Student[inGroup()];//создается массив с длинной равной количеству учеников зачисленных в группу
        //все существующие ученики записываются в массив
        int x = 0;//счетчик записей в rating
        for (int i = 0; i < group.length; i++) {
            if (group[i] != null){
                rating[x]=group[i];
                x++;} }

        //сортировка студентов методом перебора по их суммарной оценке на убывание
        for (int i = 0; i < rating.length; i++) {
            for (int j = 0; j < rating.length-1; j++) {
                if(rating[j+1].totalMark()>rating[j].totalMark()){
                    Student buffer = rating[j];
                    rating[j]= rating[j+1];
                    rating[j+1]=buffer;
                }
            }
        }
        //вывод на экран отсортированного массива студентов
        for (int i = 0; i < rating.length; i++) {
            System.out.println(rating[i].name + ": " + rating[i].totalMark());
        }

    }


    public void groupInfo() {
        System.out.println();
        System.out.println(groupName);
        System.out.println("В группе обучается студентов: " + inGroup());
        for (int i = 0; i < group.length; i++) {
            if (group[i] == null) {
                continue;
            }
            group[i].studentMarks();
        }
    }
}
