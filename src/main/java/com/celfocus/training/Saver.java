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


    public User saveOrUpdateUser(String username, Date birthDate, boolean isSenior) {
        return isUser(username) ? updateUser(username, birthDate, isSenior) : saveUser(username, birthDate, isSenior);
    }

    private User saveUser(String username, Date birthDate, boolean isSenior) {
        User user = new User(username, birthDate, isSenior);
        userList.add(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setItems(new ArrayList<>());
        shoppingCartList.add(shoppingCart);
        return user;
    }

    private User updateUser(String username, Date birthDate, boolean isSenior) {
        User user = getUserByUsername(username);
        if (user == null) {
            return null;
        }
        user.setBirthDate(birthDate);
        user.setSenior(isSenior);

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

    private ShoppingCart findShoppingCartByUser(User user) {
        ShoppingCart shoppingCartFound = null;

        if (user != null) {
            for (ShoppingCart shoppingCart : shoppingCartList) {
                if (shoppingCart.getUser() == user) {
                    shoppingCartFound = shoppingCart;
                }
            }
        }
        return shoppingCartFound;
    }

    public ShoppingCartItem findShoppingCartItem(ShoppingCart shoppingCartFound, String itemName) {
        if (shoppingCartFound != null) {
            ShoppingCartItem shoppingCartItemFound = null;
            for (ShoppingCartItem shoppingCartItem : shoppingCartFound.getItems()) {
                if (shoppingCartItem.getItem().getItemName().equals(itemName)) {
                    shoppingCartItemFound = shoppingCartItem;
                }
            }
        }
        return shoppingCartItemFound
    }

    public void addShoppingItemToUserCart(String username, String itemName, int quantity) {
        User userFound = getUserByUsername(username);
        ShoppingCart shoppingCartFound = findShoppingCartByUser(userFound);
        ShoppingCartItem shoppingCartItemFound = findShoppingCartItem(shoppingCartFound, itemName);



            if (shoppingCartItemFound != null) {
                shoppingCartItemFound.setQuantity(shoppingCartItemFound.getQuantity() + quantity);
            } else {
                Item itemFound = null;
                for (Item item : itemList) {
                    if (item.getItemName().equals(itemName)) {
                        itemFound = item;
                    }
                }

                if (itemFound != null) {
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setItem(itemFound);
                    shoppingCartItem.setQuantity(quantity);
                    if (userFound.isSenior() && (new Date().getYear() - userFound.getBirthDate().getYear() < 80)) {
                        shoppingCartItem.setDiscount(0.2);
                    } else if (userFound.isSenior()) {
                        shoppingCartItem.setDiscount(0.1);
                    }
                }
            }
        }

    }

    public void removeShoppingCartItem(String user, String itemName) {
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