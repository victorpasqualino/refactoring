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
        if (existUser(name)) {
            //updtateUser(name);
            User user = findUser(name);
            user = fillUserInfo(user,birthDay,overEighteen);
            ShoppingCart foundCart = userHasShoppingCart(user);
            if (foundCart != null) {
                users.add(user);
                return user;
            }else{
                ShoppingCart s = createShoppingCart(user);
                shoppingCarts.add(s);
                users.add(user);
                return user;
            }
        } else {
            //saveUser(name);
            User user = new User();
            user.setNameOfUser(name);
            users.add(fillUserInfo(user,birthDay,overEighteen));
            ShoppingCart s = createShoppingCart(user);
            s.setItens(new ArrayList<>());
            shoppingCarts.add(s);
            return user;
        }
    }

    private ShoppingCart userHasShoppingCart(User user){
        ShoppingCart foundCart = null;
        for (ShoppingCart cart : shoppingCarts) {
            if (cart.getUser() == user) {
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
        if (userFound == null) {
        } else {
            users.remove(userFound);
        }
    }

    public void aIU(String user, String nameItem, int qt) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.getNameOfUser().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser() == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.getItens()) {
                    if (s.getItem().getName() == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {

                    scif.setQt(scif.getQt()+qt);
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
                        if ( userFound.isOverEighteen() && (new Date().getYear() - userFound.getBirthDay().getYear() < 80) ) {
                            s1.setDiscount(0.2 );
                        } else if (userFound.isOverEighteen()) {
                            s1.setDiscount(0.1);
                        }
                    } else {

                    }
                    
                }
            }
        }
    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.getNameOfUser().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser() == userFound) {
                    found = var;
                }
            }

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