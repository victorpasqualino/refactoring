package com.celfocus.domain;

import java.util.List;

public class ShoppingCart {
	private User user;
	private List<ShoppingCartItem> itens;

	public ShoppingCart() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ShoppingCartItem> getItens() {
		return itens;
	}

	public void setItens(List<ShoppingCartItem> itens) {
		this.itens = itens;
	}
}