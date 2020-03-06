package ua.com.foxminded.studentsmanager.dao;

import ua.com.foxminded.studentsmanager.domain.entities.StudentsCourses;

import java.util.ArrayList;

public interface StudentCourseDao {

    void add(ArrayList<StudentsCourses> studentsCourses);

    void addStudentToCourse(int studentId, int courseId);

    void removeStudentFromCourse(int studentId, int courseId);

    String getStudentsByCourse(String courseName);
}
