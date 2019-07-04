package com.celfocus.training;

import com.celfocus.training.entity.Item;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final int MAX_AGE = 80;

    private final List<User> users = new ArrayList<>();
    private final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private final List<Item> itens = new ArrayList<>();

    public void createOrUpdateUser(User user) {
        User foundUser = getUserFromList(user);

        if (foundUser != null) {
            foundUser.setBirthDate(user.getBirthDate());
            foundUser.setOlder(user.isOlder());

            updateUser(foundUser);
        } else {
            User newUser = new User();
            newUser.setBirthDate(user.getBirthDate());
            newUser.setName(user.getName());
            newUser.setOlder(user.isOlder());

            createUser(newUser);
        }
    }

    private void updateUser(User user) {
        ShoppingCart foundCart = getShoppingCartFromList(user);

        if (foundCart == null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCarts.add(shoppingCart);
        }

        users.add(user);
    }

    private void createUser(User newUser) {
        users.add(newUser);

        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setUser(newUser);
        newShoppingCart.setItens(new ArrayList<>());

        shoppingCarts.add(newShoppingCart);
    }

    private ShoppingCart getShoppingCartFromList(User searchUser) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.getUser().equals(searchUser)) {
                return shoppingCart;
            }
        }
        return null;
    }

    private User getUserFromList(User searchUser) {
        for (User user : users) {
            if (user.getName().equals(searchUser.getName())) {
                return user;
            }
        }
        return null;
    }

    private ShoppingCartItem getShoppingCartItemFromCart(ShoppingCart searchCart, Item searchItem) {
        for (ShoppingCartItem item : searchCart.getItens()) {
            if (item.getItem().getName().equals(searchItem.getName())) {
                return item;
            }
        }
        return null;
    }

    private Item getItemFromList(Item searchItem) {
        for (Item item : itens) {
            if (item.getName().equals(searchItem.getName())) {
                return item;
            }
        }
        return null;
    }

    public void deleteUser(User user) {
        User foundUser = getUserFromList(user);

        if (foundUser != null) {
            users.remove(foundUser);
        }
    }

    public void addItemToShoppingCart(String userName, String itemName, int quantity) {
        User user = new User();
        user.setName(userName);

        User foundUser = getUserFromList(user);

        if (foundUser != null) {
            ShoppingCart foundCart = getShoppingCartFromList(foundUser);

            if (foundCart != null) {
                Item item = new Item();
                item.setName(itemName);

                ShoppingCartItem foundShoppingCardItem = getShoppingCartItemFromCart(foundCart, item);

                if (foundShoppingCardItem == null) {
                    Item foundItem = getItemFromList(item);

                    if (foundItem != null) {
                        ShoppingCartItem newShoppingCartItem = new ShoppingCartItem();
                        newShoppingCartItem.setItem(foundItem);
                        newShoppingCartItem.setQuantity(quantity);

                        if (foundUser.isOlder() && foundUser.hasLessThan(MAX_AGE)) {
                            newShoppingCartItem.setDiscount(0.2);
                        } else if (!foundUser.hasLessThan(MAX_AGE)) {
                            newShoppingCartItem.setDiscount(0.1);
                        }

                        foundCart.getItens().add(newShoppingCartItem);
                    }
                } else {
                    foundShoppingCardItem.setQuantity(foundShoppingCardItem.getQuantity() + quantity);
                }
            }
        }
    }

    public void addItemToList(String itemName, double itemValue) {
        Item item = new Item();
        item.setName(itemName);
        item.setValue(itemValue);

        Item foundItem = getItemFromList(item);
        if (foundItem == null) {
            itens.add(item);
        }
    }

    public void removeItemFromUserCart(String userName, String itemName) {
        User user = new User();
        user.setName(userName);

        User foundUser = getUserFromList(user);
        if (foundUser != null) {
            ShoppingCart foundCart = getShoppingCartFromList(foundUser);

            if (foundCart != null) {
                Item item = new Item();
                item.setName(itemName);

                ShoppingCartItem foundShoppingCardItem = getShoppingCartItemFromCart(foundCart, item);

                if (foundShoppingCardItem != null) {
                    foundCart.getItens().remove(foundShoppingCardItem);
                }
            }
        }
    }
}