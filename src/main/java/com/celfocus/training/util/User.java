package com.celfocus.training.util;

import com.celfocus.training.Saver;


import java.util.Date;


public class User {

    private String name;
    private Date birthDate;
    private boolean older;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isOlder() {
        return older;
    }

    public void setOlder(boolean older) {
        this.older = older;
    }

    public User() {
    }

    public User(String name, Date birthDate, boolean older) {
        this.name = name;
        this.birthDate = birthDate;
        this.older = older;
    }
}

