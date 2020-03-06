package ua.com.foxminded.studentsmanager.dao;

import ua.com.foxminded.studentsmanager.domain.entities.Course;

public interface CourseDao {

    void add (Course[] course);

    String outputCourses ();
}
