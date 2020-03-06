package ua.com.foxminded.studentsmanager.dao.postgres;

import ua.com.foxminded.studentsmanager.dao.DaoFactory;
import ua.com.foxminded.studentsmanager.dao.StudentDao;
import ua.com.foxminded.studentsmanager.domain.Constants;
import ua.com.foxminded.studentsmanager.domain.DbManager;
import ua.com.foxminded.studentsmanager.domain.SqlQueries;
import ua.com.foxminded.studentsmanager.domain.entities.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgreSqlStudentDao implements StudentDao {

    private static Logger log = Logger.getLogger(DbManager.class.getName());

    @Override
    public void add(Student[] students) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(SqlQueries.FILL_STUDENTS_TABLE);
            for (Student student : students) {
                pStatement.setObject(1, student.getGroupId());
                pStatement.setString(2, student.getFirstName());
                pStatement.setString(3, student.getLastName());
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
    public void deleteStudentById(int studentId) {
        String sql = SqlQueries.DELETE_STUDENT + studentId;
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
    public void addStudentById(int groupId, String firstName, String lastName) {
        String sql = SqlQueries.ADD_STUDENT + groupId + ",'" + firstName + "','" + lastName + "')";
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
    public String getGroupsByStudentCount(int studentCount) {
        String sql = SqlQueries.FIND_GROUPS_WITH_STUDENT_NUMBER + studentCount +
                SqlQueries.ORDER_BY_STUDENTS_COUNT;
        Connection connection = null;
        PreparedStatement pStatement = null;
        StringBuilder builder = new StringBuilder();
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(sql);
            builder = buildGroupsList(builder, pStatement);
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

    private StringBuilder buildGroupsList(StringBuilder builder, PreparedStatement pStatement) throws SQLException {
        try (ResultSet rs = pStatement.executeQuery()) {
            while (rs.next()) {
                String groupName = rs.getString("group_name");
                int studentsCount = rs.getInt("StudentsCount");
                builder.append("group ").append(groupName).append(" contains ").append(studentsCount).append(" students").append("\n");
            }
        }
        return builder;
    }
}
