package com.celfocus.training.interfaces;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

public interface ShoppingCartInterface {
    void saveItemToShoppingCart(String userName, String itemName, int itemQuantity);
    void createShoppingCartForUser(User user);
    void removeItemFromShoppingCart(String userName, String itemName);
    Boolean userHasShoppingCart(User user);
    ShoppingCart getShoppingCartFromUser(User user);
    void createShoppingCartIfNotExists(User user);
}
