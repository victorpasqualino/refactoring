package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.celfocus.training.util.*;



/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver  {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> listShoppingCarts = new ArrayList<>();
    private static final List<Item> itens = new ArrayList<>();





    public User saveOrUpdateUser(String name, Date birthday, boolean isovereighteen)  {

        User user = getUserFromList(name);
        if (user != null) {

            user.setBirthDate(birthday);
            user.setOlder(isovereighteen);
            ShoppingCart found = null;
            for (ShoppingCart shoppingList : listShoppingCarts) {
                if (shoppingList.getUser() == user) {
                    found = shoppingList;
                }
            }

            if (found == null) {

                ShoppingCart shoppingCartNew = new ShoppingCart();
                shoppingCartNew.setUser(user);
                listShoppingCarts.add(shoppingCartNew);
            }
            users.add(user);

        } else {
            updateUser(name, birthday, isovereighteen);

        }


        return user;
    }

    public User updateUser(String name, Date birthday, boolean isovereighteen) {
        User user = new User();
        user.setBirthDate(birthday);
        user.setName(name);
        user.setOlder(isovereighteen);
        users.add(user);
        ShoppingCart shoppingCartNew = new ShoppingCart();
        shoppingCartNew.setUser(user);
        shoppingCartNew.setListItems(new ArrayList<>());
        listShoppingCarts.add(shoppingCartNew);
        return user;
    }



    public Item encontrarItem(String name) {
        Item itemFound = null;
        for (Item item : itens) {
            if (item.getName().equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
                users.remove(userFound);
            }
        }

    }

    public void aIU(String user, String nameItem, int qt) {
        User userFound = getUserFromList(user);

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart shoppingcart : listShoppingCarts) {
                if (shoppingcart.getUser() == userFound) {
                    found = shoppingcart;
                }
            }

            if (found != null) {
                ShoppingCartItem shoppingCartFoundItem = null;
                shoppingCartFoundItem = getShoppingCartItemFromList(nameItem, found, shoppingCartFoundItem);

                if (shoppingCartFoundItem != null) {
                    shoppingCartFoundItem.setQuantity( shoppingCartFoundItem.getQuantity() + qt);




                } else {
                    Item itemInformation = null;
                    for (Item item : itens) {
                        if (item.getName().equals(nameItem)) {
                            itemInformation = item;
                        }
                    }

                    if (itemInformation != null) {
                        ShoppingCartItem shoppingcartitem1 = new ShoppingCartItem();
                        shoppingcartitem1.setItem(itemInformation);
                        shoppingcartitem1.setQuantity(qt);
                        if ( userFound.isOlder()  && (new Date().getYear() - userFound.getBirthDate().getYear() < 80) ) {
                            shoppingcartitem1.setDiscount(0.2);
                        } else if (userFound.isOlder() ) {
                            shoppingcartitem1.setDiscount(0.1);
                        }
                    }
                    
                }
            }
        }
    }

    private ShoppingCartItem getShoppingCartItemFromList(String nameItem, ShoppingCart found, ShoppingCartItem shoppingCartFoundItem) {
        for (ShoppingCartItem shoppingcartitem : found.getListItems()) {
            if (shoppingcartitem.getItem().getName().equals(nameItem)) {
                return shoppingcartitem;
            }
        }
        return null;
    }

    private User getUserFromList(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.getName().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : listShoppingCarts) {
                if (var.getUser() == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                scif = getShoppingCartItemFromList(nameItem, found, scif);

                if (scif != null) {
                    found.getListItems().remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        Item f = null;
        for (Item i : itens){
            if (i.getName() == arg0) {
                f = i;
            }
        }

        if ( f != null ) {

        } else {
            Item ift = new Item();
            ift.setName(arg0);
            ift.setValue(v);
            itens.add(ift);
        }
    }

}