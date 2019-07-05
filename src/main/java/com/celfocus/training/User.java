package com.celfocus.training;

import java.util.Date;

public class User {

    public User(String username, Date birthDate, boolean isSenior) {
        this.username = username;
        this.birthDate = birthDate;
        this.isSenior = isSenior;
    }

    private String username;
    private Date birthDate;
    private boolean isSenior;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean isSenior) {
        this.isSenior = isSenior;
    }
}