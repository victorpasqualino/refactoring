package com.celfocus.training;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public static class User {

        public String nameOfUser; // nome
        public Date dateOfBirth; // data de nascimento
        public boolean isOfLegalAge; // se usuário é maior de idade
    }

    public static class ShoppingCart {

        public User user;
        public List<ShoppingCartItem> items;
    }

    public static class ShoppingCartItem {

        public ItemInfo item;
        public int quantity;
        public double discount;
    }

    public static class ItemInfo {

        public String name;
        public double price;
    }

    public User createOrUpdateUser(String name, Date dateOfBirth, boolean isOfLegalAge) {

        if (userExists(name)) {
            User user = findUser(name);
            user.dateOfBirth = dateOfBirth;
            user.isOfLegalAge = isOfLegalAge;
            ShoppingCart existingShoppingCart = null;
            for (ShoppingCart shoppingCart : shoppingCarts) {
                if (shoppingCart.user == user) {
                    existingShoppingCart = shoppingCart;
                }
            }
            if (existingShoppingCart == null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.user = user;
                shoppingCarts.add(shoppingCart);
            }
            users.add(user);
            return user;

        } else {
            User user = new User();
            user.dateOfBirth = dateOfBirth;
            user.nameOfUser = name;
            user.isOfLegalAge = isOfLegalAge;
            users.add(user);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.user = user;
            shoppingCart.items = new ArrayList<>();
            shoppingCarts.add(shoppingCart);
            return user;
        }
    }

    private boolean userExists(String name) {

        User userFound = findUser(name);
        return userFound != null;
    }

    private User findUser(String name) {

        User userFound = null;
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo findItem(String name) {

        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public ShoppingCart findShoppingCart(String user) {

        User userFound = findUser(user);
        ShoppingCart shoppingCartFound = null;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == userFound) {
                shoppingCartFound = shoppingCart;
            }
        }
        return shoppingCartFound;
    }

    public ShoppingCartItem findShoppingCartItem(String user, String itemName){

        ShoppingCart foundShoppingCart = findShoppingCart(user);
        ShoppingCartItem shoppingCartItemFound = null;
        for (ShoppingCartItem shoppingCartItem : foundShoppingCart.items) {
            if (shoppingCartItem.item.name == itemName) {
                shoppingCartItemFound = shoppingCartItem;
            }
        }
        return shoppingCartItemFound;
    }

    public void deleteUser(String name) {

        User userFound = findUser(name);
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    public void addItemToCart(String user, String itemName, int quantity) {

        User userFound = findUser(user);
        ShoppingCartItem shoppingCartItemFound = findShoppingCartItem(user, itemName);

        if (shoppingCartItemFound != null) {
            shoppingCartItemFound.quantity += quantity;

        } else {
            ItemInfo itemFound = findItem(itemName);
            if (itemFound != null) {
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.item = itemFound;
                shoppingCartItem.quantity = quantity;
                int year = Year.now().getValue();
                if (userFound.isOfLegalAge && (year - userFound.dateOfBirth.getYear() < 80)) {
                    shoppingCartItem.discount = 0.2;
                } else {
                    shoppingCartItem.discount = 0.1;
                }
            }
        }
    }

    public void removeItemFromCart(String user, String itemName) {

        ShoppingCart shoppingCartFound = findShoppingCart(user);
        ShoppingCartItem shoppingCartItemFound = findShoppingCartItem(user, itemName);
        if (shoppingCartItemFound != null) {
            shoppingCartFound.items.remove(shoppingCartItemFound);
        }
    }



    public void createItemIfNotExists(String itemName, double price) {

        ItemInfo itemInfo = null;
        for (ItemInfo item : items) {
            if (item.name == itemName) {
                itemInfo = item;
            }
        }

        if (itemInfo == null) {
            ItemInfo itemToCreate = new ItemInfo();
            itemToCreate.name = itemName;
            itemToCreate.price = price;
            items.add(itemToCreate);
        }
    }
}