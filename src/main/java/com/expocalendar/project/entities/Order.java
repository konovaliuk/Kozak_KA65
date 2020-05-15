package com.expocalendar.project.entities;

import java.io.Serializable;
import java.sql.Date;


public class Order implements Serializable {
    private String orderKey;
    private int ticketsNumber;
    private Date dateValid;
    /**
     *  Order default constructor
     */
    public Order() {
    }
    /**
     *  Order constructor for all fields
     */
    public Order(String orderKey, int ticketsNumber, Date dateValid) {
        this.orderKey = orderKey;
        this.ticketsNumber = ticketsNumber;
        this.dateValid = dateValid;
    }

    /**
     * Getter for Order unique key
     *
     * @return Order primary identifier
     */

    public String getOrderKey() {
        return orderKey;
    }
    /**
     * Setter for Order unique key
     *
     * @param orderKey Order primary identifier
     */

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    /**
     * Getter for tickets number in Order
     *
     * @return Order tickets number
     */
    public int getTicketsNumber() {
        return ticketsNumber;
    }

    /**
     * Setter for tickets number in Order
     *
     * @param ticketsNumber tickets number in Order
     */
    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    /**
     * Getter for Date utill order valid
     *
     * @return Date until order is valid
     */

    public Date getDateValid() {
        return dateValid;
    }
    /**
     * Setter for Date until order valid
     *
     * @param dateValid Date until order is valid
     */
    public void setDateValid(Date dateValid) {
        this.dateValid = dateValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (ticketsNumber != order.ticketsNumber) return false;
        if (orderKey != null ? !orderKey.equals(order.orderKey) : order.orderKey != null) return false;
        return dateValid != null ? dateValid.equals(order.dateValid) : order.dateValid == null;
    }

    @Override
    public int hashCode() {
        int result = orderKey != null ? orderKey.hashCode() : 0;
        result = 31 * result + ticketsNumber;
        result = 31 * result + (dateValid != null ? dateValid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderKey='" + orderKey + '\'' +
                ", ticketsNumber=" + ticketsNumber +
                ", dateValid=" + dateValid +
                '}';
    }
}
