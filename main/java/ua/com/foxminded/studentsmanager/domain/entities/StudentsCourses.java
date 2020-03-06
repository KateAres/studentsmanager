package ua.com.foxminded.studentsmanager.domain.entities;

public class StudentsCourses {
    private int studentID;
    private int courseId;

    public StudentsCourses(int studentId, int courseId) {
        this.studentID = studentId;
        this.courseId = courseId;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseId() {
        return courseId;
    }
}
