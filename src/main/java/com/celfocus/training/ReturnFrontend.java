package com.celfocus.training;

import com.celfocus.training.entity.Item;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

public interface ReturnFrontend {

    String returnFrontendUser(String type, User user);

    String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart);

    String returnFrontendItem(String type, Item item);
}
