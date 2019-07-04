package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.celfocus.domain.ItemInfo;
import com.celfocus.domain.ShoppingCart;
import com.celfocus.domain.ShoppingCartItem;
import com.celfocus.domain.User;

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
		user.setBirthDate(birthDate);
		user.setOfAge(isOfAge);
		ShoppingCart shoppingCart = findUsersShoppingCart(user);
		if (shoppingCart != null) {
			// do nothing
		} else {
			ShoppingCart s = new ShoppingCart();
			s.setUser(user);
			shoppingCarts.add(s);
		}
		users.add(user);
		return user;
	}

	private User saveUser(String name, Date birthDate, boolean isOfAge) {
		User user = new User(name, birthDate, isOfAge);
		users.add(user);
		ShoppingCart s = new ShoppingCart();
		s.setUser(user);
		s.setItens(new ArrayList<>());
		shoppingCarts.add(s);
		return user;
	}

	public User upsertUser(String userName, Date birthDate, boolean isOfAge) {
		User user = findUser(userName);
		if (user != null) {
			return updateUser(user, birthDate, isOfAge);
		} else {
			return saveUser(userName, birthDate, isOfAge);
		}
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

	private ShoppingCart findUsersShoppingCart(User user) {
		for (ShoppingCart shoppingCart : shoppingCarts) {
			if (shoppingCart.getUser() == user) {
				return shoppingCart;
			}
		}
		return null;
	}

	private ShoppingCartItem findShoppingCartItem(ShoppingCart shoppingCart, String itemName) {
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getItens()) {
			if (shoppingCartItem.getItem().getName() == itemName) {
				return shoppingCartItem;
			}
		}
		return null;
	}

	public void addItemToShoppingCart(String name, String itemName, int quantity) {
		User user = findUser(name);
		if (user == null) {
			// Logger user not found
			return;
		}

		ShoppingCart shoppingCart = findUsersShoppingCart(user);

		if (shoppingCart == null) {
			// Logger users ShoppingCart not found
			return;
		}

		ShoppingCartItem shoppingCartItem = findShoppingCartItem(shoppingCart, itemName);

		if (shoppingCartItem != null) {
			shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
		} else {
			ItemInfo ifo = findItem(itemName);
			if (ifo == null) {
				// Logger item name does not exist in Items list
				return;
			}
			createShoppingCartItem(ifo, quantity, user);
		}
	}

	private void createShoppingCartItem(ItemInfo ifo, int quantity, User user) {
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		shoppingCartItem.setItem(ifo);
		shoppingCartItem.setQuantity(quantity);
		if (user.isOfAge() == true && (new Date().getYear() - user.getBirthDate().getYear() < 80)) {
			shoppingCartItem.setDiscount(0.2);
		} else if (user.isOfAge() == true) {
			shoppingCartItem.setDiscount(0.1);
		}

	}

	public void removeUsersItem(String userName, String nameItem) {
		User user = findUser(userName);

		if (user == null) {
			// Logger user not found
			return;
		}
		ShoppingCart usersShoppingCart = findUsersShoppingCart(user);

		if (usersShoppingCart == null) {
			// Logger user does not have a ShoppingCart
			return;
		}
		ShoppingCartItem shoppingCartItem = findShoppingCartItem(usersShoppingCart, nameItem);

		if (shoppingCartItem != null) {
			usersShoppingCart.getItens().remove(shoppingCartItem);
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