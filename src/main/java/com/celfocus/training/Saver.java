package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> itens = new ArrayList<>();

    public static class User {

        public String name;

        public Date birthDate;

        public boolean isAdult;

    }

    public static class ShoppingCart {

        public User user;

        public List<ShoppingCartItem> itemsList;
    }

    public static class ShoppingCartItem {

        public ItemInfo itemInfo;

        public int quantity;

        public double discount;

    }

    public static class ItemInfo {

        public String name;

        public double valor;
    }

    public User save(String name, Date date, boolean isAdult) {

        User user = findUsers(name);
        user.birthDate = date;
        user.isAdult = isAdult;
        ShoppingCart found = null;
        for (ShoppingCart var : shoppingCarts) {
            if (var.user == user) {
                found = var;
            }
        }
        if (found != null) {
            //do nothing
        } else {
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            shoppingCarts.add(s);
        }
        users.add(user);
        return user;
    }

    public User update(String name, Date birth_date, boolean isAdult) {

        User user = new User();
        user.birthDate = birth_date;
        user.name = name;
        user.isAdult = isAdult;
        users.add(user);
        ShoppingCart s = new ShoppingCart();
        s.user = user;
        s.itemsList = new ArrayList<>();
        shoppingCarts.add(s);
        return user;
    }

    private boolean userExists(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.name.equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    public User findUsers(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.name.equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo findItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : itens) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void delete(String name) {
        User userFound = findUsers(name);

        if (userFound == null) {
        } else {
            users.remove(userFound);
        }
    }

    public ShoppingCart findShoppingCart(User userFound) {
        ShoppingCart found = null;
        for (ShoppingCart var : shoppingCarts) {
            if (var.user == userFound) {
                found = var;
            }
        }
        return found;
    }

    public ShoppingCartItem shoppingCartItem(ShoppingCart shoppingCartFound, String nameItem) {
        ShoppingCartItem shoppingCartItem = null;
        for (ShoppingCartItem s : shoppingCartFound.itemsList) {
            if (s.itemInfo.name == nameItem) {
                shoppingCartItem = s;
            }
        }
        return shoppingCartItem;
    }

    public boolean canAddToCart(User user, ShoppingCart shoppingCart, ShoppingCartItem shoppingCartItem) {
        return user != null && shoppingCart != null && shoppingCartItem != null;
    }

    public void addItemToCart(String user, String nameItem, int quantity) {
        User userFound = findUsers(user);
        ShoppingCart shoppingCart = findShoppingCart(userFound);
        ShoppingCartItem shoppingCartItem = shoppingCartItem(shoppingCart, nameItem);
        if (canAddToCart(userFound, shoppingCart, shoppingCartItem)) {
            shoppingCartItem.quantity += quantity;
        } else {
            ItemInfo itemInfo = findItem(nameItem);

            if (itemInfo != null) {
                ShoppingCartItem s1 = new ShoppingCartItem();
                s1.itemInfo = itemInfo;
                s1.quantity = quantity;
                if (userFound.isAdult == true && (new Date().getTime() - userFound.birthDate.getTime() < 80)) {
                    s1.discount = 0.2;
                } else if (userFound.isAdult == true) {
                    s1.discount = 0.1;
                }
            }
        }
    }


    public void removeItemFromCart(String user, String nameItem) {
        User userFound = findUsers(user);
        ShoppingCart shoppingCart = findShoppingCart(userFound);
        ShoppingCartItem shoppingCartItem = shoppingCartItem(shoppingCart, nameItem);

        if (canAddToCart(userFound, shoppingCart, shoppingCartItem)) {
            shoppingCart.itemsList.remove(shoppingCartItem);

        }


    }

    public void createItem(String itemName, double v) {
        ItemInfo itemInfo = findItem(itemName);

        if (itemInfo == null) {
            ItemInfo newItemInfo = new ItemInfo();
            newItemInfo.name = itemName;
            newItemInfo.valor = v;
            itens.add(newItemInfo);
        }
    }
}