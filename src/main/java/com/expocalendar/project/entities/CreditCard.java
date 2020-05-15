package com.expocalendar.project.entities;

import java.io.Serializable;



public class CreditCard implements Serializable {
    private int id;
    private String number;
    private int CVV;
    private double balance;
    private String holder;
    private int month;
    private int year;


    /**
     * default CreditCard constructor.
     */
    public CreditCard() {
    }


    /**
     * CreditCard constructor for all fields.
     */
    public CreditCard(int id, String number, int CVV, double balance, String holder, int month, int year) {
        this.id = id;
        this.number = number;
        this.CVV = CVV;
        this.balance = balance;
        this.holder = holder;
        this.month = month;
        this.year = year;
    }

    /**
     * Getter for CreditCard id
     *
     * @return id refers to Account id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for CreditCard id
     *
     * @param id refers to Account id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Getter for CreditCard number
     *
     * @return number of CreditCard
     */
    public String getNumber() {
        return number;
    }


    /**
     * Setter for CreditCard number
     *
     * @param number of CreditCard
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Setter for CreditCard CVV
     *
     * @return CVV of CreditCard
     */

    public int getCVV() {
        return CVV;
    }

    /**
     * Setter for CreditCard CVV
     *
     * @param CVV of CreditCard
     */
    public void setCVV(int CVV) {
        this.CVV = CVV;
    }


    /**
     * Getter for CreditCard balance
     *
     * @return balance of CreditCard
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Setter for CreditCard balance
     *
     * @param balance of CreditCard
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }


    /**
     * Getter for CreditCard holder
     *
     * @return holder of CreditCard
     */
    public String getHolder() {
        return holder;
    }

    /**
     * Setter for CreditCard holder
     *
     * @param holder of CreditCard
     */
    public void setHolder(String holder) {
        this.holder = holder;
    }

    /**
     * Getter for CreditCard expiration month
     *
     * @return month of CreditCard expiration
     */

    public int getMonth() {
        return month;
    }

    /**
     * Setter for CreditCard expiration month
     *
     * @param month of CreditCard expiration
     */
    public void setMonth(int month) {
        this.month = month;
    }


    /**
     * Setter for CreditCard expiration year
     *
     * @return year of CreditCard expiration
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for CreditCard expiration expiration year
     *
     * @param year of CreditCard expiration
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (id != that.id) return false;
        if (CVV != that.CVV) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (month != that.month) return false;
        if (year != that.year) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        return holder != null ? holder.equals(that.holder) : that.holder == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + CVV;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (holder != null ? holder.hashCode() : 0);
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", CVV=" + CVV +
                ", balance=" + balance +
                ", holder='" + holder + '\'' +
                ", month=" + month +
                ", year=" + year +
                '}';
    }


    /**
     * @return new CreditCard Builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * CreditCard Builder
     */
    public static class Builder {
        private int id;
        private String number;
        private int CVV;
        private double balance;
        private String holder;
        private int month;
        private int year;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setCVV(int CVV) {
            this.CVV = CVV;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setHolder(String holder) {
            this.holder = holder;
            return this;
        }

        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        /**
         * @return new CreditCard bean using constructor
         */
        public CreditCard build() {
            return new CreditCard(id, number, CVV, balance, holder, month, year);
        }

    }
}
