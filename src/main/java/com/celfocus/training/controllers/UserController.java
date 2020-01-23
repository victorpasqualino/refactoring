package com.celfocus.training.controllers;

import com.celfocus.training.entity.User;
import com.celfocus.training.interfaces.UserInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserController implements UserInterface {
    private static final List<User> users = new ArrayList<>();

    private static final ItemInfoController itemController = new ItemInfoController();
    private static final ShoppingCartController shoppingCartController = new ShoppingCartController();


    public List<User> getUsers() {
        return users;
    }


    @Override
    public void createOrUpdateUser(String userName, Date birthDay, boolean isAdult) {
        if (userExists(userName)) {
            updateUser(userName, birthDay, isAdult);
        } else {
            createUser(userName, birthDay, isAdult);
        }
    }

    @Override
    public void updateUser(String userName, Date birthDay, boolean isAdult) {
        User user = getUserFromName(userName);
        user.birthDay = birthDay;
        user.isAdult = isAdult;
        users.add(user);
        shoppingCartController.createShoppingCartIfNotExists(user);
    }

    @Override
    public void createUser(String userName, Date birthDay, boolean isAdult) {
        User user = new User();
        user.birthDay = birthDay;
        user.userName = userName;
        user.isAdult = isAdult;
        users.add(user);
        shoppingCartController.createShoppingCartForUser(user);
    }

    @Override
    public boolean userExists(String name) {
        return users.stream().anyMatch(user -> user.userName.equals(name));
    }

    @Override
    public User getUserFromName(String name) {
        return users.stream().filter(user -> user.userName.equals(name)).findAny().orElse(null);
    }

    @Override
    public void deleteUser(String userName) {
        User user = getUserFromName(userName);
        if (user != null) {
            users.remove(user);
        }
    }


}
