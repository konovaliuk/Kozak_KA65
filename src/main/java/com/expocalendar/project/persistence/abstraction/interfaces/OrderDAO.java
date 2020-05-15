package com.expocalendar.project.persistence.abstraction.interfaces;


import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.entities.Order;

import java.util.Map;

public interface OrderDAO {
    /**
     * Obtaining all order related to Account
     *
     * @param accountId id of Account which requested its orders
     * @return map of orders and related expositions
     */
    Map<Order, Exposition> getOrders(int accountId);

    /**
     * Saves new order in database
     *
     * @param order      new Order
     * @param exposition id ordered
     * @param accountId  id of Account which requested its orders
     * @param remainder of balance in Account's related CreditCard
     * @return map of orders and related expositions
     */
    boolean saveOrder(Order order, Exposition exposition, int accountId, double remainder);

}
 