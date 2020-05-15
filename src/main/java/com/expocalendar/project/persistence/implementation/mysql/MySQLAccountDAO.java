package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.IDataSourceManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class MySQLAccountDAO implements AccountDAO {
    private static MySQLAccountDAO instance;
    private IDataSourceManager dataSourceManager;
    private final static Logger LOGGER = Logger.getLogger(MySQLAccountDAO.class);


    private static final int ID = 1;
    private static final int FIRST_NAME = 2;
    private static final int LAST_NAME = 3;
    private static final int LOGIN = 4;
    private static final int PASSWORD = 5;
    private static final int ROLE = 6;
    private static final int EMAIL = 7;


    private static final String INSERT_ACCOUNT = "INSERT INTO accounts" +
            "(first_name,last_name,login,password,email) " + "VALUES(?,?,?,?,?)";
    private static final String INSERT_CARD = "INSERT INTO cards" +
            "(card_id,number,cvv,holder,month,year) " + "VALUES(?,?,?,?,?,?)";
    private static final String FIND_ACCOUNT = "SELECT * FROM accounts WHERE login = ? AND password = ?";
    private static final String FIND_LOGIN = "SELECT * FROM accounts WHERE login = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE accounts SET first_name = ?, " +
            "last_name = ?, email = ? WHERE account_id = ?";


    private MySQLAccountDAO() {
        dataSourceManager = MySQLDataSourceManager.getInstance();
    }

    public static MySQLAccountDAO getInstance() {
        if (instance == null) {
            instance = new MySQLAccountDAO();
        }
        return instance;
    }


    @Override
    public Account findAccount(String login, String password) {
        Account account = null;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ACCOUNT)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = processRow(rs);
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("Requested account found in DB");
        return account;
    }

    @Override
    public boolean createAccount(Account account, CreditCard creditCard) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStAccount = null;
        PreparedStatement preparedStCard = null;
        try {
            connection = dataSourceManager.createConnection();
            preparedStAccount = connection.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            preparedStCard = connection.prepareStatement(INSERT_CARD);
            connection.setAutoCommit(false);

            prepareAccount(preparedStAccount, account);
            preparedStAccount.executeUpdate();

            ResultSet resultSet = preparedStAccount.getGeneratedKeys();

            prepareCard(preparedStCard, creditCard, resultSet);
            preparedStCard.executeUpdate();

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

                if (preparedStAccount != null) {
                    preparedStAccount.close();
                }

                if (preparedStCard != null) {
                    preparedStCard.close();
                }

            } catch (SQLException e) {
                LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
            }

            dataSourceManager.closeConnection(connection);
        }

        LOGGER.info("New Account created with related CreditCard");
        return flag;
    }


    @Override
    public boolean isExist(String login) {
        boolean exist = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            exist = rs.next();

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("Account existence checked in DB");
        return exist;
    }

    @Override
    public boolean updateAccount(Account account) {
        boolean flag = false;
        try (Connection connection = dataSourceManager.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT)) {
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setInt(4, account.getId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in " + getClass().getSimpleName(), e);
        }
        LOGGER.info("Account data updated");
        return flag;
    }

    private Account processRow(ResultSet rs) throws SQLException {
        return Account.newBuilder().setId(rs.getInt(ID)).
                setFirstName(rs.getString(FIRST_NAME)).
                setLastName(rs.getString(LAST_NAME)).
                setLogin(rs.getString(LOGIN)).
                setPassword(rs.getString(PASSWORD)).
                setRole(rs.getString(ROLE)).
                setEmail(rs.getString(EMAIL)).build();
    }

    private void prepareAccount(PreparedStatement ps, Account account) throws SQLException {
        ps.setString(1, account.getFirstName());
        ps.setString(2, account.getLastName());
        ps.setString(3, account.getLogin());
        ps.setString(4, account.getPassword());
        ps.setString(5, account.getEmail());
    }

    private void prepareCard(PreparedStatement ps, CreditCard creditCard, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            ps.setInt(1, resultSet.getInt(1));
        }
        ps.setString(2, creditCard.getNumber());
        ps.setInt(3, creditCard.getCVV());
        ps.setString(4, creditCard.getHolder());
        ps.setInt(5, creditCard.getMonth());
        ps.setInt(6, creditCard.getYear());
    }
}
 