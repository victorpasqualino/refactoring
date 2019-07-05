package com.celfocus.training.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.iteminfo.ItemInfoDao;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.shoppingcart.ShoppingCartDao;
import com.celfocus.training.domain.shoppingcart.item.ShoppingCartItem;
import com.celfocus.training.domain.user.User;
import com.celfocus.training.domain.user.UserDao;
import com.celfocus.training.util.Utils;

/**
 * 
 * @author NB23016
 *
 */
public class AppService {

	private UserDao userService = new UserDao();
	private ShoppingCartDao shoppingCartService = new ShoppingCartDao();
	private ItemInfoDao itemService = new ItemInfoDao();

	public User upsertUser(String name, Date birthDate) throws Exception {
		if (userService.userExists(name)) {
			return updateUser(name, birthDate);
		} else {
			return createUser(name, birthDate);
		}
	}

	private User updateUser(String userName, Date birthDate) throws Exception {
		User user = userService.update(new User(userName, birthDate, isSenior(birthDate)));
		if (!shoppingCartService.existsShoppingCart(userName)) {
			shoppingCartService.create(new ShoppingCart(user, new ArrayList<>()));
		}
		return user;
	}

	private User createUser(String name, Date birthDate) throws Exception {
		User user = userService.create(new User(name, birthDate, isSenior(birthDate)));
		shoppingCartService.create(new ShoppingCart(user, new ArrayList<>()));
		return user;
	}

	public void deleteUser(String name) throws Exception {
		userService.delete(name);
	}

	public void addItemToUserShoppingCart(String userName, String itemName, int quantity) {
		User user = userService.findUserByName(userName);
		Objects.requireNonNull(user);

		ShoppingCart shoppingCart = shoppingCartService.findByUserName(userName);
		Objects.requireNonNull(shoppingCart);

		for (ShoppingCartItem item : shoppingCart.getItems()) {
			if (item.getInfo().getName().equals(itemName)) {
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}

		ItemInfo info = itemService.findByName(itemName);
		if (info != null) {
			ShoppingCartItem item = new ShoppingCartItem(info, quantity, calculateDiscount(user));
			shoppingCart.getItems().add(item);
		}
	}

	private Double calculateDiscount(User user) {
		return user.isSenior() ? Utils.yearsSinceDate(user.getBirthDate()) < 80 ? 0.2 : 0.1 : 0.0;
	}

	public void removeItemFromUser(String userName, String itemName) {

		User user = userService.findUserByName(userName);
		Objects.requireNonNull(user);

		ShoppingCart shoppingCart = shoppingCartService.findByUserName(userName);
		Objects.requireNonNull(shoppingCart);

		for (ShoppingCartItem item : shoppingCart.getItems()) {
			if (itemName.equals(item.getInfo().getName())) {
				shoppingCart.getItems().remove(item);
				break;
			}
		}
	}

	public void createItemInfo(String name, double value) {
		if (itemService.existsItemInfo(name))
			return;
		itemService.create(new ItemInfo(name, value));
	}

	private boolean isSenior(Date birthDate) {
		return Utils.yearsSinceDate(birthDate) > 65;
	}

}
