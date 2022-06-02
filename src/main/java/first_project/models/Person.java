package first_project.models;

import org.springframework.stereotype.Component;

@Component
public class Person {
    private int id;


    private String fullName;
    private int yearOfBirthday;

    public Person() {
    }

    public Person(int id, String fullName, int yearOfBirthday) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirthday = yearOfBirthday;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", yearOfBirthday=" + yearOfBirthday +
                '}';
    }
}
