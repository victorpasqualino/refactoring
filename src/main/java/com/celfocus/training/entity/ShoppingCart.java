package com.celfocus.training.entity;

import java.util.List;

public class ShoppingCart {
    public User user;
    public List<ShoppingCartItem> shoppingCartItems;

    public ShoppingCartItem getShoppingCartItemFromItemName(String itemName) {
        return this.shoppingCartItems.stream().filter(itens -> itens.itemInfo.itemName.equals(itemName)).findAny().orElse(null);
    }
}