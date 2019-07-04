package com.celfocus.training.entity;

import java.util.Date;
import java.util.Objects;

public class User {

    private String name;

    private Date birthDate;

    private boolean older;

    public User() {
    }

    public User(String name, Date birthDate, boolean older) {
        this.name = name;
        this.birthDate = birthDate;
        this.older = older;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User anotherUser = (User) obj;
            return Objects.equals(anotherUser.getName(), this.getName())
                        && Objects.equals(anotherUser.getBirthDate(), this.getBirthDate())
                            && Objects.equals(anotherUser.isOlder(), this.isOlder());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public boolean hasLessThan(int maxAge) {
        return (new Date().getYear() - this.getBirthDate().getYear() < maxAge);
    }
}
