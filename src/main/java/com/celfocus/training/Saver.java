package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public static class User {

        public String nameOfUser;

        public Date dateOfBirth;

        public boolean isRetired;
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


    public User createNewUser(String name, Date dateOfBirth, boolean isRetired){
        User user = new User();
        user.dateOfBirth = dateOfBirth;
        user.nameOfUser = name;
        user.isRetired = isRetired;
        users.add(user);

        //create a new empty shopping cart associated to the new user
        newShoppingCart(user);

        return user;
    }

    public User updateExistingUser(String name, Date dateOfBirth, boolean isRetired){
        User user = findUser(name);
        user.dateOfBirth = dateOfBirth;
        user.isRetired = isRetired;

        ShoppingCart userShoppingCart = findUserShoppingCart(user);
        if (userShoppingCart == null) {
            newShoppingCart(user);
        }
        users.add(user);
        return user;
    }

    private boolean userExists(String name) {
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
                return true;
            }
        }
        return false;
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

    public void deleteUserIfExists(String name) {
        User user = findUser(name);
        if(user != null){
            users.remove(user);
        }
    }


    public void newShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.user = user;
        shoppingCart.items = new ArrayList<>();
        shoppingCarts.add(shoppingCart);
    }

    public ShoppingCart findUserShoppingCart(User user) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == user) {
                return shoppingCart;
            }
        }
        return null;
    }


    // change this directly in  UserFrontEndRequest.java
    public User saveOrUpdateUser(String name, Date dateOfBirth, boolean isRetired) {
        if (userExists(name)) {
            return updateExistingUser(name, dateOfBirth, isRetired);
        } else {
           return createNewUser(name, dateOfBirth, isRetired);
        }
    }


    // ItemInfo
    public ItemInfo findItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }


    public void aIU(String userName, String nameItem, int quantity) {
        User userFound = findUser(userName);

        if(userFound == null){
            return;
        } else {
            ShoppingCart foundShoppingCart = findUserShoppingCart(userFound);


        }

        if (userFound != null) {
            ShoppingCart foundShoppingCart = findUserShoppingCart(userFound);

            if (foundShoppingCart != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : foundShoppingCart.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    scif.quantity += quantity;
                } else {
                    ItemInfo ifo = findItem(nameItem);

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.quantity = quantity;
                        if ( userFound.isRetired && (new Date().getYear() - userFound.dateOfBirth.getYear() < 80) ) {
                            s1.discount = 0.2; 
                        } else if (userFound.isRetired) {
                            s1.discount = 0.1;
                        }
                    }
                    
                }
            }
        }
    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.nameOfUser.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = findUserShoppingCart(userFound);

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.items.remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : items){
            if (i.name == arg0) {
                f = i;
            }
        }

        if ( f != null ) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.name = arg0;
            ift.price = v;
            items.add(ift);
        }
    }

} 