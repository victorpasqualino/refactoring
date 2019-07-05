package com.celfocus.training.service.frontend.xml;

import java.util.Objects;

import com.celfocus.training.domain.frontend.iteminfo.ItemInfoFrontendRequest;
import com.celfocus.training.domain.frontend.shoppingcart.ShoppingCartFrontendRequest;
import com.celfocus.training.domain.frontend.user.UserFrontendRequest;
import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.user.User;
import com.celfocus.training.util.Utils;

public class XmlFrontendRequest implements UserFrontendRequest, ShoppingCartFrontendRequest, ItemInfoFrontendRequest {

	private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>";

	@Override
	public String getFrontendUser(User user) throws Exception {
		Objects.requireNonNull(user);
		return XML_HEADER + "<name> " + user.getName() + "</name>" + "<bd>"
				+ Utils.toString(user.getBirthDate(), DATE_FORMAT) + "</bd>" + "<older> " + user.isSenior()
				+ "</older>";
	}

	@Override
	public String getFrontendShoppingCart(ShoppingCart shoppingCart) throws Exception {
		Objects.requireNonNull(shoppingCart);
		return XML_HEADER + "<user> " + shoppingCart.getUser() + "</user>" + "<itens> " + shoppingCart.getItems()
				+ "</itens>";
	}

	@Override
	public String getFrontendItemInfo(ItemInfo itemInfo) throws Exception {
		Objects.requireNonNull(itemInfo);
		return "<name> " + itemInfo.getName() + "</name>" + "<valor> " + itemInfo.getValue() + "</valor>";
	}

}
