package ua.com.foxminded.studentsmanager.domain.entities;

public class Student {

    private int studentId;
    private Integer groupId;
    private String firstName;
    private String lastName;

    public Student(int studentId, Integer groupId, String firstName, String lastName) {
        this.studentId = studentId;
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
