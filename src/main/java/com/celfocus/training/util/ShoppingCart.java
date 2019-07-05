package com.celfocus.training.util;

import java.util.List;


public class ShoppingCart {

        private User user;

        private List<ShoppingCartItem> listItems;

    public ShoppingCart(User user, List<ShoppingCartItem> listItems) {
        this.user = user;
        this.listItems = listItems;
    }

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ShoppingCartItem> listItems) {
        this.listItems = listItems;
    }
}


