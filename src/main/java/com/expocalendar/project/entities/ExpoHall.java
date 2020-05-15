package com.expocalendar.project.entities;

import java.io.Serializable;



public class ExpoHall implements Serializable {
    private int id;
    private String name;
    private String address;

    /**
     * ExpoHall default constructor
     */
    public ExpoHall() {
    }

    /**
     * ExpoHall constructor for not generated fields
     */
    public ExpoHall(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * ExpoHall constructor for all fields.
     */
    public ExpoHall(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Getter for ExpoHall id
     *
     * @return ExpoHall primary identifier
     */
    public int getId() {
        return id;
    }


    /**
     * Setter for ExpoHall id
     *
     * @param id primary identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for ExpoHall name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for ExpoHall name
     *
     * @param name of ExpoHall
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for ExpoHall address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Setter for ExpoHall address
     *
     * @param address of ExpoHall
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpoHall expoHall = (ExpoHall) o;

        if (id != expoHall.id) return false;
        if (name != null ? !name.equals(expoHall.name) : expoHall.name != null) return false;
        return address != null ? address.equals(expoHall.address) : expoHall.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExpoHall{" +
                "name='" + name + '\'' +
                ", city='" + address + '\'' +
                '}';
    }
}
