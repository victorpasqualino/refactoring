package com.celfocus.training.user;


import com.celfocus.training.ItemInfo;
import com.celfocus.training.ShoppingCart;


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
					+ "<span>" + user.getNameOfUser() + "</span>"
					+ "<span>" + user.getBirthDate() + "</span>"
					+ "<span>" + user.getIfUserOlder() + "</span>"
					+ "</div>";
		} else {
			if (type.equals("xml")) {
				return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
						+ "<name> " + user.getNameOfUser() + "</name>"
						+ "<bd>" + user.getBirthDate() + "</bd>"
						+ "<older> " + user.getIfUserOlder() + "</older>";
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
					+ "<span> " + shoppingCart.getUser() + "</span>"
					+ "<span> " + ShoppingCart.getShoppingCartlistItem() + "</span>"
					+ "</div>";
		} else {
			if (type.equals("xml")) {
				return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
						+ "<user> " + shoppingCart.getUser() + "</user>"
						+ "<itens> " + ShoppingCart.getShoppingCartlistItem() + "</itens>";
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
					+ "<span> " + item.getName()+ "</span>"
					+ "<span> " + item.getValor() + "</span>"
					+ "</div>";
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
}