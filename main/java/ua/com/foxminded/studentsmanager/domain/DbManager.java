package ua.com.foxminded.studentsmanager.domain;

import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlCourseDao;
import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlGroupDao;
import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlStudentCourseDao;
import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlStudentDao;
import ua.com.foxminded.studentsmanager.dao.postgres.PostrgeSqlTablesCreation;
import ua.com.foxminded.studentsmanager.domain.entities.Course;
import ua.com.foxminded.studentsmanager.domain.entities.Group;
import ua.com.foxminded.studentsmanager.domain.entities.Student;
import ua.com.foxminded.studentsmanager.domain.entities.StudentsCourses;

import java.util.ArrayList;

public class DbManager {

    public void manageTables() {
        PostrgeSqlTablesCreation tablesCreation = new PostrgeSqlTablesCreation();
        tablesCreation.createTables();
        fillGroupsTable();
        fillCoursesTable();
        DataGenerator dataGenerator = new DataGenerator();
        Student[] students = dataGenerator.createStudents();
        fillStudentsTable(students);
        fillStudentsCoursesTable();
        Menu menu = new Menu();
        menu.executeMenu();
    }

    private void fillGroupsTable() {
        DataGenerator dataGenerator = new DataGenerator();
        Group[] groups = dataGenerator.generateGroups();
        PostgreSqlGroupDao groupDao = new PostgreSqlGroupDao();
        groupDao.add(groups);
    }

    private void fillCoursesTable() {
        DataGenerator dataGenerator = new DataGenerator();
        Course[] courses = dataGenerator.createCourses();
        PostgreSqlCourseDao courseDao = new PostgreSqlCourseDao();
        courseDao.add(courses);
    }

    private void fillStudentsTable(Student[] students) {
        PostgreSqlStudentDao studentDao = new PostgreSqlStudentDao();
        studentDao.add(students);
    }

    private void fillStudentsCoursesTable() {
        DataGenerator dataGenerator = new DataGenerator();
        ArrayList<StudentsCourses> studentsCourses = dataGenerator.assignCoursesToStudents();
        PostgreSqlStudentCourseDao studentCourseDao = new PostgreSqlStudentCourseDao();
        studentCourseDao.add(studentsCourses);
    }
}
