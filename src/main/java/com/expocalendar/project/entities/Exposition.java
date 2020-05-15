package com.expocalendar.project.entities;

import java.io.Serializable;
import java.net.URL;
import java.sql.Time;
import java.sql.Date;


public class Exposition implements Serializable {
    private int id;
    private String title;
    private String theme;
    private Date dateFrom;
    private Date dateTo;
    private Time beginTime;
    private double ticketPrice;
    private int expoHallId;
    private URL picture;
    private String description;

    /**
     * Exposition default constructor
     */
    public Exposition() {
    }

    /**
     * Exposition constructor for all fields
     */
    public Exposition(int id, String title, String theme, Date dateFrom, Date dateTo,
                      Time beginTime, double ticketPrice, int expoHallId, URL picture, String description) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.beginTime = beginTime;
        this.ticketPrice = ticketPrice;
        this.expoHallId = expoHallId;
        this.picture = picture;
        this.description = description;
    }

    /**
     * Getter for Exposition id
     *
     * @return ExpoHall primary identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Exposition id
     *
     * @param id primary identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for Exposition title
     *
     * @return Exposition title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for Exposition title
     *
     * @param title of Exposition
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for Exposition theme
     *
     * @return Exposition theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Setter for Exposition theme
     *
     * @param theme of Exposition
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Getter for Exposition start date
     *
     * @return Exposition start date
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * Setter for Exposition start date
     *
     * @param dateFrom Exposition start date
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Getter for Exposition end date
     *
     * @return Exposition end date
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * Setter for Exposition end date
     *
     * @param dateTo primary end date
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Getter for Exposition everyday begin time
     *
     * @return Exposition everyday begin time
     */
    public Time getBeginTime() {
        return beginTime;
    }

    /**
     * Setter for Exposition everyday begin time
     *
     * @param beginTime Exposition everyday begin time
     */
    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * Getter for Exposition ticket price
     *
     * @return ticketPrice Exposition ticket price
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Setter for Exposition ticket price
     *
     * @param ticketPrice ticket price
     */
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Getter for Exposition ExpoHall id
     *
     * @return expoHallId which refers to ExpoHall id
     */
    public int getExpoHallId() {
        return expoHallId;
    }

    /**
     * Setter for Exposition's ExpoHall id
     *
     * @param expoHallId refers to ExpoHall id
     */
    public void setExpoHallId(int expoHallId) {
        this.expoHallId = expoHallId;
    }

    /**
     * Getter for Exposition's picture URL
     *
     * @return picture URL
     */
    public URL getPicture() {
        return picture;
    }

    /**
     * Setter for Exposition picture URL
     *
     * @param picture URL
     */
    public void setPicture(URL picture) {
        this.picture = picture;
    }

    /**
     * Getter for Exposition description
     *
     * @return description for Exposition
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for Exposition description
     *
     * @param description for Exposition
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exposition that = (Exposition) o;

        if (id != that.id) return false;
        if (Double.compare(that.ticketPrice, ticketPrice) != 0) return false;
        if (expoHallId != that.expoHallId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (beginTime != null ? !beginTime.equals(that.beginTime) : that.beginTime != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        temp = Double.doubleToLongBits(ticketPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + expoHallId;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", startDate=" + dateFrom +
                ", endDate=" + dateTo +
                '}';
    }

    /**
     * Exposition Builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String title;
        private String theme;
        private Date dateFrom;
        private Date dateTo;
        private Time beginTime;
        private double ticketPrice;
        private int expoHallId;
        private URL picture;
        private String description;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public Builder setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder setDateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Builder setBeginTime(Time beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public Builder setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder setExpoHallId(int expoHallId) {
            this.expoHallId = expoHallId;
            return this;
        }

        public Builder setPicture(URL picture) {
            this.picture = picture;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * @return new Exposition bean using constructor
         */
        public Exposition build() {
            return new Exposition(id, title, theme, dateFrom, dateTo,
                    beginTime, ticketPrice, expoHallId, picture, description);
        }

    }
}
