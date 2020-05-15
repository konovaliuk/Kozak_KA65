package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import com.expocalendar.project.persistence.abstraction.interfaces.OrderDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLOrderDAO implements OrderDAO {
    private static MySQLOrderDAO instance;
    private IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLOrderDAO.class);

    private static final int ORDER_ID = 11;
    private static final int TICKETS_NUMBER = 12;
    private static final int DATE_VALID = 13;

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


    private static final String FIND_ORDERS = "SELECT expositions.*, orders.order_key, tickets_number, date_valid " +
            "FROM orders JOIN expositions ON expositions.exposition_id=orders.exposition_id WHERE orders.account_id=?";
    private static final String SAVE_ORDER = "INSERT INTO orders " +
            "(order_key, account_id, exposition_id, tickets_number, date_valid) VALUES (?,?,?,?,?)";
    private static final String WITHDRAW = "UPDATE cards SET balance = ? WHERE card_id = ?";


    private MySQLOrderDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLOrderDAO getInstance() {
        if (instance == null) {
            instance = new MySQLOrderDAO();
        }
        return instance;
    }


    @Override
    public boolean saveOrder(Order order, Exposition exposition, int accountId, double remainder) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement prepInsert = null;
        PreparedStatement prepWithdraw = null;

        try {

            connection = dataSourceManager.createConnection();
            connection.setAutoCommit(false);

            prepInsert = connection.prepareStatement(SAVE_ORDER);
            prepWithdraw = connection.prepareStatement(WITHDRAW);


            prepInsert.setString(1, order.getOrderKey());
            prepInsert.setInt(2, accountId);
            prepInsert.setInt(3, exposition.getId());
            prepInsert.setInt(4, order.getTicketsNumber());
            prepInsert.setDate(5, order.getDateValid());
            prepInsert.executeUpdate();

            prepWithdraw.setDouble(1, remainder);
            prepWithdraw.setInt(2, accountId);
            prepWithdraw.executeUpdate();

            connection.commit();
            flag = true;
        } catch (SQLException e) {

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqlExc) {
                LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), sqlExc);
            }
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        } finally {
            try {

                if (prepInsert != null) {
                    prepInsert.close();
                }

                if (prepWithdraw != null) {
                    prepWithdraw.close();
                }

            } catch (SQLException e) {
                LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
            }

            dataSourceManager.closeConnection(connection);
        }


        LOGGER.info("New Order Saved in DB");
        return flag;
    }

    @Override
    public Map<Order, Exposition> getOrders(int accountId) {
        Map<Order, Exposition> orders = new HashMap<>();
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ORDERS)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.put(processOrderRow(rs), processRow(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("Account with id " + accountId + "obtained orders");
        return orders;
    }

    private Order processOrderRow(ResultSet rs) throws SQLException {
        return new Order(rs.getString(ORDER_ID), rs.getInt(TICKETS_NUMBER), rs.getDate(DATE_VALID));
    }

    private Exposition processRow(ResultSet rs) throws SQLException {
        return Exposition.newBuilder().setId(rs.getInt(ID)).setTitle(rs.getString(TITLE)).
                setDateFrom(rs.getDate(DATE_FROM)).setDateTo(rs.getDate(DATE_TO)).setTheme(rs.getString(THEME)).
                setTicketPrice(rs.getDouble(TICKET_PRICE)).setExpoHallId(rs.getInt(EXPOHALL_ID)).
                setPicture(rs.getURL(PICTURE_URL)).setDescription(rs.getString(DESCRIPTION)).
                setBeginTime(rs.getTime(BEGIN_TIME)).build();
    }
}
 