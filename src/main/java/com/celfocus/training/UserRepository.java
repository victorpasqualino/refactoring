package com.celfocus.training;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repository {

    private final List<User> users = new ArrayList<>();
    private final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private final List<ItemInfo> itens = new ArrayList<>();

    public void createUser() {

    }

    public void updateUser() {

    }

    //todo - fundir os 2 métodos seguintes
    public boolean existUser(User userToSearch) {
        for (User user : users) {
            if (user.getName().equals(userToSearch.getName())) {
                return true;
            }
        }
        return false;
    }

    private User getUserFromList(User userToSearch) {
        for (User user : users) {
            if (user.getName().equals(userToSearch.getName())) {
                return user;
            }
        }
        return null;
    }

    //todo - este método vai deixar de existir; criar métodos mais pequenos em que só têm um objetivo
    //public User createOrUpdateUser(String name, Date bd, boolean ifuserisolder) {
    public User createOrUpdateUser(User user) {
        if (existUser(user)) {
            User foundUser = getUserFromList(user);
            foundUser.setBirthDate(user.getBirthDate());
            foundUser.setOlder(user.isOlder());

            ShoppingCart foundCart = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser().equals(user)) {
                    foundCart = var;
                }
            }

            if (foundCart == null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setUser(user);
                shoppingCarts.add(shoppingCart);
            }

            users.add(user);
            return user;
        }

        return null;
    }



    public ItemInfo encontrarItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : itens) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
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
            if (user1.nameOfUser.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.itens) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    scif.qt += qt;
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : itens) {
                        if (item.name.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.qt = qt;
                        if ( userFound.ifuserisolder
                 == true && (new Date().getYear() - userFound.bd.getYear() < 80) ) {
                            s1.discount = 0.2; 
                        } else if (userFound.ifuserisolder
                 == true) {
                            s1.discount = 0.1;
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
            if (user1.nameOfUser.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.itens) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.itens.remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : itens){
            if (i.name == arg0) {
                f = i;
            }
        }

        if ( f != null ) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.name = arg0;
            ift.valor = v;
            itens.add(ift);
        }
    }

} 