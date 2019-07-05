package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.celfocus.training.util.User;


/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver  {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> listShoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> itens = new ArrayList<>();



    public static class ShoppingCartItem {

        public Saver.ItemInfo item;

        public int quantity;

        public double discount;
    }

/**pus tudo como static por causa da classe user que criei ---> duvida */
    public static class ShoppingCart {

        public User user;

        public List<Saver.ShoppingCartItem> listItems;
    }

    public static class ItemInfo {

        public String name;

        public double valor;
    }

    public User saveOrUpdateUser(String name, Date birthday, boolean isovereighteen)  {

        User user = foundUserName(name);
        if (userExists(name)) {


            user.birthDay = birthday;
            user.isOverEighteen = isovereighteen;
            ShoppingCart found = null;
            for (ShoppingCart shoppingList : listShoppingCarts) {
                if (shoppingList.user == user) {
                    found = shoppingList;
                }
            }

            if (found != null) {
                //do nothing
            } else {
                ShoppingCart shoppingCartNew = new ShoppingCart();
                shoppingCartNew.user = user;
                listShoppingCarts.add(shoppingCartNew);
            }
            users.add(user);

        }
        return user;
    }

    public User updateUser(String name, Date birthday, boolean isovereighteen) {
        User user = new User();
        user.birthDay = birthday;
        user.nameOfUser = name;
        user.isOverEighteen = isovereighteen;
        users.add(user);
        ShoppingCart shoppingCartNew = new ShoppingCart();
        shoppingCartNew.user = user;
        shoppingCartNew.listItems = new ArrayList<>();
        listShoppingCarts.add(shoppingCartNew);
        return user;
    }

    private boolean userExists(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User foundUserName(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
                userFound = user;
            }
        }
        return userFound;
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
                users.remove(userFound);
            }
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
            for (ShoppingCart shoppingcart : listShoppingCarts) {
                if (shoppingcart.user == userFound) {
                    found = shoppingcart;
                }
            }

            if (found != null) {
                ShoppingCartItem shoppingCartFoundItem = null;
                for (ShoppingCartItem shoppingcartitem : found.listItems) {
                    if (shoppingcartitem.item.name == nameItem) {
                        shoppingCartFoundItem = shoppingcartitem;
                    }
                }

                if (shoppingCartFoundItem != null) {
                    shoppingCartFoundItem.quantity += qt;
                } else {
                    ItemInfo itemInformation = null;
                    for (ItemInfo item : itens) {
                        if (item.name.equals(nameItem)) {
                            itemInformation = item;
                        }
                    }

                    if (itemInformation != null) {
                        ShoppingCartItem shoppingcartitem1 = new ShoppingCartItem();
                        shoppingcartitem1.item = itemInformation;
                        shoppingcartitem1.quantity = qt;
                        if ( userFound.isOverEighteen  && (new Date().getYear() - userFound.birthDay.getYear() < 80) ) {
                            shoppingcartitem1.discount = 0.2;
                        } else if (userFound.isOverEighteen ) {
                            shoppingcartitem1.discount = 0.1;
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
            ShoppingCart found = null;
            for (ShoppingCart var : listShoppingCarts) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.listItems) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.listItems.remove(scif);
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