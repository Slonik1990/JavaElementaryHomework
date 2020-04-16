package Task27Stream;

public class Student{
    private String lastname;
    private String firstname;



    public Student(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return lastname + " " +  firstname;
    }

}
