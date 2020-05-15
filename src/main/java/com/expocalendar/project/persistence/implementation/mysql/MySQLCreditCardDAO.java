package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.persistence.abstraction.interfaces.CreditCardDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLCreditCardDAO implements CreditCardDAO {
    private static MySQLCreditCardDAO instance;
    private final IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLCreditCardDAO.class);

    private static final int ID = 1;
    private static final int NUMBER = 2;
    private static final int CVV = 3;
    private static final int BALANCE = 4;
    private static final int HOLDER = 5;
    private static final int MONTH = 6;
    private static final int YEAR = 7;

    private static final String FIND_CARD = "SELECT * FROM cards WHERE card_id = ?";

    private MySQLCreditCardDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLCreditCardDAO getInstance() {
        if (instance == null) {
            instance = new MySQLCreditCardDAO();
        }
        return instance;
    }

    @Override
    public CreditCard findCard(int id) {
        CreditCard creditCard = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_CARD)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                creditCard = processRow(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("Requested CreditCard found for further validation");
        return creditCard;
    }

    private CreditCard processRow(ResultSet rs) throws SQLException {
        return CreditCard.newBuilder().setBalance(rs.getInt(ID)).setNumber(rs.getString(NUMBER)).
                setCVV(rs.getInt(CVV)).setBalance(rs.getDouble(BALANCE)).setHolder(rs.getString(HOLDER)).
                setMonth(rs.getInt(MONTH)).setYear(rs.getInt(YEAR)).build();

    }
}
 