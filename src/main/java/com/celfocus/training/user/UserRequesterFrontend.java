package com.celfocus.training.user;

import com.celfocus.training.Item;
import com.celfocus.training.Saver;
import com.celfocus.training.ShoppingCart;
import com.celfocus.training.User;
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
    private Saver saver = new Saver();

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
             + SPAN + user.getUsername() + CSPAN
             + SPAN + user.getBirthDate() + CSPAN
             + SPAN + user.isSenior() + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getUsername() + "</name>"
                    + "<bd>" + user.getBirthDate() + "</bd>"
                    + "<older> " + user.isSenior() + "</older>";
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
             + SPAN + shoppingCart.getItems() + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItems() + "</itens>";
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
    public String returnFrontendItem(String type, Item item) {
        if (type.equals("html")) {
            return DIV
             + H1 + "Item" + CH1
             + SPAN + item.getItemName() + CSPAN
             + SPAN + item.getPrice() + CSPAN
             + CDIV;
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.getItemName() + "</name>"
                    + "<valor> " + item.getPrice() + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param username
     * @param unformattedDate
     * @param isSenior
     */
    public void createOrUpdateUser(String username, String unformattedDate, boolean isSenior) {
        Date date = Utils.toDate(unformattedDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - date.getYear() < 65) {
            isSenior = false;
        }
        saver.saveOrUpdateUser(username.toUpperCase(), date, isSenior);
    }

    /**
     * Remove User
     */
    public void deleteUser(String username) {
        saver.deleteUser(username);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addShoppingItem(String user, String nameItem, int qt) {
        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addShoppingItemToUserCart(user, nameItem, qt);
    }

}