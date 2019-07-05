package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.Saver;
import com.celfocus.training.Saver.ItemInfo;
import com.celfocus.training.Saver.ShoppingCart;
import com.celfocus.training.Saver.User;
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
             + "<span>" + user.nameOfUser + "</span>"
             + "<span>" + user.dateOfBirth + "</span>"
             + "<span>" + user.isOfLegalAge + "</span>"
             + "</div>";

        } else if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.nameOfUser + "</name>"
                    + "<dateOfBirth>" + user.dateOfBirth + "</dateOfBirth>"
                    + "<older> " + user.isOfLegalAge + "</older>";

        } else {
            return "";
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
             + "<span> " + shoppingCart.items + "</span>"
             + "</div>";

        } else if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<items> " + shoppingCart.items + "</items>";

        } else {
            return "";
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
             + "<span> " + item.name + "</span>"
             + "<span> " + item.price + "</span>"
             + "</div>";

        } else if (type.equals("xml")) {
                return "<name> " + item.name + "</name>"
                    + "<price> " + item.price + "</price>";

        } else {
            return "";
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param nameOfUser
     * @param dateOfBirth
     * @param isOfLegalAge
     */
    public void createOrUpdateUser(String nameOfUser, String dateOfBirth, String isOfLegalAge) {

        Saver saver = new Saver();
        nameOfUser = nameOfUser.toUpperCase();
        Date date = Utils.toDate(dateOfBirth, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - date.getYear() < 18) {
            isOfLegalAge = "false";
        }
        saver.createOrUpdateUser(nameOfUser, date, isOfLegalAge.equals("true") ? true : false);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String user) {

        Saver saver = new Saver();
        saver.deleteUser(user);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addItemToShoppingCart(String user, String itemName, int quantity) {

        Saver saver = new Saver();
        itemName = itemName.toLowerCase().concat("_item");
        saver.addItemToCart(user, itemName, quantity);
    }

}