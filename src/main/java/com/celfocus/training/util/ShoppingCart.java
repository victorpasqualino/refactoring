package com.celfocus.training.util;

import com.celfocus.training.Saver;

import java.util.List;

public class ShoppingCart {
    private User user;

    private List<ShoppingCartItem> itens;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getItens() {
        return itens;
    }

    public void setItens(List<ShoppingCartItem> itens) {
        this.itens = itens;
    }
}
