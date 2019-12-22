package Task5;


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
            visits[lesson - 1] = true;
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

}
