package ua.com.foxminded.studentsmanager.dao.postgres;

import ua.com.foxminded.studentsmanager.dao.CourseDao;
import ua.com.foxminded.studentsmanager.dao.DaoFactory;
import ua.com.foxminded.studentsmanager.domain.*;
import ua.com.foxminded.studentsmanager.domain.entities.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgreSqlCourseDao implements CourseDao {

    private static final Logger log = Logger.getLogger(PostgreSqlCourseDao.class.getName());

    @Override
    public void add(Course[] courses) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(SqlQueries.FILL_COURSE_TABLE);
            for (Course course : courses) {
                pStatement.setString(1, course.getCourseName());
                pStatement.setString(2, course.getCourseDescription());
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
            }
        }
    }

    @Override
    public String outputCourses() {
        String sql = SqlQueries.OUTPUT_ALL_COURSES;
        Connection connection = null;
        PreparedStatement pStatement = null;
        StringBuilder builder = new StringBuilder();
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(sql);
            buildCoursesList(builder, pStatement);
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

    private StringBuilder buildCoursesList(StringBuilder builder, PreparedStatement pStatement) throws SQLException {
        try (ResultSet rs = pStatement.executeQuery()) {
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                builder.append("id of the course ").append(courseName).append(" - ").append(courseId).append("\n");
            }
        }
        return builder;
    }
}
