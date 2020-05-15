package com.expocalendar.project.service.interfaces;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.exceptions.RegistrationException;

import java.util.Map;

/**
 * Interface provides service for authorization operations
 * including Account login and registration.
 * Methods implementations implies bsiness logic
 * interacting with appropriate DAO
 *
 * @author Nicolas
 */

public interface AuthorizationService {
    /**
     * Checks if account exists in database before registration
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @throws RegistrationException when account exists or request parameters
     *                               contains invalid values.
     */

    void checkAccount(Map<String, String> requestParameters) throws RegistrationException;

    /**
     * Creates new Account in database following buisness logic of registration
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @return boolean result of Account creating operation success.
     */

    boolean createAccount(Map<String, String> requestParameters);

    /**
     * Update Account credentials in database
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @param account           Account from current session to be updated.
     * @return updated Account object.
     */
    Account updateAccount(Map<String, String> requestParameters, Account account);

    /**
     * Checking and getting Account from database if exists
     *
     * @param login    value requested by user
     * @param password value requested by user
     * @return Account founded in database.
     */

    Account findAccount(String login, String password);
}
