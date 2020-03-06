package ua.com.foxminded.studentsmanager.dao.postgres;

import ua.com.foxminded.studentsmanager.dao.DaoFactory;
import ua.com.foxminded.studentsmanager.domain.Constants;
import ua.com.foxminded.studentsmanager.domain.FileDataReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostrgeSqlTablesCreation {

    private static final Logger log = Logger.getLogger(PostrgeSqlTablesCreation.class.getName());

    public void createTables() {
        FileDataReader fileDataReader = new FileDataReader();
        String sql = fileDataReader.getDataFromFile(Constants.CREATE_TABLES);
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
}
