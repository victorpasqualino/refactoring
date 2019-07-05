package com.celfocus.training;

import com.celfocus.training.util.ItemInfo;
import com.celfocus.training.util.ShoppingCart;
import com.celfocus.training.util.ShoppingCartItem;
import com.celfocus.training.util.User;

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


    public User saveOrUpdateUser(String name, Date birthDay, boolean overEighteen) {
        return existUser(name) ? updateUser(name, birthDay, overEighteen) : saveUser(name, birthDay, overEighteen);
    }

    private User updateUser(String name, Date birthDay, boolean overEighteen) {
        User user = findUser(name);
        user = fillUserInfo(user,birthDay,overEighteen);
        ShoppingCart foundCart = userHasShoppingCart(user);
        if (foundCart != null) {
            users.add(user);
        }else{
            ShoppingCart s = createShoppingCart(user);
            shoppingCarts.add(s);
            users.add(user);
        }
        return user;
    }

    private User saveUser(String name, Date birthDay, boolean overEighteen) {
        User user = new User();
        user.setNameOfUser(name);
        users.add(fillUserInfo(user,birthDay,overEighteen));
        ShoppingCart s = createShoppingCart(user);
        s.setItens(new ArrayList<>());
        shoppingCarts.add(s);
        return user;
    }

    private ShoppingCart userHasShoppingCart(User user){
        ShoppingCart foundCart = null;
        for (ShoppingCart cart : shoppingCarts) {
            if (cart.getUser().equals(user)) {
                foundCart = cart;
            }
        }
        return foundCart;
    }

    private User fillUserInfo(User user, Date birthDate,boolean overeighteen){
        user.setBirthDay(birthDate);
        user.setOverEighteen(overeighteen);
        return user;
    }

    private ShoppingCart createShoppingCart(User user){
        ShoppingCart sc = new ShoppingCart();
        sc.setUser(user);
        return sc;
    }

    private boolean existUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getNameOfUser().equals(name) ) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User findUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getNameOfUser().equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo encontrarItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : itens) {
            if (item.getName().equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getNameOfUser().equals(name)) {
                userFound = user;
            }
        }
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    private ShoppingCartItem findShoppingCartItem(ShoppingCart found, String nameItem) {
        ShoppingCartItem scif = null;
        if (found != null) {
            for (ShoppingCartItem s : found.getItens()) {
                if (s.getItem().getName().equals(nameItem)) {
                    scif = s;
                }
            }
        }
        return scif;
    }

    public void aIU(String user, String nameItem, int qt) {
        User userFound = findUser(user);
        ShoppingCart found = findShoppingCart(userFound);
        ShoppingCartItem scif = findShoppingCartItem(found, nameItem);

        if (scif != null) {

            scif.setQt(scif.getQt() + qt);
        } else {
            ItemInfo ifo = null;
            for (ItemInfo item : itens) {
                if (item.getName().equals(nameItem)) {
                    ifo = item;
                }
            }

            if (ifo != null) {
                ShoppingCartItem s1 = new ShoppingCartItem();
                s1.setItem(ifo);
                s1.setQt(qt);
                if (userFound != null){
                    if (userFound.isOverEighteen() && (new Date().getYear() - userFound.getBirthDay().getYear() < 80)) {
                        s1.setDiscount(0.2);
                    } else if (userFound.isOverEighteen()) {
                        s1.setDiscount(0.1);
                    }
                }

            }

        }
    }
    private ShoppingCart findShoppingCart(User userFound) {
        ShoppingCart found = null;
        for (ShoppingCart var : shoppingCarts) {
            if (var.getUser() == userFound) {
                found = var;
            }
        }
        return found;
    }

    public void rIU(String user, String nameItem) {
        User userFound = findUser(user);

        if (userFound != null) {
            ShoppingCart found = findShoppingCart(userFound);

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.getItens()) {
                    if (s.getItem().getName() == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.getItens().remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : itens){
            if (i.getName() == arg0) {
                f = i;
            }
        }

        if ( f != null ) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.setName(arg0);
            ift.setValor(v);
            itens.add(ift);
        }
    }

} 