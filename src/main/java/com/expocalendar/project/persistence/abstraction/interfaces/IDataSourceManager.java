package com.expocalendar.project.persistence.abstraction.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataSourceManager {
    /**
     *
     * @return new Connection
     * @throws SQLException
     */
    Connection createConnection() throws SQLException;

    /**
     *
     * @param connection to be closed
     */
    void closeConnection(Connection connection);

}
 