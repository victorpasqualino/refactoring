package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e Item
 */
public class Saver {

    private static final List<User> userList = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCartList = new ArrayList<>();
    private static final List<Item> itemList = new ArrayList<>();


    public User saveOrUpdateUser(String username, Date birthDate, boolean isOfAge) {
        return isUser(username) ? updateUser(username, birthDate, isOfAge) : saveUser(username, birthDate, isOfAge);
    }

    private User saveUser(String username, Date birthDate, boolean isOfAge) {
        User user = new User(username, birthDate, isOfAge);
        userList.add(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setItems(new ArrayList<>());
        shoppingCartList.add(shoppingCart);
        return user;
    }

    private User updateUser(String username, Date birthDate, boolean isOfAge) {
        User user = getUserByUsername(username);
        if (user == null) {
            return null;
        }
        user.setBirthDate(birthDate);
        user.setOfAge(isOfAge);

        ShoppingCart found = null;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if (shoppingCart.getUser() == user) {
                found = shoppingCart;
            }
        }

        if (found == null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCartList.add(shoppingCart);
        }
        userList.add(user);
        return user;
    }

    private boolean isUser(String username) {
        boolean userFound = false;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userFound = true;
            }
        }
        return userFound;
    }

    private User getUserByUsername(String username) {
        User userFound = null;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public Item getItem(String itemName) {
        Item itemInfo = null;
        for (Item item : itemList) {
            if (item.getItemName().equals(itemName)) {
                itemInfo = item;
            }
        }
        return itemInfo;
    }

    public void deleteUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userList.remove(user);
            }
        }
    }

    public void aIU(String username, String itemName, int quantity) {
        User userFound = getUserByUsername(username);

        if (userFound != null) {
            ShoppingCart shoppingCartFound = null;
            for (ShoppingCart shoppingCart : shoppingCartList) {
                if (shoppingCart.getUser() == userFound) {
                    shoppingCartFound = shoppingCart;
                }
            }

            if (shoppingCartFound != null) {
                ShoppingCartItem shoppingCartItemFound = null;
                for (ShoppingCartItem shoppingCartItem : shoppingCartFound.getItems()) {
                    if (shoppingCartItem.getItem().getItemName().equals(itemName)) {
                        shoppingCartItemFound = shoppingCartItem;
                    }
                }

                if (shoppingCartItemFound != null) {
                    shoppingCartItemFound.setQuantity(shoppingCartItemFound.getQuantity() + quantity);
                } else {
                    Item itemInfo = null;
                    for (Item item : itemList) {
                        if (item.getItemName().equals(itemName)) {
                            itemInfo = item;
                        }
                    }

                    if (itemInfo != null) {
                        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                        shoppingCartItem.setItem(itemInfo);
                        shoppingCartItem.setQuantity(quantity);
                        if (userFound.isOfAge() && (new Date().getYear() - userFound.getBirthDate().getYear() < 80)) {
                            shoppingCartItem.setDiscount(0.2);
                        } else if (userFound.isOfAge()) {
                            shoppingCartItem.setDiscount(0.1);
                        }
                    }
                }
            }
        }
    }

    public void rIU(String user, String itemName) {
        User userFound = null;
        for (User user1 : userList) {
            if (user1.getUsername().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart shoppingCartFound = null;
            for (ShoppingCart shoppingCart : shoppingCartList) {
                if (shoppingCart.getUser() == userFound) {
                    shoppingCartFound = shoppingCart;
                }
            }

            if (shoppingCartFound != null) {
                ShoppingCartItem shoppingCartItem = null;
                for (ShoppingCartItem s : shoppingCartFound.getItems()) {
                    if (s.getItem().getItemName() == itemName) {
                        shoppingCartItem = s;
                    }
                }

                if (shoppingCartItem != null) {
                    shoppingCartFound.getItems().remove(shoppingCartItem);
                }
            }
        }
    }

    public void citemifnotexists(String itemName, double price) {
        Item item = null;
        for (Item listedItem : itemList){
            if (listedItem.getItemName() == itemName) {
                item = listedItem;
            }
        }

        if (item == null ) {
            item = new Item();
            item.setItemName(itemName);
            item.setPrice(price);
            itemList.add(item);
        }
    }

} 