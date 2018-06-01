package com.example.earzo.styles.models;

import com.orm.SugarRecord;

public class User extends SugarRecord {

    private String userEmail;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    public User(String userEmail, String firstName, String lastName, String password) {
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
