package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class MySQLDataSourceManager implements IDataSourceManager {
    private final static String RESOURCE_NAME = "java:comp/env/jdbc/exposition_calendar_db";
    private final static Logger LOGGER = Logger.getLogger(MySQLDataSourceManager.class);
    private static MySQLDataSourceManager instance;
    private DataSource dataSource;

    private MySQLDataSourceManager() {
    }

    public static MySQLDataSourceManager getInstance() {
        if (instance == null) {
            instance = new MySQLDataSourceManager();
        }
        return instance;
    }


    @Override
    public Connection createConnection() throws SQLException {
        try {
            dataSource = (DataSource) new InitialContext().lookup(RESOURCE_NAME);
        } catch (NamingException e) {
            LOGGER.error("NamingException occurred in " + getClass().getSimpleName(), e);
        }

        LOGGER.info("New DataSource connection created");
        return dataSource.getConnection();
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException  occurred in " + getClass().getSimpleName(), e);
        }
    }
}
 