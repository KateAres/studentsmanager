package ua.com.foxminded.studentsmanager.domain;

import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlCourseDao;
import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlStudentCourseDao;
import ua.com.foxminded.studentsmanager.dao.postgres.PostgreSqlStudentDao;

public class Menu {
    public void outputMessage(String message) {
        System.out.println(message);
    }

    public void executeMenu() {

        outputMessage("Choose what to do:");
        outputMessage("a. - Find all groups with less or equals student count.");
        outputMessage("b. - Find all students related to course with given name.");
        outputMessage("c. - Add new student.");
        outputMessage("d. - Delete student by student_id.");
        outputMessage("e. - Add a student to the course (from a list).");
        outputMessage("f. - Remove the student from one of his or her courses.");

        switch (InputService.input()) {
            case ("a"):
                int studentCount = InputService.inputStudentCount();
                outputMessage(findGroupsWithStudentNumber(studentCount));
                break;
            case ("b"):
                String courseName = InputService.inputCourseName();
                outputMessage(findStudentsInCourse(courseName));
                break;
            case ("c"):
                int groupId = InputService.inputGroupId();
                String firstName = InputService.inputFirstName();
                String lastName = InputService.inputLastName();
                addStudent(groupId, firstName, lastName);
                outputMessage("The student data have been added");
                break;
            case ("d"):
                deleteStudent(InputService.inputStudentId());
                outputMessage("The student data have been deleted.");
                break;
            case ("e"):
                int studentId = InputService.inputStudentId();
                outputMessage(outputAllCourses());
                int courseId = InputService.inputCourseId();
                addStudentToCourse(studentId, courseId);
                outputMessage("Student " +
                        studentId + " has been added to the course with ID " + courseId);
                break;
            case ("f"):
                deleteStudentFromCourse(InputService.inputStudentId(), InputService.inputCourseId());
                outputMessage("The student has been deleted from the course");
                break;
            default:
                throw new IllegalArgumentException("The letter is invalid");
        }
    }

    private void deleteStudentFromCourse(int studentId, int courseId) {
        PostgreSqlStudentCourseDao sqlStudentCourseDao = new PostgreSqlStudentCourseDao();
        sqlStudentCourseDao.removeStudentFromCourse(studentId, courseId);
    }

    private String outputAllCourses() {
        PostgreSqlCourseDao sqlCourseDao = new PostgreSqlCourseDao();
        return sqlCourseDao.outputCourses();
    }

    private void addStudentToCourse(int studentId, int courseId) {
        PostgreSqlStudentCourseDao sqlStudentCourseDao = new PostgreSqlStudentCourseDao();
        sqlStudentCourseDao.removeStudentFromCourse(studentId, courseId);
    }

    private void deleteStudent(int studentId) {
        PostgreSqlStudentDao sqlStudentDao = new PostgreSqlStudentDao();
        sqlStudentDao.deleteStudentById(studentId);
    }

    private void addStudent(int groupId, String firstName, String lastName) {
        PostgreSqlStudentDao sqlStudentDao = new PostgreSqlStudentDao();
        sqlStudentDao.addStudentById(groupId, firstName, lastName);
    }

    private String findGroupsWithStudentNumber(int studentCount) {
        PostgreSqlStudentDao sqlStudentDao = new PostgreSqlStudentDao();
        return sqlStudentDao.getGroupsByStudentCount(studentCount);
    }

    private String findStudentsInCourse(String courseName) {
        PostgreSqlStudentCourseDao sqlStudentCourseDao = new PostgreSqlStudentCourseDao();
        return sqlStudentCourseDao.getStudentsByCourse(courseName);
    }
}
