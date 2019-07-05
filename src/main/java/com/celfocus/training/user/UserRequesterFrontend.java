package com.celfocus.training.user;


import com.celfocus.training.ItemInfo;
import com.celfocus.training.ShoppingCart;


/**
 * User For Frontent
 */
public class UserRequesterFrontend {
	
	private String openSpan = "<span> ";
	private String closeSpan = "</span>";
	private String openDiv = "<div>";
	private String closeDiv = "</div>";
	
	/**
	 * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
	 * @param type tipo do frontend utilizado
	 * @param user usuario que será renderizado
	 * @return o texto no formato solicitado com as informarções do user
	 */
	public String returnFrontendUser(String type, User user) {
		if (type.equals("html")) {
			return openDiv
					+ "<h1>User</h1>"
					+ openSpan + user.getNameOfUser() + closeSpan
					+ openSpan + user.getBirthDate() + closeSpan
					+ openSpan + user.getIfUserOlder() + closeSpan
					+ closeDiv;
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
			return openDiv
					+ "<h1>ShoppingCart</h1>"
					+ openSpan + shoppingCart.getUser() + closeSpan
					+ openSpan + ShoppingCart.getShoppingCartlistItem() + closeSpan
					+ closeDiv;
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
					+  openSpan + item.getName()+ closeSpan
					+  openSpan + item.getValor() + closeSpan
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