package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;

import java.sql.SQLException;


public interface AccountDAO {

    /**
     * @param login    Account's login
     * @param password Account's password
     * @return Account found in database with requested login and password
     */
    Account findAccount(String login, String password);

    /**
     * @param account    will be created in database
     * @param creditCard will be created in database
     * @return boolean result of transaction success
     */
    boolean createAccount(Account account, CreditCard creditCard);

    /**
     * @param login requested
     * @return boolean result depending on account with this login exist's in database
     */
    boolean isExist(String login);

    /**
     * @param account will be updated in database
     * @return boolean result of operation success
     */
    boolean updateAccount(Account account);

}
