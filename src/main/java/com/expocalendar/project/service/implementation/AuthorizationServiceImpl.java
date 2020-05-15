package com.expocalendar.project.service.implementation;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.CreditCard;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;
import com.expocalendar.project.web.exceptions.PasswordException;
import com.expocalendar.project.web.exceptions.RegistrationException;
import com.expocalendar.project.service.interfaces.AuthorizationService;
import org.apache.log4j.Logger;

import java.util.Map;

public class AuthorizationServiceImpl implements AuthorizationService {
    private AccountDAO accountDAO;

    public AuthorizationServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void checkAccount(Map<String, String> requestParameters) throws RegistrationException {

        if (!requestParameters.get("password").equals(requestParameters.get("repeat"))) {
            throw new PasswordException("not equals");
        }

        if (accountDAO.isExist(requestParameters.get("login"))) {
            throw new RegistrationException("login exists");
        }

    }

    @Override
    public boolean createAccount(Map<String, String> requestParameters) {
        Account account = Account.newBuilder().setFirstName(requestParameters.get("firstName")).
                setLastName(requestParameters.get("lastName")).
                setLogin(requestParameters.get("login")).
                setPassword(requestParameters.get("password")).
                setEmail(requestParameters.get("email")).build();

        CreditCard creditCard = CreditCard.newBuilder().setNumber(requestParameters.get("cardNumber")).
                setCVV(Integer.valueOf(requestParameters.get("cvv"))).
                setHolder(requestParameters.get("cardHolder")).
                setMonth(Integer.valueOf(requestParameters.get("month"))).
                setYear(Integer.valueOf(requestParameters.get("year"))).build();

        return accountDAO.createAccount(account, creditCard);
    }

    @Override
    public Account updateAccount(Map<String, String> requestParameters, Account account) {
        Account updAccount = Account.newBuilder().
                setId(Integer.valueOf(requestParameters.get("accountId"))).
                setFirstName(requestParameters.get("firstName")).
                setLastName(requestParameters.get("lastName")).
                setEmail(requestParameters.get("email")).
                setLogin(account.getLogin()).setPassword(account.getPassword()).
                setRole(account.getRole()).build();

        accountDAO.updateAccount(updAccount);

        return updAccount;
    }

    @Override
    public Account findAccount(String login, String password) {
        return accountDAO.findAccount(login, password);
    }
}
