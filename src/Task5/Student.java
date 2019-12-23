package Task5;
/*Выбрал подход при котором студенты получают отметки за домашнее задание привязанное к уроку, что и хранит в себе поле
        marks, реализованное как массив, благодаря данному подходу 0 обретает некий смысл и говорит о том,
        что ДЗ не выполнено.
        Присутствие на уроках не несет в себе оценочного критерия, чему вполне удовлетворяет поле visits, реализованный
        как массив boolean переменных.
        visits и marks не привязаны друг к другу, т.к например можно получить оценку за задание не присутствуя на уроке,
        или наоборот - присутствовать на уроке и ничего не выполнить
        но данные поля объединяет единый принимаемый конструкторами параметр, который символизирует размерность учебного
        курса
        **/

 public class Student {

    String name;
    int[] marks;
    boolean[]visits;
    private  int x =0;//индикатор объявления массивов с оценками и посещениями


    //минимальный конструктор, который запрашивает только имя студента
    public Student(String name) {
        this.name = name;
    }

    //принимает количество занятий в курсе и сразу на основе этого инициализирует массивы для оценок и посещений
    public Student(String name, int lessons) {
        this.name = name;
        this.marks = new int[lessons];
        this.visits = new boolean[lessons];
        this.x = 1;
    }

    //инициализирует массивы, принимая количество занятий, запланированных у студента, если изначально этого не было известно
     //например если студент занимается индивидуально и ему еще не сразу составили курс
    public void setCourse(int lessons){
        this.marks = new int[lessons];
        this.visits = new boolean[lessons];
        this.x = 1;
    }

    //устанавливает оценку за задание
    public void setMark(int lesson, int mark){
        if(x==0){
            System.out.println("Укажите запланированное количество занятий при помощи метода setCourse");
        }else if(lesson>marks.length) {
            System.out.println("Данное занятие не предусмотрено курсом");
        } else{
            marks[lesson - 1] = mark;
        }
    }

    //отмечает присутствие на занятии
    public void setVisit(int lesson){
        if(x==0){
            System.out.println("Укажите запланированное количество занятий при помощи метода setCourse");
        }else if(lesson>visits.length) {
            System.out.println("Данное занятие не предусмотрено курсом");
        } else{
            visits[lesson - 1] = true;
        }
    }

    //очищает информацию о песещениях и оценках студента
    public void cleanStudent(){
        for (int i = 0; i < marks.length; i++) {
            marks[i] = 0;
            visits[i] = false;
        }
    }
    //средняя оценка за выполненные задания
     public double mediumMark(){
         double medium = 0;
         if(taskComplete()==0){
             medium = 0;
         }else {
             medium = ((double)totalMark())/taskComplete();
         }
         return medium;
     }

     //общая оценка за все занятия, можно использовать для построения рейтинга группы
     public int totalMark(){
         int summ=0;
         for (int i = 0; i < marks.length; i++) {
                 summ = summ + marks[i];
             }
         return summ;
     }

     //количество выполненных заданий
     public int taskComplete() {
         int summ = 0;
         for (int i = 0; i < marks.length; i++) {
             if (marks[i] > 0) {
                 summ++;
             }
         }return summ;
     }

     //количество посещений
     public int totalVisits() {
         int summ = 0;
         for (int i = 0; i < visits.length; i++) {
             if (visits[i]) {
                 summ++;
             }
         }return summ;
     }

     //выводит на экран оценки студента, вспомогательный метод, используется в досье группы
     public void studentMarks() {
             System.out.print(name + ": ");
             for (int i = 0; i < marks.length; i++) {
                 System.out.print(" " + marks[i]);
             }
             System.out.println(";");
         }



     public  void studentInfo (){
             System.out.println();
             System.out.println("Студент: " + name);
             System.out.println("Посетил занятий: " + totalVisits());
             System.out.println("Выполнил заданий: " + taskComplete());
             if(taskComplete()>0){
                 System.out.println("За домашнюю работу получил следующие баллы");
                 for (int i = 0; i < marks.length; i++) {
                     if(marks[i]!=0){
                         System.out.println("Задание " +(i+1)+":  " + marks[i]);
                     }
                 }
             }
             System.out.println("Средний балл за выполненные задания: " + mediumMark());
         System.out.println(" ");
         }

}
