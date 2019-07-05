package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.Saver;
import com.celfocus.training.ItemInfo;
import com.celfocus.training.ShoppingCart;
import com.celfocus.training.User;
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
             + "<span>" + user.isRetired + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.nameOfUser + "</name>"
                    + "<dateOfBirth>" + user.dateOfBirth + "</dateOfBirth>"
                    + "<older> " + user.isRetired + "</older>";
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
             + "<span> " + shoppingCart.items + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<items> " + shoppingCart.items + "</items>";
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
             + "<span> " + item.name + "</span>"
             + "<span> " + item.price + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.name + "</name>"
                    + "<price> " + item.price + "</price>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    // Create or update user
    public String formatName(String name){
        return name.toUpperCase();
    }

    public Date formatDate(String dateString){
        return Utils.toDate(dateString, new SimpleDateFormat("dd/mm/yyyy"));
    }

    int retirementAge = 65;
    public boolean isUserRetired(Date birhDate){
        return (new Date().getYear() - birhDate.getYear() > retirementAge);
    }

    public void createUser(String userName, String birthDate){
        Saver saver = new Saver();

        saver.createNewUser(formatName(userName), formatDate(birthDate), isUserRetired(formatDate(birthDate)));
    }

    public void updateUser(String userName, String birthDate){
        Saver saver = new Saver();

        saver.updateExistingUser(formatName(userName), formatDate(birthDate), isUserRetired(formatDate(birthDate)));
    }

    // Remove user
    public void deleteUser(String userName) {
        Saver saver = new Saver();
        saver.deleteUserIfExists(userName);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String user, String nameItem, int qt) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addItemToShoppingCart(user, nameItem, qt);
    }

}