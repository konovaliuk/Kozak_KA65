package com.expocalendar.project.persistence.implementation.mysql;

import com.expocalendar.project.persistence.abstraction.interfaces.*;
import com.expocalendar.project.persistence.abstraction.DAOFactory;


public class MySQLDAOFactory extends DAOFactory {

    @Override
    public AccountDAO getAccountDAO() {
        return MySQLAccountDAO.getInstance();
    }

    @Override
    public ExpoHallDAO getExpoHallDAO() {
        return MySQLExpoHallDAO.getInstance();
    }

    @Override
    public ExpositionDAO getExpositionDAO() {
        return MySQLExpositionDAO.getInstance();
    }

    @Override
    public CreditCardDAO getCreditCardDAO() {
        return MySQLCreditCardDAO.getInstance();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return MySQLOrderDAO.getInstance();
    }
}
 