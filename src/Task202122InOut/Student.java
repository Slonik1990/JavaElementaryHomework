package Task202122InOut;

public class Student implements Comparable<Student> {
    private Integer id;
    private String lastName;
    private String firstName;
    private int[] marks = new int[10];
    private static int counter =0;

    public Student(String firstName, String lastName) {
        this.id = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        feelMarks();
    }


    private void feelMarks() {
        for (int i = 0; i < marks.length; i++) {
            marks[i] = (int) (Math.random() * 10 + 1);
        }
    }

    private double getAverageMark() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return (double) sum / marks.length;
    }

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

    //натуральная сортировка будет выполняться по id, т.к. только это поле всегда уникально
    @Override
    public int compareTo(Student s) {
        return this.id.compareTo(s.id);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

}
