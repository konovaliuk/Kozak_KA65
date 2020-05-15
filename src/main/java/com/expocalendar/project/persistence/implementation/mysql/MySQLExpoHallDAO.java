package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLExpoHallDAO implements ExpoHallDAO {
    private static MySQLExpoHallDAO instance;
    private final IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLExpoHallDAO.class);

    private static final int ID = 1;
    private static final int NAME = 2;
    private static final int ADDRESS = 3;


    private static final String FIND_ALL_EXPOHALLS = "SELECT * FROM expohalls";
    private static final String FIND_EXPOHAll = "SELECT * FROM expohalls WHERE expohall_id = ?";
    private static final String INSERT_EXPOHALL = "INSERT INTO expohalls (name, address) VALUES(?,?)";
    private static final String UPDATE_EXPOHALL = "UPDATE expohalls SET name = ?, address = ? WHERE expohall_id = ?";
    private static final String DELETE_EXPOHALL = "DELETE FROM expohalls WHERE expohall_id = ?";


    private MySQLExpoHallDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLExpoHallDAO getInstance() {
        if (instance == null) {
            instance = new MySQLExpoHallDAO();
        }
        return instance;
    }

    public List<ExpoHall> findAll() {
        List<ExpoHall> expoHalls = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL_EXPOHALLS);
            while (rs.next()) {
                expoHalls.add(processRow(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("List of ExpoHalls obtained from DB");
        return expoHalls;
    }

    @Override
    public ExpoHall findExpoHall(int id) {
        ExpoHall expoHall = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_EXPOHAll)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                expoHall = processRow(rs);
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("Requested ExpoHall obtained from DB");
        return expoHall;
    }

    @Override
    public boolean createExpoHall(ExpoHall expoHall) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXPOHALL)) {
            preparedStatement.setString(1, expoHall.getName());
            preparedStatement.setString(2, expoHall.getAddress());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("New ExpoHall created");
        return flag;
    }

    @Override
    public boolean updateExpoHall(ExpoHall expoHall) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXPOHALL)) {
            preparedStatement.setString(1, expoHall.getName());
            preparedStatement.setString(2, expoHall.getAddress());
            preparedStatement.setInt(3, expoHall.getId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("ExpoHall data updated");
        return flag;
    }

    @Override
    public boolean deleteExpoHall(int id) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXPOHALL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("ExpoHall deleted");
        return flag;
    }

    private ExpoHall processRow(ResultSet rs) throws SQLException {
        return new ExpoHall(rs.getInt(ID), rs.getString(NAME), rs.getString(ADDRESS));
    }
}
 