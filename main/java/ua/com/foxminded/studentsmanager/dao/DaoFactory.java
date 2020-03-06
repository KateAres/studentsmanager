package ua.com.foxminded.studentsmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DaoFactory {

    private static final Logger logger = Logger.getLogger(DaoFactory.class.getName());
    private static final String dbUrl = "jdbc:postgresql://127.0.0.1:5432/StudentsData";
    private static final String dbUser = "postgres";
    private static final String dbPassword = "123";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            logger.info("cannot establish connection");
            e.printStackTrace();
        }
        return connection;
    }
}
