package ua.com.foxminded.studentsmanager.domain.entities;

public class Course {

    private int courseId;
    private String courseName;
    private String courseDescription;

    public Course(int courseId, String courseName, String courseDescription) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
