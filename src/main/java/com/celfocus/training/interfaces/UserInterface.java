package com.celfocus.training.interfaces;

import com.celfocus.training.entity.User;

import java.util.Date;

public interface UserInterface {
    void createOrUpdateUser(String userName, Date birthDay, boolean isAdult);
    void updateUser(String userName, Date birthDay, boolean isAdult);
    void createUser(String userName, Date birthDay, boolean isAdult);
    boolean userExists(String name);
    User getUserFromName(String name);
    void deleteUser(String userName);
}
