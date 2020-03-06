package ua.com.foxminded.studentsmanager.dao.postgres;

import ua.com.foxminded.studentsmanager.dao.DaoFactory;
import ua.com.foxminded.studentsmanager.dao.StudentCourseDao;
import ua.com.foxminded.studentsmanager.domain.*;
import ua.com.foxminded.studentsmanager.domain.entities.StudentsCourses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PostgreSqlStudentCourseDao implements StudentCourseDao {

    private static Logger log = Logger.getLogger(PostgreSqlStudentCourseDao.class.getName());

    @Override
    public void add(ArrayList<StudentsCourses> studentsCourses) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(SqlQueries.FILL_JOIN_TABLE);
            for (StudentsCourses studentWithCourse : studentsCourses) {
                pStatement.setObject(1, studentWithCourse.getStudentID());
                pStatement.setObject(2, studentWithCourse.getCourseId());
                pStatement.execute();
            }
        } catch (SQLException e) {
            log.warning(Constants.CANNOT_ESTABLISH_CONNECTION);
            e.printStackTrace();
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_STATEMENT);
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_CONNECTION);
                e.printStackTrace();
            }
        }
    }


    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        String sql = SqlQueries.ADD_STUDENT_TO_COURSE + studentId + ", " + courseId + ")";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.execute();
        } catch (SQLException e) {
            log.warning(Constants.CANNOT_ESTABLISH_CONNECTION);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_STATEMENT);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_CONNECTION);
            }
        }
    }

    @Override
    public void removeStudentFromCourse(int studentId, int courseId) {
        String sql = SqlQueries.DELETE_STUDENTS_FROM_COURSE + studentId + SqlQueries.COURSE_ID + courseId;
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.execute();
        } catch (SQLException e) {
            log.warning(Constants.CANNOT_ESTABLISH_CONNECTION);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_STATEMENT);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_CONNECTION);
            }
        }
    }

    @Override
    public String getStudentsByCourse(String courseName) {
        String sql = SqlQueries.FIND_STUDENT_IN_COURSE + courseName + "'";
        Connection connection = null;
        PreparedStatement pStatement = null;
        StringBuilder builder = new StringBuilder();
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(sql);
            builder = buildStudentsList(builder, pStatement);
        } catch (SQLException e) {
            log.warning(Constants.CANNOT_ESTABLISH_CONNECTION);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_STATEMENT);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.warning(Constants.CANNOT_CLOSE_CONNECTION);
            }
        }
        return builder.toString();
    }

    private StringBuilder buildStudentsList(StringBuilder builder, PreparedStatement pStatement) throws SQLException {
        try (ResultSet rs = pStatement.executeQuery()) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int studentId = rs.getInt("student_id");
                builder.append(studentId).append(" ").append(firstName).append(" ").append(lastName).append("\n");
            }
            return builder;
        }
    }
}
