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

    private static final String DIV = "<div>";
    private static final String CDIV = "</div>";
    private static final String SPAN = "<span>";
    private static final String CSPAN = "</span>";
    private static final String H1 = "<h1>";
    private static final String CH1 = "</h1>";

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return DIV
             + H1 + "User" + CH1
             + SPAN + user.nameOfUser + CSPAN
             + SPAN + user.bd + CSPAN
             + SPAN + user.ifuserisolder + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.nameOfUser + "</name>"
                    + "<bd>" + user.bd + "</bd>"
                    + "<older> " + user.ifuserisolder + "</older>";
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
            return DIV
             + H1 + "ShoppingCart" + CH1
             + SPAN + shoppingCart.user + CSPAN
             + SPAN + shoppingCart.itens + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<itens> " + shoppingCart.itens + "</itens>";
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
            return DIV
             + H1 + "Item" + CH1
             + SPAN + item.name + CSPAN
             + SPAN + item.valor + CSPAN
             + CDIV;
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
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public void createOrUpdateUser(String arg0, String arg1, String arg2) {
        Saver saver = new Saver();

        arg0 = arg0.toUpperCase();

        Date d = Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            arg2 = "false";
        }

        saver.saveOrUpdateUser(arg0, Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy")), arg2.equals("true") ? true : false);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        Saver saver = new Saver();
        saver.deleteUserOrNot(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String user, String nameItem, int qt) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.aIU(user, nameItem, qt);
    }

}