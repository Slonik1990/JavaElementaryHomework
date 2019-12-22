package Task4;
/*
Данная программа представлен как некий органайзер преподавателя, ведущего только одну группу. Т.к. группа может быть
        только одна, массивы с данными реализованы как глобальные и указываются в теле методов, что позволило сократить
        число принимаемых методами параметров.
Выбрал подход при котором студенты получают отметки за домашнее задание привязанное к уроку, что и хранит в себе массив
        Marks,благодаря данному подходу 0 обретает некий смысл и говорит о том, что ДЗ не выполнено.
Присутствие на уроках не несет в себе оценочного критерия, чему вполне удовлетворяет массив Visits, реализованный
        как массив boolean переменных.
        **/
class Students {

    static String [] students = new String[5];
    static int [][] marks = new int[5][5];
    static boolean[][] visits = new boolean[5][5];

    public static void main(String[] args) {
        addStudent("Солоник");
        addStudent("Иванов");
        addStudent("Петров");
        addStudent("Сидоров");
        addStudent("Васильев");
        addStudent("Сулецкий");
        setMark("Солоник", 1, 8);
        setMark("Солоник", 2, 9);
        setMark("Солоник", 3, 10);
        setMark("Солоник", 4, 7);
        setMark("Солоник", 5, 10);
        setVisit("Солоник", 1);
        setVisit("Солоник", 2);
        setVisit("Солоник", 3);
        setVisit("Солоник", 4);
        studentInfo("Солоник");
        groupInfo();
        goodBye("Иванов");
        groupInfo();
        addStudent("Сулецкий");
        groupInfo();

    }
    //метод записывает студента в массив фамилий на первое попавшееся свободное место
    //если все ячейки массива уже заняты - выводит сообщение
    public static void addStudent(String name){
        int record = 0;//индикатор записи студента
        for (int i = 0; i < students.length; i++)
            if (students[i] == null) {
                students[i] = name;
                record++;//индикатор записи студента
                System.out.println("Студент " + name + " добавлен в учебную группу");
                break; }
        if(record == 0){
            System.out.println("Свободных мест нет. Студент "+ name+" не записан");
        }
    }

    //вспомогательный метод определяющий индекс студента по фамилии
    public static int studentIndex(String name){
        int studIndex = -1;
        for (int i = 0; i < students.length; i++) {
            if(students[i]!=null&&students[i].equals(name)){
                studIndex = i;
                break; }
        }
        return studIndex;
    }

    //принимает фамилию, номер урока в общечеловеческом понимании и оценку, которую необходимо установить
    public static void setMark(String name, int lesson, int mark){
        if(studentIndex(name)==-1){
            System.out.println("Нет такого студента");
        } else if(lesson > marks[studentIndex(name)].length){
            System.out.println("Такое занятие не предусмотренно программой");
        }else {
            marks[studentIndex(name)][lesson - 1] = mark;
            System.out.println("Оценка установлена");
        }
    }
    //принимает фамилию, номер урока в общечеловеческом понимании и устанавливает отметку о посещении
    public static void setVisit(String name, int lesson){
        if(studentIndex(name)==-1){
            System.out.println("Нет такого студента");
        } else if(lesson > visits[studentIndex(name)].length){
            System.out.println("Такое занятие не предусмотренно программой");
        }else {
            visits[studentIndex(name)][lesson - 1] = true;
            System.out.println("Отметка о посещении установлена");
        }
    }

    public static void findStudent(String name){
        if(studentIndex(name)>=0){
            System.out.println("Студент с такой фамилией есть в данной группе");
        }else {
            System.out.println("Нет такого студента, поищите в другой группе");
        }
    }

    //если такой студент есть в группе, по его индексу очищаются данные о нем со всех массивов
    public static void goodBye(String name){
        int index = studentIndex(name);
        if(index>=0){
            students[studentIndex(name)] = null;//удаление фамилии
            for (int mark: marks[index]) {
                mark = 0;//удаление оценок
            }
            for (boolean visit: visits[index]) {
                visit = false;//удаление посещений
            }
            System.out.println("Студент уничтожен");
        }else {
            System.out.println("Такого студента и небыло");
        }
    }

    // полное очищение информации о группе
    public static void clearGroup(){
        for (int i = 0; i < students.length; i++) {
            students[i] = null;
        }
        for (int i = 0; i <marks.length ; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                marks[i][j]=0;
            }
        }
        for (int i = 0; i <visits.length ; i++) {
            for (int j = 0; j < visits[i].length; j++) {
                visits[i][j]=false;
            }
        }
        System.out.println("Информация о группее полностью очищена");
    }


    //счетчик посещений
    public static int helloStudent(String name){
        int x = 0;
        for (int i = 0; i < visits[studentIndex(name)].length; i++) {
            if (visits[studentIndex(name)][i]){
                x++;
            }
        }
        return x;
    }

    //счетчик среднего балла за выполненные задания
    public static double helloHomework(String name){
        double summ=0;
        int x = 0;
        double medium = 0.0;
        for (int i = 0; i < marks[studentIndex(name)].length; i++) {
            if (marks[studentIndex(name)][i]>0){
                summ = summ + marks[studentIndex(name)][i];
                x++;
            }
        }if(x==0){
            medium = 0.0;
        }else{
            medium = summ/x;
        }
        return medium;
    }



    //выводит досье на студента
    public static void studentInfo (String name){
        int index = studentIndex(name);
        if (index == -1){
            System.out.println("Нет такого студента");
        } else {
            System.out.println();
            System.out.println("Студент: " + name);
            System.out.println("Посетил занятий: " + helloStudent(name));
            System.out.println("За домашнюю работу получил следующие баллы");
            for (int i = 0; i < marks[studentIndex(name)].length; i++) {
                System.out.println("Задание " +(i+1)+":  " + marks[studentIndex(name)][i]);
            }
            System.out.println("Средний бал за выполненные задания: " + helloHomework(name));
        }
    }

    public static void groupInfo(){
        System.out.println();
        for (int i = 0; i < students.length; i++) {
            if(students[i]==null){
                continue;
            }
            System.out.print(students[i] + ": ");
            for (int j = 0; j < marks[i].length; j++) {
                System.out.print(marks[i][j] + " ");
            }
                System.out.println(";");
        }
    }

}