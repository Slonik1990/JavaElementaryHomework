package Task2021InOut;

public class Student implements Comparable<Student> {
    private Integer id;
    private String lastName;
    private String firstName;
    private int[] marks = new int[10];
    private static int counter =0;

    public Student(String lastName, String firstName) {
        this.id = ++counter;
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
    public double getAverageMark() {
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
        result = result.concat("       " + String.valueOf(getAverageMark()));
        return result;
    }


    //преобразование студента в CSV формат
    public String asCSVString(){
        String s = getLastName() + "," + getFirstName() + ",";
        for (int i = 0; i < marks.length; i++) {
            s = s.concat(String.valueOf(marks[i]) + ",");
        }
        s = s.concat(String.valueOf(getAverageMark()) + " \n");
        return s;
    }

    //натуральная сортировка будет выполняться по id, т.к. только это поле всегда уникально
    @Override
    public int compareTo(Student s) {
        return this.id.compareTo(s.id);
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }
}
