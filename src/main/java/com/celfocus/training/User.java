package com.celfocus.training;

import java.util.Date;

public class User {

    public User(String username, Date birthDate, boolean isOfAge) {
        this.username = username;
        this.birthDate = birthDate;
        this.isOfAge = isOfAge;
    }

    private String username;
    private Date birthDate;
    private boolean isOfAge;

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

    public boolean isOfAge() {
        return isOfAge;
    }

    public void setOfAge(boolean ofAge) {
        isOfAge = ofAge;
    }
}