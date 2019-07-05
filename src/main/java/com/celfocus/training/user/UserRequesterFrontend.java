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
             + "<span>" + user.name + "</span>"
             + "<span>" + user.birthDate + "</span>"
             + "<span>" + user.isAdult + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.name + "</name>"
                    + "<birthDate>" + user.birthDate + "</birthDate>"
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
             + "<span> " + shoppingCart.itemsList + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<itemsList> " + shoppingCart.itemsList + "</itemsList>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item itemInfo que será renderizado
     * @return o texto no formato solicitado com as informarções do itemInfo
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.name + "</span>"
             + "<span> " + item.valor + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.name + "</name>"
                    + "<valor> " + item.valor + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param name
     * @param date
     * @param argumento
     */
    public void createOrUpdateUser(String name, String date, String argumento) {
        Saver saver = new Saver();

        name = name.toUpperCase();

        Date d = Utils.toDate(date, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getTime() - d.getTime() < 65) {
            argumento = "false";
        }

        saver.save(name, Utils.toDate(date, new SimpleDateFormat("dd/mm/yyyy")), argumento.equals("true") ? true : false);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String name) {
        Saver saver = new Saver();
        saver.delete(name);
    }

    /**
     * Adicionar itemInfo ao carrinho
     */
    public void addditemToCart(String user, String nameItem, int quantity) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addItemToCart(user, nameItem, quantity);
    }

}