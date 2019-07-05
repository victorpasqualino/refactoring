package com.celfocus.training.util;

import com.celfocus.training.Saver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private static final List<User> users = new ArrayList<>();
    private static final List<Saver.ShoppingCart> listShoppingCarts = new ArrayList<>();
    private static final List<Saver.ItemInfo> itens = new ArrayList<>();

    public String nameOfUser;

    public Date birthDay;

    public boolean isOverEighteen;


}

