package ua.com.foxminded.studentsmanager.dao;

import ua.com.foxminded.studentsmanager.domain.entities.Student;

public interface StudentDao {

    void add(Student[] students);

    void deleteStudentById(int id);

    void addStudentById(int groupId, String firstName, String lastName);

    String getGroupsByStudentCount(int studentCount);
}
