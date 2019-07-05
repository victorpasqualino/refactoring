package com.celfocus.training.util;

import java.util.Date;

public class User {
    private String nameOfUser; // nome

    private Date birthDay; // data de nascimento

    private boolean overEighteen; // se usuário é maior de idade

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isOverEighteen() {
        return overEighteen;
    }

    public void setOverEighteen(boolean overEighteen) {
        this.overEighteen = overEighteen;
    }
}
