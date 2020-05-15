package com.expocalendar.project.entities;

import java.io.Serializable;


public class Account implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String role;
    private String email;

    /**
     * default Account constructor.
     */
    public Account() {
    }


    /**
     * Account constructor for all fields.
     */
    public Account(int id, String firstName, String lastName,
                   String login, String password, String role, String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }


    /**
     * Getter for Account id
     *
     * @return id primary identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Account id
     *
     * @param id primary identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for Account firstName
     *
     * @return firstName Account holder's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for Account firstName
     *
     * @param firstName Account holder's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for Id
     *
     * @return lastName Account holder's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName
     *
     * @param lastName Account holder's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for Login
     *
     * @return login for authentication
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for login
     *
     * @param login for authentication
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for password
     *
     * @return password for authentication
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for password
     *
     * @param password for authentication
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for email
     *
     * @return email of account holder
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email of account holder
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for Account role
     *
     * @return Account role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for role
     *
     * @param role Account role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (firstName != null ? !firstName.equals(account.firstName) : account.firstName != null) return false;
        if (lastName != null ? !lastName.equals(account.lastName) : account.lastName != null) return false;
        if (login != null ? !login.equals(account.login) : account.login != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        if (role != null ? !role.equals(account.role) : account.role != null) return false;
        return email != null ? email.equals(account.email) : account.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * @return new Builder for Account
     */

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Account Builder
     */
    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private String role;
        private String email;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }


        /**
         * @return new Account bean using constructor
         */
        public Account build() {
            return new Account(id, firstName, lastName, login, password, role, email);
        }

    }
}
