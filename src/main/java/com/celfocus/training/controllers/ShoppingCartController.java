package com.celfocus.training.controllers;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import com.celfocus.training.interfaces.ShoppingCartInterface;
import com.celfocus.training.util.Discounts;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartController implements ShoppingCartInterface {

    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final UserController userController = new UserController();
    private static final ItemInfoController itemController = new ItemInfoController();

    public static List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    @Override
    public void saveItemToShoppingCart(String userName, String itemName, int itemQuantity) {
        User user = userController.getUserFromName(userName);
        if (user == null) return;

        ShoppingCart shoppingCart = getShoppingCartFromUser(user);
        if (shoppingCart == null) {
            createShoppingCartForUser(user);
            shoppingCart = getShoppingCartFromUser(user);
        }

        ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItemFromItemName(itemName);

        if (shoppingCartItem != null) {
            shoppingCartItem.quantity += itemQuantity;
        } else {
            addItemToShoppingCart(itemName, itemQuantity, user, shoppingCart);
        }
    }

    private void addItemToShoppingCart(String itemName, int itemQuantity, User user, ShoppingCart shoppingCart) {
        ItemInfo item = itemController.getItemInfoFromName(itemName);
        if (item == null) return;

        double discount = Discounts.calculateDiscount(user);
        ShoppingCartItem newShoppingCartItem = new ShoppingCartItem();
        newShoppingCartItem.itemInfo = item;
        newShoppingCartItem.quantity = itemQuantity;
        newShoppingCartItem.discount = discount;
        shoppingCart.shoppingCartItems.add(newShoppingCartItem);
    }

    @Override
    public void createShoppingCartForUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.user = user;
        shoppingCart.shoppingCartItems = new ArrayList<>();
        shoppingCarts.add(shoppingCart);
    }

    @Override
    public void removeItemFromShoppingCart(String userName, String itemName) {
        User user = userController.getUserFromName(userName);
        if (user == null) return;

        ShoppingCart shoppingCart = getShoppingCartFromUser(user);
        if (shoppingCart == null) return;
        shoppingCart.shoppingCartItems.removeIf(shoppingCartItem -> shoppingCartItem.itemInfo.itemName.equals(itemName));
    }

    @Override
    public Boolean userHasShoppingCart(User user) {
        return shoppingCarts.stream().anyMatch(shoppingCart -> shoppingCart.user.equals(user));
    }

    @Override
    public ShoppingCart getShoppingCartFromUser(User user) {
        return shoppingCarts.stream().filter(shoppingCart -> shoppingCart.user.equals(user)).findAny().orElse(null);
    }

    @Override
    public void createShoppingCartIfNotExists(User user) {
        boolean userDoesNottHaveShoppingCart = !userHasShoppingCart(user);
        if (userDoesNottHaveShoppingCart) {
            createShoppingCartForUser(user);
        }
    }

}
