package com.celfocus.training.service.frontend.html;

import java.util.Objects;

import com.celfocus.training.domain.frontend.iteminfo.ItemInfoFrontendRequest;
import com.celfocus.training.domain.frontend.shoppingcart.ShoppingCartFrontendRequest;
import com.celfocus.training.domain.frontend.user.UserFrontendRequest;
import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.user.User;

public class HtmlFrontendRequest implements UserFrontendRequest, ShoppingCartFrontendRequest, ItemInfoFrontendRequest {

	@Override
	public String getFrontendUser(User user) throws Exception {
		Objects.requireNonNull(user);
		return "<div>" + "<h1>User</h1>" + "<span>" + user.getName() + "</span>" + "<span>" + user.getBirthDate()
				+ "</span>" + "<span>" + user.isSenior() + "</span>" + "</div>";
	}

	@Override
	public String getFrontendShoppingCart(ShoppingCart shoppingCart) throws Exception {
		Objects.requireNonNull(shoppingCart);
		return "<div>" + "<h1>ShoppingCart</h1>" + "<span> " + shoppingCart.getUser() + "</span>" + "<span> "
				+ shoppingCart.getItems() + "</span>" + "</div>";
	}

	@Override
	public String getFrontendItemInfo(ItemInfo itemInfo) throws Exception {
		Objects.requireNonNull(itemInfo);
		return "<div>" + "<h1>Item</h1>" + "<span> " + itemInfo.getName() + "</span>" + "<span> " + itemInfo.getValue()
				+ "</span>" + "</div>";
	}

}
