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
		HTML, XML
	}

	private static final String USER_TEMPLATE_HTML = "<div><h1>User</h1><span>%s</span><span>%s</span><span>%s</span></div>";
	private static final String SHOPPING_CART_TEMPLATE_HTML = "<div><h1>ShoppingCart</h1><span>%s</span><span>%s</span><span>%s</span></div>";
	private static final String ITEM_INFO_TEMPLATE_HTML = "<div><h1>User</h1><span>%s</span><span>%s</span><span>%s</span></div>";

	private static final String USER_TEMPLATE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><name> %s</name><bd>%s</bd><older>$s</older>";
	private static final String SHOPPING_CART_TEMPLATE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><user>%s</user><itens>%s</itens>";
	private static final String ITEM_INFO_TEMPLATE_XML = "<name> %s</name><valor> %s</valor>";

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
		if (type.equals(formatTypes.HTML)) {
			return String.format(USER_TEMPLATE_HTML, user.getName(), Utils.toString(user.getBirthDate(), "DD/MM/YYYY"));
		} else if (type.equals(formatTypes.XML)) {
			return String.format(USER_TEMPLATE_XML, user.getName(), Utils.toString(user.getBirthDate(), "DD/MM/YYYY"));
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
		if (type.equals(formatTypes.HTML)) {
			return String.format(SHOPPING_CART_TEMPLATE_HTML, shoppingCart.getUser().getName(),
					shoppingCart.getItems());
		} else if (type.equals(formatTypes.XML)) {
			return String.format(SHOPPING_CART_TEMPLATE_XML, shoppingCart.getUser().getName(), shoppingCart.getItems());
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
		if (type.equals(formatTypes.HTML)) {
			return String.format(ITEM_INFO_TEMPLATE_HTML, item.getName(), item.getValor());
		} else if (type.equals(formatTypes.XML)) {
			return String.format(ITEM_INFO_TEMPLATE_XML, item.getName(), item.getValor());
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
		if (Utils.yearsSinceDate(dt) < 65) {
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