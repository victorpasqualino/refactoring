package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.domain.ItemInfo;
import com.celfocus.domain.ShoppingCart;
import com.celfocus.domain.User;
import com.celfocus.training.Saver;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend implements IItemRequestFrontend, IShoppingCartRequestFrontend, IUserRequestFrontend {

	enum formatTypes {
		html, xml
	}

	/**
	 * Metodo utilizado para retornar o Usuario no formato do frontend
	 * solicitado
	 * 
	 * @param type
	 *            tipo do frontend utilizado
	 * @param user
	 *            usuario que será renderizado
	 * @return o texto no formato solicitado com as informarções do user
	 */
	public String returnFrontendUser(String type, User user) {
		if (type.equals(formatTypes.html)) {
			return "<div>" + "<h1>User</h1>" + "<span>" + user.getName() + "</span>" + "<span>" + user.getBirthDate()
					+ "</span>" + "<span>" + user.isOfAge() + "</span>" + "</div>";
		} else if (type.equals(formatTypes.xml)) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>" + "<name> " + user.getName()
					+ "</name>" + "<bd>" + user.getBirthDate() + "</bd>" + "<older> " + user.isOfAge() + "</older>";
		} else {
			// do nothing
			return "";
		}

	}

	/**
	 * Metodo utilizado para retornar o Shoppingcart no formato do frontend
	 * solicitado
	 * 
	 * @param type
	 *            tipo do frontend utilizado
	 * @param shoppingCart
	 *            shoppingCart que será renderizado
	 * @return o texto no formato solicitado com as informarções do shoppingCart
	 */
	public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
		if (type.equals(formatTypes.html)) {
			return "<div>" + "<h1>ShoppingCart</h1>" + "<span> " + shoppingCart.getUser() + "</span>" + "<span> "
					+ shoppingCart.getItens() + "</span>" + "</div>";
		} else if (type.equals(formatTypes.xml)) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>" + "<user> " + shoppingCart.getUser()
					+ "</user>" + "<itens> " + shoppingCart.getItens() + "</itens>";
		} else {
			// do nothing
			return "";
		}

	}

	/**
	 * Metodo utilizado para retornar o Item no formato do frontend solicitado
	 * 
	 * @param type
	 *            tipo do frontend utilizado
	 * @param item
	 *            item que será renderizado
	 * @return o texto no formato solicitado com as informarções do item
	 */
	public String returnFrontendItem(String type, ItemInfo item) {
		if (type.equals(formatTypes.html)) {
			return "<div>" + "<h1>Item</h1>" + "<span> " + item.getName() + "</span>" + "<span> " + item.getValor()
					+ "</span>" + "</div>";
		} else if (type.equals(formatTypes.xml)) {
			return "<name> " + item.getName() + "</name>" + "<valor> " + item.getValor() + "</valor>";
		} else {
			// do nothing
			return "";
		}

	}

	/**
	 * Cria ou atualiza usuario
	 * 
	 * @param userName
	 * @param date
	 * @param isOfAge
	 */
	public void upsertUser(String userName, String date, String isOfAge) {
		Saver saver = new Saver();

		userName = userName.toUpperCase();

		Date dt = Utils.toDate(date, new SimpleDateFormat("dd/mm/yyyy"));
		if (new Date().getYear() - dt.getYear() < 65) {
			isOfAge = "false";
		}

		saver.upsertUser(userName, Utils.toDate(date, new SimpleDateFormat("dd/mm/yyyy")),
				isOfAge.equals("true") ? true : false);
	}

	/**
	 * Remover Usuario
	 */
	public void deleteUser(String userName) {
		Saver saver = new Saver();
		saver.deleteUser(userName);
	}

	/**
	 * Adicionar item ao carrinho
	 */
	public void addItemToShoppingCart(String user, String nameItem, int qt) {
		Saver saver = new Saver();

		nameItem = nameItem.toLowerCase().concat("_item");

		saver.addItemToShoppingCart(user, nameItem, qt);
	}

}