package com.celfocus.training.user;

import com.celfocus.training.Saver;
import com.celfocus.training.util.ItemInfo;
import com.celfocus.training.util.ShoppingCart;
import com.celfocus.training.util.User;
import com.celfocus.training.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
             + SPAN + user.getNameOfUser() + CSPAN
             + SPAN + user.getBirthDay() + CSPAN
             + SPAN + user.isOverEighteen() + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getNameOfUser() + "</name>"
                    + "<bd>" + user.getBirthDay() + "</bd>"
                    + "<older> " + user.isOverEighteen() + "</older>";
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
             + SPAN + shoppingCart.getUser() + CSPAN
             + SPAN + shoppingCart.getItens() + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItens() + "</itens>";
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
             + SPAN + item.getName() + CSPAN
             + SPAN + item.getValor() + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.getName() + "</name>"
                    + "<valor> " + item.getValor() + "</valor>";
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