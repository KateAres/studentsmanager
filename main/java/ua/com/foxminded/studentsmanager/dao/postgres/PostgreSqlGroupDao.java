package ua.com.foxminded.studentsmanager.dao.postgres;

import ua.com.foxminded.studentsmanager.dao.DaoFactory;
import ua.com.foxminded.studentsmanager.dao.GroupDao;
import ua.com.foxminded.studentsmanager.domain.Constants;
import ua.com.foxminded.studentsmanager.domain.DbManager;
import ua.com.foxminded.studentsmanager.domain.entities.Group;
import ua.com.foxminded.studentsmanager.domain.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgreSqlGroupDao implements GroupDao {

    private static Logger log = Logger.getLogger(DbManager.class.getName());

    @Override
    public void add(Group[] groups) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            DaoFactory daoFactory = new DaoFactory();
            connection = daoFactory.getConnection();
            pStatement = connection.prepareStatement(SqlQueries.FILL_GROUPS_TABLE);
            for (Group group : groups) {
                pStatement.setString(1, group.getGroupName());
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
}
