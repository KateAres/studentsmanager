package ua.com.foxminded.studentsmanager.domain;

public class SqlQueries {

    public static final String FIND_GROUPS_WITH_STUDENT_NUMBER = "SELECT groups.group_name, COUNT(student_id) StudentsCount " +
            "FROM students " +
            "JOIN groups ON students.group_id = groups.group_id " +
            "GROUP BY groups.group_name " +
            "HAVING COUNT(student_id) <=";
    public static final String ORDER_BY_STUDENTS_COUNT = "ORDER BY StudentsCount";
    public static final String DELETE_STUDENTS_FROM_COURSE = "DELETE FROM students_courses WHERE student_id = ";
    public static final String COURSE_ID = " AND course_id = ";
    public static final String OUTPUT_ALL_COURSES = "SELECT course_id, course_name FROM courses";
    public static final String ADD_STUDENT_TO_COURSE = "INSERT INTO students_courses (student_id, course_id) VALUES (";
    public static final String DELETE_STUDENT = "DELETE FROM students WHERE student_id=";
    public static final String ADD_STUDENT = "INSERT INTO students (group_id, first_name, last_name) VALUES (";
    public static final String FIND_STUDENT_IN_COURSE = "SELECT students.student_id, students.first_name, students.last_name " +
            "FROM students " +
            "JOIN students_courses ON students.student_id = students_courses.student_id " +
            "JOIN courses ON courses.course_id = students_courses.course_id " +
            "GROUP BY courses.course_name, students.first_name, students.last_name, students.student_id " +
            "HAVING courses.course_name = '";
    public static final String FILL_GROUPS_TABLE = "INSERT INTO groups (group_name) VALUES (?)";
    public static final String FILL_COURSE_TABLE = "INSERT INTO courses (course_name, course_description) VALUES (?,?)";
    public static final String FILL_STUDENTS_TABLE = "INSERT INTO students (group_id, first_name, last_name) VALUES (?,?,?)";
    public static final String FILL_JOIN_TABLE = "INSERT INTO students_courses (student_id, course_id) VALUES (?,?)";

    private SqlQueries() {
        throw new AssertionError();
    }
}
