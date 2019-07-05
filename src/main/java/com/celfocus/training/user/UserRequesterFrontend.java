package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    private static final String DIV_OPEN = "<div>";
    private static final String DIV_CLOSE = "</div>";
    private static final String SPAN_OPEN = "<span>";
    private static final String SPAN_CLOSE = "</span>";

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {

        if (type.equals("html")) {
            return DIV_OPEN
             + "<h1>User</h1>"
             + SPAN_OPEN + user.nameOfUser + SPAN_CLOSE
             + SPAN_OPEN + user.dateOfBirth + SPAN_CLOSE
             + SPAN_OPEN + user.isOfLegalAge + SPAN_CLOSE
             + DIV_CLOSE;

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
            return DIV_OPEN
             + "<h1>ShoppingCart</h1>"
             + SPAN_OPEN + shoppingCart.user + SPAN_CLOSE
             + SPAN_OPEN + shoppingCart.items + SPAN_CLOSE
             + DIV_CLOSE;

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
            return DIV_OPEN
             + "<h1>Item</h1>"
             + SPAN_OPEN + item.name + SPAN_CLOSE
             + SPAN_OPEN + item.price + SPAN_CLOSE
             + DIV_CLOSE;

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
        Date birthDate = Utils.toDate(dateOfBirth, new SimpleDateFormat("yyyy"));
        Calendar calendar = new Calendar;
        if (calendar.get(Calendar.YEAR) - birthDate.getYear() < 18) {
            isOfLegalAge = "false";
        }
        saver.createOrUpdateUser(nameOfUser, birthDate, isOfLegalAge.equals("true") ? true : false);
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