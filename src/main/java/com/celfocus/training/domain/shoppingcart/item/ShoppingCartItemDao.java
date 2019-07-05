package com.celfocus.training.domain.shoppingcart.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.celfocus.training.domain.AbstractDao;
import com.celfocus.training.util.Utils;

public class ShoppingCartItemDao implements AbstractDao<ShoppingCartItem> {

	List<ShoppingCartItem> items = new ArrayList<>();

	@Override
	public List<ShoppingCartItem> getAll() {
		return items;
	}

	@Override
	public ShoppingCartItem create(ShoppingCartItem item) {
		Objects.requireNonNull(item);
		items.add(item);
		return item;
	}

	@Override
	public void delete(String name) {
		ShoppingCartItem item = findByItemName(name);
		if (item != null) {
			items.remove(item);
		}
	}

	public ShoppingCartItem findByItemName(String name) {
		if (Utils.isNullOrEmpty(name)) {
			return null;
		}
		for (ShoppingCartItem item : items) {
			if (name.equals(item.getInfo().getName())) {
				return item;
			}
		}
		return null;
	}

}
