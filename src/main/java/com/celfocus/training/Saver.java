package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.celfocus.domain.ItemInfo;
import com.celfocus.domain.ShoppingCart;
import com.celfocus.domain.ShoppingCartItem;
import com.celfocus.domain.User;
import com.celfocus.training.util.Utils;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e
 * ItemInfo
 */
public class Saver {

	private static final List<User> users = new ArrayList<>();
	private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
	private static final List<ItemInfo> itens = new ArrayList<>();

	public User saveUser(User user) {
		users.add(user);
		return user;

	}

	private User updateUser(User user, Date birthDate, boolean isOfAge) {
		User toUpdate = findUser(user.getName());
		toUpdate.setBirthDate(birthDate);
		toUpdate.setOfAge(isOfAge);

		ShoppingCart shoppingCart = findUsersShoppingCart(user.getName());
		if (shoppingCart == null) {
			ShoppingCart cart = new ShoppingCart(user, new ArrayList<>());
			shoppingCarts.add(cart);
		}

		return user;
	}

	private User saveUser(String name, Date birthDate, boolean isOfAge) {
		User user = new User(name, birthDate, isOfAge);
		users.add(user);
		shoppingCarts.add(new ShoppingCart(user, new ArrayList<>()));
		return user;
	}

	public User upsertUser(String userName, Date birthDate, boolean isOfAge) {
		User user = findUser(userName);
		return user != null ? updateUser(user, birthDate, isOfAge) : saveUser(userName, birthDate, isOfAge);
	}

	private User findUser(String name) {
		for (User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public ItemInfo findItem(String name) {
		for (ItemInfo item : itens) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public void deleteUser(String userName) {
		User user = findUser(userName);
		if (user != null) {
			users.remove(user);
		}
	}

	private ShoppingCart findUsersShoppingCart(String userName) {
		for (ShoppingCart shoppingCart : shoppingCarts) {
			if (userName.equals(shoppingCart.getUser().getName())) {
				return shoppingCart;
			}
		}
		return null;
	}

	private ShoppingCartItem findShoppingCartItem(ShoppingCart shoppingCart, String itemName) {
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
			if (shoppingCartItem.getItem().getName().equals(itemName)) {
				return shoppingCartItem;
			}
		}
		return null;
	}

	public void addItemToShoppingCart(String name, String itemName, int quantity) {
		User user = findUser(name);
		if (user == null) {
			return;
		}

		ShoppingCart shoppingCart = findUsersShoppingCart(user.getName());
		if (shoppingCart == null) {
			return;
		}

		ShoppingCartItem shoppingCartItem = findShoppingCartItem(shoppingCart, itemName);
		if (shoppingCartItem != null) {
			shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
		} else {
			ItemInfo ifo = findItem(itemName);
			if (ifo == null) {
				return;
			}
			shoppingCart.getItems().add(new ShoppingCartItem(ifo, quantity, calculateDiscount(user)));
		}
	}

	private double calculateDiscount(User user) {
		return user.isOfAge() ? calculateSeniorDiscount(user) : 0.0;
	}

	private double calculateSeniorDiscount(User user) {
		return Utils.yearsSinceDate(user.getBirthDate()) < 80 ? 0.2 : 0.1;
	}

	public void removeUsersItem(String userName, String nameItem) {
		User user = findUser(userName);
		if (user == null) {
			return;
		}

		ShoppingCart usersShoppingCart = findUsersShoppingCart(user.getName());
		if (usersShoppingCart == null) {
			return;
		}
		ShoppingCartItem shoppingCartItem = findShoppingCartItem(usersShoppingCart, nameItem);

		if (shoppingCartItem != null) {
			usersShoppingCart.getItems().remove(shoppingCartItem);
		}

	}

	public void createitemIfNotExists(String itemName, double value) {
		ItemInfo item = findItem(itemName);
		if (item == null) {
			ItemInfo ift = new ItemInfo();
			ift.setName(itemName);
			ift.setValor(value);
			itens.add(ift);
		}
	}

}