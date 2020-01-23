package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.controllers.ShoppingCartController;
import com.celfocus.training.controllers.UserController;
import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.userName + "</span>"
             + "<span>" + user.birthDay + "</span>"
             + "<span>" + user.isAdult + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.userName + "</name>"
                    + "<bd>" + user.birthDay + "</bd>"
                    + "<older> " + user.isAdult + "</older>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.user + "</span>"
             + "<span> " + shoppingCart.shoppingCartItems + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<itens> " + shoppingCart.shoppingCartItems + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.itemName + "</span>"
             + "<span> " + item.value + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.itemName + "</name>" + "<valor> " + item.value + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param userName
     * @param unformattedDate
     * @param isAdultString
     */
    public void createOrUpdateUser(String userName, String unformattedDate, String isAdultString) {
        UserController userController = new UserController();

        userName = userName.toUpperCase();

        Date date = Utils.toDate(unformattedDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - date.getYear() < 65) {
            isAdultString = "false";
        }

        userController.createOrUpdateUser(userName, date, Boolean.getBoolean(isAdultString));
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String userName) {
        UserController userController = new UserController();
        userController.deleteUser(userName);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addItemToShoppingCart(String userName, String itemName, int quantity) {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        itemName = itemName.toLowerCase().concat("_item");
        shoppingCartController.saveItemToShoppingCart(userName, itemName, quantity);
    }

}