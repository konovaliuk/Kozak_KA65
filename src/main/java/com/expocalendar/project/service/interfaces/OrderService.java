package com.expocalendar.project.service.interfaces;

import com.expocalendar.project.entities.*;


import java.util.Map;

/**
 * Interface provides service for ordering operations
 * Methods implementations implies business logic
 * interacting with appropriate DAO
 *
 * @author Nicolas
 */

public interface OrderService {
    /**
     * Processing new order of Account.
     *
     * @param account           User which makes new order.
     * @param requestParameters extracted from HttpServletRequest object
     * @return boolean result of transaction.
     */
    boolean processOrder(Account account, Map<String, String> requestParameters);

    /**
     * Obtaining all order related to Account
     *
     * @param account Account which requested its orders
     * @return map of orders and related expositions
     */

    Map<Order, Exposition> getOrders(Account account);

    /**
     * sending mail to recipient after successful ticket purchase
     * or resending it on user request
     *
     * @param order      provides information to email content
     * @param account    recipient of email.
     * @param exposition object provides information to email content
     * @param expoHall   object provides information to email content
     */

    void sendMail(Order order, Account account, Exposition exposition, ExpoHall expoHall);
}
