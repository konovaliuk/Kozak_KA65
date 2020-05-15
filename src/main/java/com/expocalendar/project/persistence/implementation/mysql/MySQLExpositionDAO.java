package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLExpositionDAO implements ExpositionDAO {
    private static MySQLExpositionDAO instance;
    private final IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLExpositionDAO.class);


    private static final int ID = 1;
    private static final int TITLE = 2;
    private static final int DATE_FROM = 3;
    private static final int DATE_TO = 4;
    private static final int THEME = 5;
    private static final int TICKET_PRICE = 6;
    private static final int EXPOHALL_ID = 7;
    private static final int PICTURE_URL = 8;
    private static final int DESCRIPTION = 9;
    private static final int BEGIN_TIME = 10;


    private static final String FIND_All = "SELECT * FROM expositions";
    private static final String FIND_EXPOSITION = "SELECT * FROM expositions WHERE exposition_id = ?";
    private static final String FIND_THEMES = "SELECT DISTINCT theme FROM expositions";
    private static final String INSERT_EXPOSITION = "INSERT INTO expositions" +
            "(title, date_from, date_to, theme, ticket_price, expohall_id, picture, description, begin_time) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_EXPOSITION = "UPDATE expositions SET title = ?, date_from = ?, " +
            "date_to = ?, theme = ?, ticket_price = ?, expohall_id = ?, picture = ?, " +
            "description = ?, begin_time = ? WHERE exposition_id = ?";
    private static final String DELETE_EXPOSITION = "DELETE FROM expositions WHERE exposition_id = ?";


    private MySQLExpositionDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLExpositionDAO getInstance() {
        if (instance == null) {
            instance = new MySQLExpositionDAO();
        }
        return instance;
    }

    @Override
    public List<Exposition> findAll() {
        List<Exposition> expositions = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_All);
            while (rs.next()) {
                expositions.add(processRow(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }

        LOGGER.info("All expositions obtained from DB");
        return expositions;
    }

    @Override
    public List<String> findThemes() {
        List<String> themes = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_THEMES);
            while (rs.next()) {
                themes.add(rs.getString(1));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("List of themes obtained from DB");
        return themes;
    }


    public List<Exposition> findExpositions(String query) {
        List<Exposition> expositions = new ArrayList<>();
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                expositions.add(processRow(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }

        LOGGER.info("List of expositions related on request parameters obtained from DB");
        return expositions;
    }

    @Override
    public int countExpositions(String query) {
        int count = 0;
        try (Connection connection = dataSourceManager.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info(count + " expositions found, based on request criteria");
        return count;
    }

    @Override
    public boolean createExposition(Exposition exposition) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXPOSITION)) {
            prepare(preparedStatement, exposition);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("New Expositions created");
        return flag;
    }

    @Override
    public Exposition findExposition(int expositionId) {
        Exposition exposition = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_EXPOSITION)) {
            ps.setInt(1, expositionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exposition = processRow(rs);
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }

        LOGGER.info("Requested Exposition found in DB");
        return exposition;
    }


    @Override
    public boolean updateExposition(Exposition exposition) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXPOSITION)) {
            prepare(preparedStatement, exposition);
            preparedStatement.setInt(10, exposition.getId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }

        LOGGER.info("Expositions data updated");
        return flag;
    }

    @Override
    public boolean deleteExposition(int expositionId) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXPOSITION)) {
            preparedStatement.setInt(1, expositionId);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }

        LOGGER.info("Expositions deleted from DB");
        return flag;
    }


    private Exposition processRow(ResultSet rs) throws SQLException {
        return Exposition.newBuilder().setId(rs.getInt(ID)).setTitle(rs.getString(TITLE)).
                setDateFrom(rs.getDate(DATE_FROM)).setDateTo(rs.getDate(DATE_TO)).setTheme(rs.getString(THEME)).
                setTicketPrice(rs.getDouble(TICKET_PRICE)).setExpoHallId(rs.getInt(EXPOHALL_ID)).
                setPicture(rs.getURL(PICTURE_URL)).setDescription(rs.getString(DESCRIPTION)).
                setBeginTime(rs.getTime(BEGIN_TIME)).build();
    }


    private void prepare(PreparedStatement ps, Exposition exposition) throws SQLException {
        ps.setString(1, exposition.getTitle());
        ps.setDate(2, exposition.getDateFrom());
        ps.setDate(3, exposition.getDateTo());
        ps.setString(4, exposition.getTheme());
        ps.setDouble(5, exposition.getTicketPrice());
        ps.setInt(6, exposition.getExpoHallId());
        ps.setString(7, exposition.getPicture().toString());
        ps.setString(8, exposition.getDescription());
        ps.setTime(9, exposition.getBeginTime());
    }
}
 